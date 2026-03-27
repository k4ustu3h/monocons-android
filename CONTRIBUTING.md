# Forkicons contributing guide

Welcome to the Forkicons contributing guide!

If you find errors or want to suggest improvements in the guide itself, create an issue.

## Involvement

**Contributors**

The development of the core app is not done here. Forkicons focuses on providing a curated set of icons and adapting the base project to our needs. The Lawnicons community (which includes everyone who wants to contribute) handles the core app development. The primary focus is on managing icons and the specific features that Forkicons offers.

[Lawnicons contributors](https://github.com/LawnchairLauncher/lawnicons/graphs/contributors) • [Forkicons contributors](https://github.com/k4ustu3h/forkicons/graphs/contributors) • [Lawnchair](https://github.com/k4ustu3h/lawnchair)

**Development**

The main tasks are to maintain Forkicons and interaction with launchers, fix bugs, add new features and automate organizational processes. Please see our issues for more details.

**Icons**

You can contribute your icons, fulfill icon requests, add missing app IDs, refine and update existing icons, clean up dead apps and duplicates. Mastering the Forkicons design guidelines in practice will allow you to do icon reviews.

## Contributing code

Code-related contributions are welcome, but please note a crucial distinction: **the core application code is maintained upstream in the Lawnicons repository**.

If you discover a bug or issue with the app’s core functionality, please check the Lawnicons repository first. If the issue exists there, you should report it to Lawnicons and track its progress on their issues page. Once they release a stable version with a fix, it will be merged into Forkicons.

If your code contribution is specific to Forkicons (e.g., adding a new feature that doesn't exist in the upstream Lawnicons), please discuss it by creating an issue.

Visit the Lawnicons developer wiki for developer information regarding Lawnicons.

[Lawnicons developer wiki](https://github.com/LawnchairLauncher/lawnicons/wiki)

## Forkicons design guidelines

The contributors who laid the foundations: [GrabsterTV](https://github.com/Grabstertv) and [Chefski](https://github.com/Chefski).

> [!TIP]
> The Lawnicons design guidelines are also available in Figma, you can practice there.  
> [View in Figma](https://www.figma.com/community/file/1544976260626797886)

[Common issues](https://github.com/k4ustu3h/forkicons/blob/main/docs/images/common-issues-to-fix.png)

### Approach

The Forkicons style is built on strong fundamentals and a commitment to quality. To minimize the need for rework, please read these guidelines carefully. Our main goal is to create high-quality icons that clearly represent their respective apps. To achieve this goal, you may sometimes need to redesign an icon from scratch.

Tips

- Make no more than 5 icons at a time, as long as your PRs require rework.
- Prioritize quality.
- Try to reproduce the original style exactly.
- Practice on easy-to-make icons to understand the whole process.
- See how other contributors have made pull requests (PRs).

[Merged PRs](https://github.com/k4ustu3h/forkicons/pulls?q=is%3Apr+is%3Amerged+label%3Aicons)

### Naming

**TL;DR**

```
_2048.svg | 2048
lawnicons.svg | Lawnicons
habitacao_caixa.svg | Habitação Caixa
beijing_card.svg | 北京一卡通 ~~ Beijing Card
a_and_w.svg | A&amp;W
```

**App name**

The main app name should be in its main language. It can be found in app stores or primary sources.

Apps with non-English names require an additional name based on the English alphabet. At best it will be a localized official app name. If an app name is mostly made up of letters from the English alphabet, it doesn't need an additional one.

Tips

- Add localized names if available.
- Transliterate non-English names when there are no localized ones.
- Separate app names using `~~`. First, the main app name, then the additional one.
- Delete things that aren't part of an app name.
- Use the HTML character references for special symbols: for instance, `&amp;` instead of "&".

**Icon name (drawable)**

Repeat the app name. Use `a–z`, `0–9`, and `_` for spaces. Insert `_` before a digit at the beginning of an icon name.

Tips

- When multiple apps are linked to the same icon, choose the most popular app name for it.
- Replace non-English letters with English letters.

### Fundamentals

#### 1 Canvas

<img src="docs/images/fundamentals-1-canvas.png" alt="1 Canvas" height="200" />

`192 × 192 px`. Use the correct canvas size to create a safe zone around icons.

#### 2 Abstract icons

<img src="docs/images/fundamentals-2-abstract-icons.png" alt="2 Abstract icons" height="200" />

Determine the abstract icon size before you start. The exact size is determined by the shape's position, and the graphic editor used. The icon content area is `160 × 160 px`.

Tips

- Follow the blue guides.
- Use existing icons as an example.
- Aim for pixel-perfect.
- The margin of error is `<0.1 px`.

#### 3 Square icons

<img src="docs/images/fundamentals-3-square-icons.png" alt="3 Square icons" height="200" />

Determine the square icon size before you start. These are icons with `50%` or more of the edges running along the square. The exact size is determined by the shape's position, and the graphic editor used. The square icon content area is `154 × 154 px`.

Tips

- Follow the golden guides.
- Use existing icons as an example.
- Aim for pixel-perfect.
- The margin of error is `<0.1 px`.

#### 4 Color

<img src="docs/images/fundamentals-4-color.png" alt="4 Color" height="200" />

All shapes must be without fill. Shapes can be transparent to match the original icon.

### Quality

#### 1 Consistency

<img src="docs/images/quality-1-consistency.png" alt="*1 Consistency" height="200" />

The primary goal is to maintain consistency with the app's official monochrome icon.

- **If an official one exists:** Use it to stay true to the original branding.
- **If it doesn't:** Design a high-quality, recognizable monochrome version of the original icon.

#### 2 Visual balance

<img src="docs/images/quality-2-visual-balance.png" alt="2 Visual balance" height="200" />

- Ensure all elements are visually balanced.
- Avoid jarring shifts between shapes or sections.
- Make the final icon feel unified and intentional, not pieced together.

#### 3 Excessive density

<img src="docs/images/quality-4-excessive-density.png" alt="3 Excessive density" height="200" />

Your icons should be clean and easy to read. Avoid making them too dense or detailed. Avoid unnecessary tranparency.

Tips

- Icons should have enough space between elements to be clear at small sizes.
- Enlarge original icons to make the main features easier to draw.
- Simplify or redraw.

#### 4 Alignment

<img src="docs/images/quality-5-alignment.png" alt="4 Alignment" height="200" />

Icons should be centered, but shape-aware. Align them to the optical center as much as possible within the icon content area. The optical aligment is where your icon looks and feels centered.

#### 5 Text icons

<img src="docs/images/quality-6-text-icons.png" alt="5 Text icons" height="200" />

- Avoid using long text strings (more than `3` letters in `1` line) unless it's a critical part of the branding.

- Brands with text logos should be studied carefully to create a recognizable, high-quality monochrome version.

- If you want to keep only a text, then it should be of high quality and occupy at least ¹⁄₃ of the icon content area.

#### 6 Complex icons

<img src="docs/images/quality-7-complex-icons.png" alt="6 Complex icons" height="200" />

First, try to make a complex icon based on the original. When it’s clear that the original icon can’t be conveyed in a monochrome style, you need to study the visual part of an app or a game. Whatever you come to, the result should be at least logical and high-quality.

Sources for creating a recognizable icon

- Branding guidelines.
- UI or gameplay.
- Website’s favicons.
- In-app icons.
- Essence of an app or a game.
- Combination of recognizable features and your own ideas.

#### 7 Minimal icons

<img src="docs/images/quality-8-minimal-icons.png" alt="7 Minimal icons" height="200" />

Some minimal icons should be detailed based on an app design to become more recognizable. Add distinctive features to them **only** when it makes sense.

Only add the additional feaures if they appear anywhere in the app. Don't stray away from the original.

#### 8 Version badges

<img src="docs/images/quality-9-version-badges.png" alt="8 Version badges" height="200" />

Use one of our version badges to highlight a separate version of an app **only** if the original icons are indistinguishable. For instance, it could be nightly builds, paid apps with a free one available, or lite versions. Keep in mind that cases such as Opera Mini or Firefox Klar are different.

Tips

- Create a safe zone of 8px stroke around the version badge by cutting shapes (refer to the Excessive density section).
- The default location for the badge is the lower right corner, but a lot depends on the icon shape.
- Avoid shifting icons for the sake of the badge.
- Create an issue if there aren’t enough version badges.

## Icon contribution tools

### Vector graphics editor

To create icons, you need a vector graphics editor, which allows you to save icons in SVG format. Mobile vector editors won't work. We recommend Figma because it has easier quality control. You can use Advanced SVG Export to save optimized SVGs in Figma.

[Figma](https://www.figma.com/) • [Advanced SVG Export](https://www.figma.com/community/plugin/782713260363070260)

### GitHub Desktop

You can use it to create a local copy of your repository on GitHub and upload all the changes. Before getting into your repository, the changes must appear in your local copy.

[GitHub Desktop](https://github.com/apps/desktop)

### App ID search tool

You can use it to find app IDs. If you fulfill icon requests from our table, all the app IDs are there.

[How to find app IDs](#how-to-find-app-ids)

### Other tools

**File explorer**. It will help you copy icons to a local copy of your repository.

**Text editor**. It will help you to link icons and app IDs in `appfilter.xml`. This is how icon packs work.

**Terminal (command line)**. It will add convenience if you regularly contribute dozens of icons.

## How to find app IDs

An app ID is a record consisting of a package and an activity, separated by `/`. App IDs allow you to link icons and apps.

Sample (Forkicons)  
Package: `k4ustu3h.forkicons`  
Activity: `k4ustu3h.forkicons.MainActivity`  
App component: `k4ustu3h.forkicons/k4ustu3h.forkicons.MainActivity`

**Forkicons**

This method is suitable if you are interested in installed apps that aren't supported in Forkicons.

1. Install and open Forkicons.
2. Long press our logo.
3. Swipe down.
4. Copy missing app IDs to clipboard.
5. Save it wherever it's convenient.

[Download Forkicons](https://github.com/k4ustu3h/forkicons#download)

**Icon Request**

1. Download and launch Icon Request.
2. Tap one of the options:

- UPDATE EXISTING — to copy app IDs.
- REQUEST NEW — to save icon images and app IDs. This option is better if you are creating icons.

3. Use the Icon Request toolbar to select apps.
4. Copy, save or share.

[Google Play](https://play.google.com/store/apps/details?id=de.kaiserdragon.iconrequest) • [GitHub](https://github.com/Kaiserdragon2/IconRequest/releases)

**Icon Pusher**

1. Download and launch Icon Pusher.
2. Select the icons you want to upload or select all by pressing the square in the top right.
3. Submit the selected apps.
4. View your submission on the Icon Pusher website.

[Google Play](https://play.google.com/store/apps/details?id=dev.southpaw.iconpusher) • [Website](https://iconpusher.com/)

**Android Debug Bridge (adb)**

1. Connect your Android device or emulator to your laptop/desktop PC that has `adb` installed.
2. Open the app whose details you want to inspect (e.g. Telegram).
3. Open a new Command Prompt or Terminal window and input `adb devices`.
4. Finally, type the below-given command to get the information about the currently open app.

[How to install ADB](https://www.xda-developers.com/install-adb-windows-macos-linux/)

Mac or Linux

```console
adb shell dumpsys window | grep 'mCurrentFocus'
```

Windows

```console
adb shell dumpsys window | findstr "mCurrentFocus"
```

![](docs/images/contributing-image-3.png)

## Adding icons and missing app IDs to Forkicons

You need to link SVGs and app IDs correctly, create a PR to our repository through your fork, and wait for it to be reviewed.

Tips

- Avoid name conflicts.
- Add missing app IDs to icons that are identical to the originals.
- Make sure your icons or missing app IDs haven't been added earlier: search the `appfilter.xml` and check PRs.

[How to find app IDs](#how-to-find-app-ids) • [Icon contribution tools](#icon-contribution-tools) • [appfilter.xml](app/assets/appfilter.xml) • [PRs](https://github.com/k4ustu3h/forkicons/pulls)

### Manual process

Let's imagine that you have an icon in SVG format, an app name and an app ID.

Icon: `forkicons.svg`  
App name: `Forkicons`  
App ID: `k4ustu3h.forkicons/k4ustu3h.forkicons.MainActivity`

1. Fork the Forkicons repository.
2. Clone the fork via GitHub Desktop.
3. Open it with a file explorer. This is your local copy.
4. Сopy `forkicons.svg` to the `svgs/` folder. Note the icon name.
5. Open `app/assets/appfilter.xml` and add a new line using the same template as the existing lines.

```
Do
<item component="ComponentInfo{k4ustu3h.forkicons/k4ustu3h.forkicons.MainActivity}" drawable="forkicons" name="Forkicons" />

Template
<item component="ComponentInfo{APP_ID}" drawable="ICON_NAME" name="APP_NAME" />
```

6. Save changes and push it to your fork via GitHub Desktop.
7. Open your fork in a web browser and create a PR: `Contribute → Open pull request`. Describe your PR according to our templates.
8. Make sure that the build went without errors and await a review (better to do a self-review).
9. We will merge your PR, fix the little things, or leave a comment asking you to rework.

**Clean commit history**

A commit history appears after your PR is merged. Please keep your repository up to date if you plan to create more than one PR, otherwise you may drag the commit history through all your PRs. There are two main ways to do this:

- Open `Terminal` on the local copy of your repository via GitHub Desktop. Run `git reset --hard upstream/main`. Overwrite your repository with your local copy via GitHub Desktop: `Force push origin`.
- Or delete your repository and start the contribution process from scratch.

### icontool.py

This tool will help you if you regularly contribute icons or missing app IDs.

[icontool.py guide](/docs/icontool_guide.md)
