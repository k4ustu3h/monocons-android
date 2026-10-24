#!/usr/bin/env python3
import subprocess
import json
import re
import os
import urllib.request
from collections import defaultdict
from datetime import datetime, timezone


def run(cmd: str) -> str:
    result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
    if result.returncode != 0:
        return ""
    return result.stdout.strip()


def get_latest_tag(repo: str) -> str:
    # 1. Try GitHub CLI
    tag = run(f"gh api repos/{repo}/tags --jq '.[].name' | grep '^v' | grep -v 'nightly' | head -1")
    if tag:
        return tag

    # 2. Try GitHub REST API via urllib
    token = os.getenv("GITHUB_TOKEN")
    headers = {"User-Agent": "nightly-notes-script"}
    if token:
        headers["Authorization"] = f"Bearer {token}"
    try:
        req = urllib.request.Request(f"https://api.github.com/repos/{repo}/tags", headers=headers)
        with urllib.request.urlopen(req, timeout=5) as resp:
            data = json.loads(resp.read().decode())
            for item in data:
                name = item.get("name", "")
                if name.startswith("v") and "nightly" not in name:
                    return name
    except Exception:
        pass

    # 3. Try git ls-remote origin
    tag = run("git ls-remote --tags --refs origin 'v*' | sed 's|.*refs/tags/||' | sort -V | grep -v 'nightly' | tail -1")
    if tag:
        return tag

    # 4. Try local git tag
    tag = run("git tag --list 'v*' --sort=-version:refname | grep -v 'nightly' | head -1")
    if tag:
        return tag

    return "v1.2.1"


def get_merged_prs(repo: str, latest_tag: str) -> list[dict]:
    if latest_tag:
        output = run(f"git log {latest_tag}..HEAD --format='%s|%an|%h'")
    else:
        output = run("git log --since='24 hours ago' --format='%s|%an|%h'")

    if not output:
        return []

    commits = []
    for line in output.split("\n"):
        if not line.strip():
            continue

        parts = line.split("|")
        title = parts[0].strip() if len(parts) > 0 else ""
        git_author = parts[1].strip() if len(parts) > 1 else "unknown"
        commit_hash = parts[2].strip() if len(parts) > 2 else ""

        author = git_author
        labels = []
        is_pr = False
        pr_number = commit_hash
        title_lower = title.lower()

        code_prefixes = ["fix:", "refactor:", "perf:", "chore:", "ci:", "build:", "test:"]
        if any(title_lower.startswith(prefix) for prefix in code_prefixes):
            if not any(word in title_lower for word in ["dependenc", "bump"]):
                labels.append({"name": "code"})

        pr_match = re.search(r"\(#(\d+)\)\s*$", title)
        if pr_match:
            is_pr = True
            pr_number = pr_match.group(1)

            pr_data_json = run(f"gh pr view {pr_number} -R {repo} --json author,labels")
            if pr_data_json:
                try:
                    pr_data = json.loads(pr_data_json)
                    github_login = pr_data.get("author", {}).get("login")
                    if github_login:
                        author = github_login

                    for label in pr_data.get("labels", []):
                        labels.append({"name": label.get("name", "").lower()})
                except json.JSONDecodeError:
                    pass
        else:
            if commit_hash:
                github_login = run(f"gh api repos/{repo}/commits/{commit_hash} --jq '.author.login'")
                if github_login and github_login.lower() != "null":
                    author = github_login

        commits.append({
            "title": title,
            "author": {"login": author},
            "labels": labels,
            "mergedAt": datetime.now(timezone.utc).isoformat(),
            "number": pr_number,
        })

    return commits


def parse_icon_stats(title: str) -> tuple[int, int, int]:
    icons = sum(int(x) for x in re.findall(r"\+?(\d+)\s*icons?", title, re.IGNORECASE))
    links = sum(int(x) for x in re.findall(r"\+?(\d+)\s*links?", title, re.IGNORECASE))
    updates = sum(int(x) for x in re.findall(r"\+?(\d+)\s*updates?", title, re.IGNORECASE))
    return icons, links, updates


def is_first_timer_from_labels(pr: dict) -> bool:
    labels = [l.get("name", "").lower() for l in pr.get("labels", [])]
    return "first timer" in labels


