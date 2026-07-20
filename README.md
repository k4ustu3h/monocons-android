<p align="center">
<img src="docs/images/repo-cover.png" alt="Monocons repository cover" width="640" />
</p>

<h1 align="center" style="margin-top: 0px;">Monocons</h1>

<div align="center">    
  <a href="https://github.com/k4ustu3h/monocons-android/blob/main/LICENSE">
    <img alt="GitHub License" src="https://img.shields.io/github/license/k4ustu3h/monocons-android?logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIGhlaWdodD0iMjRweCIgdmlld0JveD0iMCAtOTYwIDk2MCA5NjAiIHdpZHRoPSIyNHB4IiBmaWxsPSIjRkZGRkZGIj48cGF0aCBkPSJNNDgwLTQ0MHEtNTAgMC04NS0zNXQtMzUtODVxMC01MCAzNS04NXQ4NS0zNXE1MCAwIDg1IDM1dDM1IDg1cTAgNTAtMzUgODV0LTg1IDM1Wm0wIDMyMEwyOTMtNThxLTIwIDctMzYuNS01VDI0MC05NXYtMjU0cS0zOC00Mi01OS05NnQtMjEtMTE1cTAtMTM0IDkzLTIyN3QyMjctOTNxMTM0IDAgMjI3IDkzdDkzIDIyN3EwIDYxLTIxIDExNXQtNTkgOTZ2MjU0cTAgMjAtMTYuNSAzMlQ2NjctNThsLTE4Ny02MlptMC0yMDBxMTAwIDAgMTcwLTcwdDcwLTE3MHEwLTEwMC03MC0xNzB0LTE3MC03MHEtMTAwIDAtMTcwIDcwdC03MCAxNzBxMCAxMDAgNzAgMTcwdDE3MCA3MFpNMzIwLTE1OWwxNjAtNDEgMTYwIDQxdi0xMjRxLTM1IDIwLTc1LjUgMzEuNVQ0ODAtMjQwcS00NCAwLTg0LjUtMTEuNVQzMjAtMjgzdjEyNFptMTYwLTYyWiIvPjwvc3ZnPg==&color=43444B"></a>
  <a href="https://github.com/k4ustu3h/monocons-android/actions/workflows/build_debug_apk.yml">
    <img alt="GitHub Actions Workflow Status" src="https://img.shields.io/github/actions/workflow/status/k4ustu3h/monocons-android/build_debug_apk.yml?branch=main&label=Debug%20APK&logo=github&color=3F9E00"></a>
  <a href="https://github.com/k4ustu3h/monocons-android/actions/workflows/build_release_apk.yml">
    <img alt="GitHub Actions Workflow Status" src="https://img.shields.io/github/actions/workflow/status/k4ustu3h/monocons-android/build_release_apk.yml?label=Release%20APK&logo=github&color=3F9E00"></a>
  <a href="https://github.com/LawnchairLauncher/lawnicons/releases/tag/v2.18.0">
    <img alt="Lawnicons Version" src="https://img.shields.io/badge/Lawnicons_Version-v2.18.0-s?logo=data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIGZpbGw9Im5vbmUiIHZpZXdCb3g9IjAgMCAxOTIgMTkyIj48cGF0aCBzdHJva2U9IiNmZmYiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgc3Ryb2tlLXdpZHRoPSIxMiIgZD0iTTY1LjggMTE0LjA2YTEwLjEgMTAuMSAwIDAgMC0xNy41LS4wMWwtMjEuODcgMzcuNDFjLTQgNi44Ni44OCAxNS41NCA4Ljc1IDE1LjU0aDQzLjY0YzcuODcgMCAxMi43NS04LjY2IDguNzYtMTUuNTJsLTIxLjc5LTM3LjQyWiIvPjxjaXJjbGUgY3g9IjU0IiBjeT0iNTQiIHI9IjI5IiBzdHJva2U9IiNmZmYiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIgc3Ryb2tlLWxpbmVqb2luPSJyb3VuZCIgc3Ryb2tlLXdpZHRoPSIxMiIvPjxyZWN0IHdpZHRoPSI1NiIgaGVpZ2h0PSI1NiIgeD0iMTEwIiB5PSIyNiIgc3Ryb2tlPSIjZmZmIiBzdHJva2UtbGluZWNhcD0icm91bmQiIHN0cm9rZS1saW5lam9pbj0icm91bmQiIHN0cm9rZS13aWR0aD0iMTIiIHJ4PSIxNCIvPjxwYXRoIHN0cm9rZT0iI2ZmZiIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIiBzdHJva2UtbGluZWpvaW49InJvdW5kIiBzdHJva2Utd2lkdGg9IjEyIiBkPSJNMTI3LjIyIDExNC4wOGExMy45OSAxMy45OSAwIDAgMSAyMS41NiAwbDUuMSA2LjE3YTEzLjk5IDEzLjk5IDAgMCAwIDEuODcgMS44N2w2LjE4IDUuMWExMy45OSAxMy45OSAwIDAgMSAwIDIxLjU3bC02LjE4IDUuMWExMy45OSAxMy45OSAwIDAgMC0xLjg3IDEuODdsLTUuMSA2LjE3YTEzLjk5IDEzLjk5IDAgMCAxLTIxLjU2IDBsLTUuMS02LjE3YTEzLjk5IDEzLjk5IDAgMCAwLTEuODctMS44N2wtNi4xNy01LjFhMTMuOTkgMTMuOTkgMCAwIDEgMC0yMS41N2w2LjE3LTUuMWExMy45OSAxMy45OSAwIDAgMCAxLjg3LTEuODdsNS4xLTYuMTd6Ii8+PC9zdmc+"></a>

</div>

<br/>

