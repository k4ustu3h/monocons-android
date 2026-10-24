# Monocons design guidelines

> [!TIP]
> [View in Figma](https://www.figma.com/community/file/1632857392429740665)

Please read these guidelines carefully to avoid rework. The goal is to create high-quality icons that represent their apps, even if it means redesigning from scratch. Prioritize quality over exact reproduction, and make it pixel-perfect.

The Lawnicons contributors who laid the foundations: [GrabsterTV](https://github.com/Grabstertv) and [Chefski](https://github.com/Chefski).

## Naming

### TL;DR
```
_2048.svg | 2048
lawnicons.svg | Lawnicons
habitacao_caixa.svg | Habitação Caixa
beijing_card.svg | 北京一卡通 ~~ Beijing Card
a_and_w.svg | A&amp;W
```

### App name

The app name should be in its primary language, sourced from app stores. For non-English names, add a localized or transliterated English version, separated by `~~` (main name first). If the name is mostly English letters, no second name is needed.

Delete things that aren't part of an app name, and use HTML character references for special symbols (for example, &amp; instead of &).

### Icon name (drawable)

Repeat the app name, using `a–z`, `0–9`, and `_` for spaces. Insert `_` before a digit if the icon name starts with one. For multiple apps sharing one icon, use the most popular name.

## Fundamentals

The best way to learn the design guidelines is to read them through once, then refer back to each point as you work on each icon.

### 1 Canvas

<img src="images/fundamentals-1-canvas.png" alt="1 Canvas" height="200" />

`192 × 192 px`. Use the correct canvas size to create a safe zone around icons.

### 2 Abstract icons

<img src="images/fundamentals-2-abstract-icons.png" alt="2 Abstract icons" height="200" />

The exact abstract icon size is determined by the shape's position, and the graphic editor used.
The icon content area is `160 × 160 px`.

Tips: follow the blue guides, use existing icons as an example, and aim for pixel-perfect.

### 3 Square icons

<img src="images/fundamentals-3-square-icons.png" alt="3 Square icons" height="200" />

These are icons with `50%` or more of the edges running along the square. The square icon size is determined by the shape's position, and the graphic editor used. The square icon content area is `154 × 154 px`.

Tips: follow the golden guides, use existing icons as an example, and aim for pixel-perfect.

### 4 Color

<img src="images/fundamentals-4-color.png" alt="4 Color" height="200" />

All shapes must be without fill. Shapes can have some transparency to match the original icon.

## Quality

### 1 Consistency

<img src="images/quality-1-consistency.png" alt="*1 Consistency" height="200" />

The primary goal is to maintain consistency with the app's official monochrome icon.

- **If an official one exists:** Use it to stay true to the original branding.
- **If it doesn't:** Design a high-quality, recognizable monochrome version of the original icon.

### 2 Visual balance

<img src="images/quality-2-visual-balance.png" alt="2 Visual balance" height="200" />

- Ensure all elements are visually balanced.
- Avoid jarring shifts between shapes or sections.
- Make the final icon feel unified and intentional, not pieced together.

### 3 Excessive density

<img src="images/quality-3-excessive-density.png" alt="3 Excessive density" height="200" />

Your icons should be clean and easy to read. Avoid making them too dense or detailed. Avoid unnecessary tranparency.

Tips: move lines and shapes further apart or combine into one, and enlarge original icons to make the main features easier to draw.

### 4 Alignment

<img src="images/quality-4-alignment.png" alt="4 Alignment" height="200" />

Icons should be centered, but shape-aware. Align them to the optical center as much as possible within the icon content area. The optical aligment is where your icon looks and feels centered.

### 5 Text icons

<img src="images/quality-5-text-icons.png" alt="5 Text icons" height="200" />

Text longer than `3` letters in `1` line usually aren't legible. Apps and games with text icons often need to be studied in order to create a recognizable monochrome-style icon.

### 6 Complex icons

<img src="images/quality-6-complex-icons.png" alt="6 Complex icons" height="200" />

First, try to make a complex icon based on the original. When it’s clear that the original icon can’t be conveyed in a monochrome style, you need to study the visual part of an app or a game. Whatever you come to, the result should be at least logical and high-quality.

Recognition sources: branding guidelines, UI or gameplay, website favicons, in-app icons, essence of an app or game, and a combination of recognizable features with your own ideas.

### 7 Version badges

<img src="images/quality-7-version-badges.png" alt="7 Version badges" height="200" />

Use one of our version badges to highlight a separate version of an app **only** if the original icons are indistinguishable. For instance, it could be nightly builds or paid apps with a free one available. Keep in mind that cases such as Opera Mini or Firefox Klar are different.

Tips: cut lines around the badge of `8px` stroke, place it in the lower right corner when possible, and don't shift icons for it.

### 8 Minimal generic icons

<img src="images/quality-8-minimal-generic-icons.png" alt="8 Minimal icons" height="200" />

Minimal generic icons need distinctive features to aid recognition.