def get_icon_contributors(prs: list[dict]) -> list[dict]:
    contributors = defaultdict(lambda: {"icons": 0, "links": 0, "updates": 0, "first_time": False})

    for pr in prs:
        title = pr.get("title", "")
        author = pr.get("author", {}).get("login", "unknown")

        if any(word in title.lower() for word in ["icon", "link", "update", "qa", "feat"]):
            icons, links, updates = parse_icon_stats(title)

            if icons > 0 or links > 0 or updates > 0:
                contributors[author]["icons"] += icons
                contributors[author]["links"] += links
                contributors[author]["updates"] += updates

                if is_first_timer_from_labels(pr):
                    contributors[author]["first_time"] = True

    def sort_key(item):
        stats = item[1]
        return -(stats["icons"] + stats["links"] + stats["updates"])

    return [
        {
            "author": author,
            "icons": stats["icons"],
            "links": stats["links"],
            "updates": stats["updates"],
            "first_time": stats["first_time"],
        }
        for author, stats in sorted(contributors.items(), key=sort_key)
    ]


def generate_notes() -> str:
    repo = os.getenv("GH_REPO", "k4ustu3h/monocons-android")
    latest_tag = get_latest_tag(repo)
    prs = get_merged_prs(repo, latest_tag)

    if not prs:
        return "No changes in this nightly build."

    total_prs = len(prs)

    icon_prs = [
        p for p in prs
        if any(w in p.get("title", "").lower() for w in ["icon", "link", "update", "qa", "feat"])
    ]
    dep_prs = [
        p for p in prs
        if any(w in p.get("title", "").lower() for w in ["dependenc", "update dependency", "bump"])
    ]
    code_prs = [
        p for p in prs
        if "code" in [l["name"] for l in p.get("labels", [])]
    ]

    all_authors = set()
    for pr in prs:
        author = pr.get("author", {}).get("login", "")
        if author:
            all_authors.add(author)

    total_icons = 0
    total_links = 0
    for pr in icon_prs:
        i, l, u = parse_icon_stats(pr.get("title", ""))
        total_icons += i
        total_links += l

    sha = os.getenv("GITHUB_SHA", "unknown")[:7]
    branch = os.getenv("GITHUB_REF_NAME", "main")

    icon_contributors = get_icon_contributors(icon_prs)

    lines = []
    lines.append(f"Build: `{sha}` \u2022 Branch: `{branch}`\n")
    lines.append("### Summary")
    lines.append(f"- **{total_prs} commits** merged")
    if code_prs:
        lines.append(f"- **{len(code_prs)} code improvements**")
    lines.append(f"- **~{total_icons} icons** and **~{total_links} links** added")
    lines.append(f"- **{len(dep_prs)} dependency updates** applied")
    lines.append(f"- **{len(all_authors)} contributors** participated")

    if icon_contributors:
        lines.append(f"\n### Top icon contributors")
        first_timers_list = []
        for c in icon_contributors:
            parts = []
            if c["icons"] > 0:
                label = "icon" if c["icons"] == 1 else "icons"
                parts.append(f"{c['icons']} {label}")
            if c["links"] > 0:
                label = "link" if c["links"] == 1 else "links"
                parts.append(f"{c['links']} {label}")
            if c["updates"] > 0:
                label = "update" if c["updates"] == 1 else "updates"
                parts.append(f"{c['updates']} {label}")

            if c.get("first_time"):
                first_timers_list.append(f"@{c['author']}: {' + '.join(parts)}")
            else:
                lines.append(f"@{c['author']}: {' + '.join(parts)}")

        if first_timers_list:
            lines.append(f"\n#### First timers")
            lines.extend(first_timers_list)

    if code_prs:
        lines.append(f"\n### Code")
        for pr in code_prs:
            author = pr.get("author", {}).get("login", "unknown")
            title = pr.get("title", "")
            number = pr.get("number", "")

            clean_title = re.sub(r"\s*\(#\d+\)\s*$", "", title)
            ref = f"#{number}" if str(number).isdigit() else f"`{number}`"
            lines.append(f"- {clean_title} by @{author} in {ref}")

    lines.append(
        f"\nFull Changelog: [{latest_tag}...nightly](https://github.com/{repo}/compare/{latest_tag}...nightly)"
    )

    return "\n".join(lines)


if __name__ == "__main__":
    print(generate_notes())