Monocons is a fork of [Lawnicons](https://github.com/LawnchairLauncher/lawnicons), an icon pack developed by the Lawnchair team, and supported by the community. Originally an addon for Lawnchair 12 Alpha 5 and above to implement themed icons, it can now be used on many launchers.

_Monocons restores the original themed icons of apps and adds icons for apps that doesn't have one, instead of the outlined icons in the original Lawnicons._

Try Monocons on the latest version of Lawnchair. You can enable themed icons by going to `Home Settings → General → Icon Style` and choosing the desired option.

[Try Lawnchair](https://github.com/LawnchairLauncher/lawnchair#download)

## Download

<p align="left">
  <a href="https://apt.izzysoft.de/fdroid/index/apk/k4ustu3h.monocons.izzy">
    <img src="docs/images/badge-izzyondroid.png" alt="Get it on IzzyOnDroid" height="60" /></a>
  <a href="https://apps.obtainium.imranr.dev/redirect?r=obtainium://add/https://github.com/k4ustu3h/monocons-android">
    <img src="docs/images/badge-obtainium.png" alt="Get it on Obtainium" height="60" /></a>
  <a href="https://github.com/k4ustu3h/monocons-android/releases/latest">
    <img src="docs/images/badge-github.png" alt="Get it on GitHub" height="60" /></a>
</p>

### Pre-release version with recent updates

[nightly.link](https://nightly.link/k4ustu3h/monocons-android/workflows/build_debug_apk/main/Debug%20APK) • [Obtainium](https://apps.obtainium.imranr.dev/redirect?r=obtainium://app/%7B%22id%22%3A%22ak4ustu3h.monocons%22%2C%22url%22%3A%22https%3A%2F%2Fgithub.com%2Fk4ustu3h%2Fmonocons%22%2C%22author%22%3A%22k4ustu3h%22%2C%22name%22%3A%22Monocons%22%2C%22preferredApkIndex%22%3A0%2C%22additionalSettings%22%3A%22%7B%5C%22includePrereleases%5C%22%3Atrue%2C%5C%22fallbackToOlderReleases%5C%22%3Atrue%2C%5C%22filterReleaseTitlesByRegEx%5C%22%3A%5C%22Monocons%20Nightly%5C%22%2C%5C%22filterReleaseNotesByRegEx%5C%22%3A%5C%22%5C%22%2C%5C%22verifyLatestTag%5C%22%3Afalse%2C%5C%22dontSortReleasesList%5C%22%3Afalse%2C%5C%22useLatestAssetDateAsReleaseDate%5C%22%3Afalse%2C%5C%22trackOnly%5C%22%3Afalse%2C%5C%22versionExtractionRegEx%5C%22%3A%5C%22%5C%22%2C%5C%22matchGroupToUse%5C%22%3A%5C%22%5C%22%2C%5C%22versionDetection%5C%22%3Afalse%2C%5C%22releaseDateAsVersion%5C%22%3Atrue%2C%5C%22useVersionCodeAsOSVersion%5C%22%3Afalse%2C%5C%22apkFilterRegEx%5C%22%3A%5C%22%5C%22%2C%5C%22invertAPKFilter%5C%22%3Afalse%2C%5C%22autoApkFilterByArch%5C%22%3Atrue%2C%5C%22appName%5C%22%3A%5C%22Monocons%20Nightly%5C%22%2C%5C%22shizukuPretendToBeGooglePlay%5C%22%3Afalse%2C%5C%22allowInsecure%5C%22%3Afalse%2C%5C%22exemptFromBackgroundUpdates%5C%22%3Afalse%2C%5C%22skipUpdateNotifications%5C%22%3Afalse%2C%5C%22about%5C%22%3A%5C%22%5C%22%7D%22%2C%22overrideSource%22%3Anull%7D) • [GitHub](https://github.com/k4ustu3h/monocons-android/releases/tag/nightly)

## Supporting

> [!IMPORTANT]
> Support us on Lawnchair's Open Collective or its GitHub to help maintain Lawnicons, and in turn Monocons.

[Open Collective](https://opencollective.com/lawnchair) • [GitHub](https://github.com/sponsors/LawnchairLauncher)

## Contributing

<div align="left">
    <a href="CONTRIBUTING.md">
        <img alt="Monocons guidelines" src="https://img.shields.io/badge/Monocons%20guidelines-3F9E00?logo=gitbook&logoColor=ffffff"></a>
    <a href="https://lawnchair.crowdin.com/lawnicons">
        <img alt="Lawnicons localization issue" src="https://img.shields.io/badge/Localization-3F9E00?logo=crowdin&logoColor=ffffff"></a>      
</div>

### Icons

You may add missing icons if they are of high quality, with no more than 5 icons per pull request. It's essential to follow the Monocons design guidelines.

### Development

You're welcome to work on our issues.

### Icons

It's required to follow the Monocons design guidelines. Accepted contributions include: new icons capped at 5 per pull request, missing app IDs, rebranding reports, and updates for outdated icons.

[Monocons design guidelines](https://github.com/k4ustu3h/monocons-android/blob/main/CONTRIBUTING.md#contributing-icons-tldr) • [Report outdated and low-quality icons](https://github.com/k4ustu3h/monocons-android/issues/new?template=report_outdated_and_low_quality_icons.yml)

### Icon requests

`Open Monocons → Tap "Request icons" → Select and request icons`

The only guaranteed way to get an icon added is to contribute it yourself.

## Credits

- [Lawnicons](https://github.com/LawnchairLauncher/lawnicons)
- [RKicons](https://github.com/RadekBledowski/rkicons) - The first Lawnicons fork that restored the original icons.
- [Material Symbols](https://fonts.google.com/icons)
- [Simple Icons](https://simpleicons.org/)
