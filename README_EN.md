# Charlie Zoom 🔍

English | [中文](README.md)

A lightweight **client-side** Minecraft mod that gives you a zoom key — just like OptiFine's zoom, but as a standalone mod for **Fabric**, **Forge** and **NeoForge**.

## ✨ Features

- **One-key zoom** — hold **C** to zoom in, release to ease back (key is rebindable in vanilla key bind settings)
- **Smooth transition** — FOV animates with an ease-out curve (fast first, then slowing), finishing in about **0.1–0.2 seconds**
- **Scroll-wheel zoom level** — while zooming, scroll the mouse wheel to adjust the target FOV in **10° steps** (scroll up = zoom in, scroll down = zoom out), **default 30°**
- **Wide zoom range** — from **10°** (spyglass-like) up to **170°** (ultra-wide), going **beyond Minecraft's vanilla 30–110° limit**
- **Sensitivity linked to zoom** — mouse speed automatically scales by `original FOV / current FOV` for precise aiming; it also eases smoothly during the transition, and your game settings are **never modified**
- **All perspectives** — works in first person and third person

## 🎮 Controls

| Action | Key |
| --- | --- |
| Hold to zoom | **C** (rebindable) |
| Adjust zoom level while zooming | Mouse wheel (10° per step) |

## 📦 Supported Platforms

The repository is organized by `loader/version-line`; each directory is an independently buildable mod project:

```
fabric/     Fabric Loader + Fabric API
  ├─ 1.20.x/           Minecraft 1.20.1 ~ 1.20.6   (Java 17)
  ├─ 1.21.1-1.21.8/    Minecraft 1.21.1 ~ 1.21.8   (Java 21)
  └─ 1.21.9+/          Minecraft 1.21.9 ~ 1.21.11  (Java 21)
forge/      Forge
  └─ 1.20.1-1.20.5/    Minecraft 1.20.1 ~ 1.20.4 + 1.20.6 (no official Forge for 1.20.5)
neoforge/   NeoForge
  ├─ 1.20.1/           Minecraft 1.20.1
  ├─ 1.20.2-1.20.4/    Minecraft 1.20.2 ~ 1.20.4
  ├─ 1.20.5-1.20.6/    Minecraft 1.20.5 ~ 1.20.6
  └─ 1.21.1/           Minecraft 1.21.1
```

> Note: NeoForge's client event API was reworked several times between 1.20.1 / 1.20.2 / 1.20.5 / 1.21.2+, so unlike Fabric, one jar cannot cover a large range of versions. The NeoForge builds are split by API-compatible minor versions.

## 📥 Installation

1. Install the matching mod loader ([Fabric](https://fabricmc.net/), [Forge](https://files.minecraftforge.net/), or [NeoForge](https://neoforged.net/))
2. For **Fabric**, also install [Fabric API](https://modrinth.com/mod/fabric-api)
3. Drop the mod jar into `.minecraft/mods/`
4. Launch the game and hold **C** to zoom!

> Download release jars from the [Releases](../../releases) page.

## 🔨 Building

Each directory builds independently (JDK 17+ for 1.20.x, JDK 21+ for 1.21.x):

```bat
cd fabric\1.21.1-1.21.8
gradlew.bat build
```

Artifacts land in each project's `build/libs/`.

- Gradle distributions are downloaded from the Huawei Cloud mirror (`gradle/wrapper/gradle-wrapper.properties`)
- Fabric dependency resolution uses the Aliyun Maven mirror
- Forge / NeoForge dependencies come from maven.minecraftforge.net / maven.neoforged.net

## 📁 Code Structure (Fabric 1.21.1 as example)

| File | Purpose |
| --- | --- |
| `client/.../Charlie_zoomClient.java` | Registers the key binding, syncs zoom state each tick |
| `client/.../ZoomState.java` | Zoom state, target/current FOV, smooth animation, scroll adjustment |
| `mixin/client/GameRendererMixin.java` | Overrides vanilla `getFov` with the animated FOV while zooming (Fabric) |
| `mixin/client/MouseMixin.java` | Divides mouse delta by the zoom scale (Fabric) |
| `CharlieZoom.java` | Forge/NeoForge entry point: events (FOV / scroll / tick / key registration) |
| `mixin/MouseHandlerMixin.java` | Divides mouse delta by the zoom scale (Forge/NeoForge) |

## 📄 License

[MIT](LICENSE.txt)
