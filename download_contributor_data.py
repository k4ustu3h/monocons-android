import os
import json
import urllib.request
import argparse
import shutil
from PIL import Image

API_URL = "https://api.github.com/repos/k4ustu3h/monocons-android/contributors"
RAW_DIR = "app/assets"
DRAWABLE_DIR = "app/src/izzy/res/drawable"

EXTRA_AVATARS = [
    {
        "login": "Grabstertv",
        "id": 49114212,
        "avatar_url": "https://avatars.githubusercontent.com/u/49114212?v=4"
    }
]

def clean_offline_data():
    print("Running cleanup...")

    json_path = os.path.join(RAW_DIR, "contributors.json")
    if os.path.exists(json_path):
        os.remove(json_path)
        print(f"Deleted: {json_path}")
    else:
        print(f"Not found (skipping): {json_path}")

    if os.path.exists(DRAWABLE_DIR):
        shutil.rmtree(DRAWABLE_DIR)
        print(f"Deleted directory and contents: {DRAWABLE_DIR}")
    else:
        print(f"Not found (skipping): {DRAWABLE_DIR}")

    print("Cleanup complete.")

def download_offline_data():
    os.makedirs(RAW_DIR, exist_ok=True)
    os.makedirs(DRAWABLE_DIR, exist_ok=True)

    print("Fetching contributors list...")
    req = urllib.request.Request(API_URL, headers={'User-Agent': 'Mozilla/5.0'})
    with urllib.request.urlopen(req) as response:
        data = json.loads(response.read().decode())

    json_path = os.path.join(RAW_DIR, "contributors.json")
    with open(json_path, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=4)
    print(f"Saved pure contributors.json to {json_path}")

    download_list = list(data)
    existing_ids = {user.get("id") for user in data}

    for extra in EXTRA_AVATARS:
        if extra["id"] not in existing_ids:
            download_list.append(extra)
            print(f"Added {extra['login']} to image download queue")

    for user in download_list:
        user_id = str(user.get("id", ""))
        avatar_url = user.get("avatar_url", "")
        if not user_id or not avatar_url: continue

        safe_name = f"avatar_u{user_id}"
        temp_path = os.path.join(DRAWABLE_DIR, f"{safe_name}.tmp")
        final_path = os.path.join(DRAWABLE_DIR, f"{safe_name}.webp")

        try:
            print(f"Downloading ID {user_id}...")
            img_req = urllib.request.Request(avatar_url, headers={'User-Agent': 'Mozilla/5.0'})
            with urllib.request.urlopen(img_req) as img_response:
                with open(temp_path, 'wb') as f:
                    f.write(img_response.read())

            with Image.open(temp_path) as img:
                if img.mode in ("RGBA", "P"):
                    img = img.convert("RGBA")

                img.thumbnail((128, 128), Image.Resampling.LANCZOS)

                img.save(final_path, "WEBP", quality=80)

            os.remove(temp_path)

        except Exception as e:
            print(f"Failed to process {user_id}: {e}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Manage offline contributor data for Monocons Izzy build.")
    parser.add_argument(
        "--clean",
        action="store_true",
        help="Clean up the previously downloaded contributors.json and avatar images."
    )

    args = parser.parse_args()

    if args.clean:
        clean_offline_data()
    else:
        download_offline_data()
