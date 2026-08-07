# Charlie Zoom 🔍

[English](README_EN.md) | 中文

按住按键（默认 **C**，可在按键绑定中修改）一键放大视野的 Minecraft 客户端模组，支持 **Fabric / Forge / NeoForge**。

## ✨ 功能特性

- **一键缩放** — 按住 C 放大，松开平滑恢复（按键可在原版按键设置中修改）
- **平滑过渡** — FOV 采用缓出动画（先快后慢），约 **0.1~0.2 秒**完成
- **滚轮调节倍数** — 放大状态下滚动滚轮调整目标视野，每格 **10°**（上滚放大、下滚缩小），默认 **30°**
- **超大缩放范围** — 从 **10°**（接近望远镜）到 **170°**（超广角），**突破原版 30~110° 的限制**
- **灵敏度联动** — 放大期间鼠标灵敏度按 `原FOV / 当前FOV` 比例自动降低，瞄准更精准；动画过程中同步平滑变化，**永不修改游戏设置**
- **所有视角生效** — 第一人称与第三人称

## 🎮 操作

| 操作 | 按键 |
| --- | --- |
| 按住缩放 | **C**（可修改） |
| 缩放中调节倍数 | 鼠标滚轮（每格 10°） |

## 📦 支持的加载器与版本

目录按 `加载器/版本线` 组织，每个目录是一个独立可构建的 mod 项目：

```
fabric/     Fabric Loader + Fabric API
  ├─ 1.20.x/           Minecraft 1.20.1 ~ 1.20.6   (Java 17)
  ├─ 1.21.1-1.21.8/    Minecraft 1.21.1 ~ 1.21.8   (Java 21)
  └─ 1.21.9+/          Minecraft 1.21.9 ~ 1.21.11  (Java 21)
forge/      Forge
  └─ 1.20.1-1.20.5/    Minecraft 1.20.1 ~ 1.20.4 + 1.20.6（Forge 官方没有 1.20.5）
neoforge/   NeoForge
  ├─ 1.20.1/           Minecraft 1.20.1
  ├─ 1.20.2-1.20.4/    Minecraft 1.20.2 ~ 1.20.4
  ├─ 1.20.5-1.20.6/    Minecraft 1.20.5 ~ 1.20.6
  └─ 1.21.1/           Minecraft 1.21.1
```

> 说明：NeoForge 的客户端事件 API 在 1.20.1 / 1.20.2 / 1.20.5 / 1.21.2+ 之间多次重构，无法像 Fabric 那样用单个 jar 覆盖一大段版本，因此按 API 兼容的小版本段拆分。

## 📥 安装

1. 安装对应的模组加载器（[Fabric](https://fabricmc.net/)、[Forge](https://files.minecraftforge.net/) 或 [NeoForge](https://neoforged.net/)）
2. **Fabric** 需要额外安装 [Fabric API](https://modrinth.com/mod/fabric-api)
3. 将模组 jar 放入 `.minecraft/mods/`
4. 启动游戏，按住 **C** 即可放大！

> 正式发布版 jar 请从 [Releases](../../releases) 页面下载。

## 🔨 构建

每个目录独立构建（1.20.x 用 JDK 17+，1.21.x 用 JDK 21+）：

```bat
cd fabric\1.21.1-1.21.8
gradlew.bat build
```

产物在各自 `build/libs/` 下。

- Gradle 发行版走华为云镜像（`gradle/wrapper/gradle-wrapper.properties`）
- Fabric 依赖解析已加入阿里云 Maven 镜像
- Forge / NeoForge 依赖来自 maven.minecraftforge.net / maven.neoforged.net

## 📁 代码结构（以 Fabric 1.21.1 为例）

| 文件 | 作用 |
| --- | --- |
| `client/.../Charlie_zoomClient.java` | 注册按键绑定，tick 时同步缩放状态 |
| `client/.../ZoomState.java` | 缩放状态、目标/当前 FOV、平滑动画、滚轮调整 |
| `mixin/client/GameRendererMixin.java` | 缩放时用动画 FOV 覆盖原版 getFov（Fabric） |
| `mixin/client/MouseMixin.java` | 缩放时把鼠标增量除以缩放比例（Fabric） |
| `CharlieZoom.java` | Forge/NeoForge 入口：事件（FOV/滚轮/tick/按键注册） |
| `mixin/MouseHandlerMixin.java` | 缩放时把鼠标增量除以缩放比例（Forge/NeoForge） |

## 📄 许可证

[MIT](LICENSE.txt)
