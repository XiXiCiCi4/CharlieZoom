# Modrinth 发布文案（英文）

## 项目名称 (Name)
`Charlie Zoom`

## 一句话简介 (Description —— 发布页的短描述，不带 Markdown)
Zoom in with one key press — smooth FOV transition, scroll-wheel zoom level, and sensitivity that scales with your zoom.

## 完整介绍 (Body —— Markdown)

---

# 🔍 Charlie Zoom

A lightweight **client-side** Minecraft mod that gives you a zoom key — just like OptiFine's zoom, but as a standalone mod for **Fabric**, **Forge** and **NeoForge**.

Hold the zoom key (default: **C**, rebindable in Controls → Key Binds) and your FOV smoothly eases down to a zoomed-in view. Release it and it eases right back.

## ✨ Features

- **One-key zoom** — hold **C** to zoom in, release to zoom out (key is rebindable in vanilla key bind settings)
- **Smooth transition** — FOV animates with an ease-out curve (fast at first, then slowing down), finishing in about **0.1–0.2 seconds**
- **Scroll-wheel zoom level** — while zooming, scroll the mouse wheel to adjust the target FOV in **10° steps** (scroll up = zoom in, scroll down = zoom out), **default 30°**
- **Wide zoom range** — from **10°** (spyglass-like) up to **170°** (ultra-wide), going **beyond Minecraft's vanilla 30–110° limit**
- **Sensitivity linked to zoom** — while zoomed, mouse sensitivity is automatically reduced in proportion to `original FOV / current FOV`, so aiming stays precise. It also scales smoothly during the transition animation. Your game settings are **never modified**
- **Works in all perspectives** — first person and third person

## 🎮 Controls

| Action | Key |
| --- | --- |
| Hold to zoom | **C** (rebindable) |
| Adjust zoom level while zooming | Mouse wheel (10° per step) |

## 📦 Supported Platforms

| Loader | Minecraft versions |
| --- | --- |
| **Fabric** | 1.20.1 – 1.20.6 · 1.21.1 – 1.21.8 · 1.21.9 – 1.21.11 |
| **Forge** | 1.20.1 – 1.20.4 · 1.20.6 |
| **NeoForge** | 1.20.1 · 1.20.2 – 1.20.4 · 1.20.5 – 1.20.6 · 1.21.1 |

> Download the file matching **your** Minecraft version and loader.

## 📥 Installation

1. Install the matching mod loader ([Fabric](https://fabricmc.net/), [Forge](https://files.minecraftforge.net/), or [NeoForge](https://neoforged.net/))
2. For **Fabric**, also install [Fabric API](https://modrinth.com/mod/fabric-api)
3. Drop the mod jar into `.minecraft/mods/`
4. Launch the game and press **C** to zoom!

## ❓ FAQ / Notes

- **Client-side only** — safe to install on your client; servers don't need it (and other players won't see any effect of it)
- **No settings file** — zoom level resets per session; it's all in-game via the scroll wheel
- **Doesn't touch your settings** — sensitivity is applied transiently while zooming and never written to your options
- **Compatible with other zoom methods** — designed to coexist; only overrides the camera FOV and mouse input while you hold the key

## 📄 License

MIT — free to use, modify and redistribute.

---

# Modrinth 发布页字段建议

- **Name**: `Charlie Zoom`
- **Description**: 见上文一句话简介
- **Body**: 见上文 Markdown
- **License**: MIT
- **Categories (标签)**: `utility`, `client-side`（Fabric 还有 `cosmetic`？不，选 utility + client-side 即可）
- **Loader 相关**: 勾选对应的三个加载器
- **游戏版本**: 勾选 1.20.1、1.20.2、1.20.4、1.20.6、1.21.1、1.21.4、1.21.8、1.21.9、1.21.11（按你发布的 jar 实际覆盖）
- **环境 (Environment)**: 勾选 **Client** only

---

# 中文对照版（如想在中文社区使用）

## 一句话简介
一键放大模组：按住按键平滑放大视野，滚轮调节缩放倍数，灵敏度随缩放自动降低。

## 完整介绍

# 🔍 Charlie Zoom

轻量的**客户端** Minecraft 模组：一键放大（类似 OptiFine 的缩放），独立支持 **Fabric**、**Forge** 与 **NeoForge**。

按住缩放键（默认 **C**，可在控制 → 按键绑定中修改），FOV 平滑过渡到放大视野；松开后平滑恢复。

## ✨ 功能特性

- **一键缩放** — 按住 C 放大，松开恢复（按键可在原版按键设置中修改）
- **平滑过渡** — FOV 采用缓出动画（先快后慢），约 **0.1~0.2 秒**完成
- **滚轮调节倍数** — 放大状态下滚动滚轮调整目标视野，每格 **10°**（上滚放大、下滚缩小），**默认 30°**
- **超大缩放范围** — 从 **10°**（接近望远镜）到 **170°**（超广角），**突破原版 30~110° 的限制**
- **灵敏度联动** — 放大期间鼠标灵敏度按 `原FOV / 当前FOV` 比例自动降低，瞄准更精准；动画过程中也平滑变化，且**永不修改游戏设置**
- **所有视角生效** — 第一人称与第三人称

## 🎮 操作

| 操作 | 按键 |
| --- | --- |
| 按住缩放 | **C**（可修改） |
| 缩放中调节倍数 | 鼠标滚轮（每格 10°） |

## 📦 支持的加载器与版本

| 加载器 | Minecraft 版本 |
| --- | --- |
| **Fabric** | 1.20.1 – 1.20.6 · 1.21.1 – 1.21.8 · 1.21.9 – 1.21.11 |
| **Forge** | 1.20.1 – 1.20.4 · 1.20.6 |
| **NeoForge** | 1.20.1 · 1.20.2 – 1.20.4 · 1.20.5 – 1.20.6 · 1.21.1 |

> 请下载与你 **Minecraft 版本和加载器匹配** 的文件。

## 📥 安装

1. 安装对应的模组加载器（[Fabric](https://fabricmc.net/)、[Forge](https://files.minecraftforge.net/) 或 [NeoForge](https://neoforged.net/)）
2. **Fabric** 需要额外安装 [Fabric API](https://modrinth.com/mod/fabric-api)
3. 将模组 jar 放入 `.minecraft/mods/`
4. 启动游戏，按住 **C** 即可放大！

## ❓ 常见问题

- **纯客户端模组** — 只需装在客户端；服务器无需安装
- **无需配置文件** — 缩放倍数在游戏内用滚轮调节即可
- **不修改任何设置** — 灵敏度只在放大期间临时生效，不会写入你的游戏选项
- **与其它缩放方式兼容** — 仅在按住按键时覆盖相机 FOV 与鼠标输入

## 📄 许可证

MIT — 可自由使用、修改与分发。
