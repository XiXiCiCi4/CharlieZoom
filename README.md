# Charlie Zoom

按住按键（默认 **C**，可在按键绑定中修改）一键放大视野的 Minecraft 客户端模组。

- **滚轮调倍数**：放大状态下滚动滚轮调整缩放倍数（目标视野每格 ±10°，范围 **10°~170°**，突破原版 30~110 限制；上滚放大、下滚缩小，默认 30°）
- **平滑缩放**：FOV 指数缓动（先快后慢，约 0.1~0.2s 完成过渡）
- **灵敏度联动**：放大期间鼠标灵敏度按 `原FOV / 当前FOV` 的比例同步降低（动画过程中同步平滑变化），不修改游戏设置

## 支持的加载器与版本

目录按 `加载器/版本线` 组织，每个目录是一个独立可构建的 mod 项目：

```
fabric/     Fabric Loader + Fabric API
  ├─ 1.20.x/           Minecraft 1.20.1 ~ 1.20.6   (Java 17)
  ├─ 1.21.1-1.21.8/    Minecraft 1.21.1 ~ 1.21.8   (Java 21)
  └─ 1.21.9+/          Minecraft 1.21.9 ~ 1.21.11  (Java 21)
forge/      Forge
  └─ 1.20.1-1.20.5/    Minecraft 1.20.1 ~ 1.20.4 + 1.20.6（Forge 官方没有 1.20.5）
neoforge/   NeoForge
  ├─ 1.20.1/           Minecraft 1.20.1            (ModDevGradle legacyforge)
  ├─ 1.20.2-1.20.4/    Minecraft 1.20.2 ~ 1.20.4
  ├─ 1.20.5-1.20.6/    Minecraft 1.20.5 ~ 1.20.6
  └─ 1.21.1/           Minecraft 1.21.1
```

> 说明：NeoForge 的客户端事件 API 在 1.20.1 / 1.20.2 / 1.20.5 / 1.21.2+ 之间多次重构（包名与事件类变化），因此无法像 Fabric 那样用单个 jar 覆盖一大段版本；这里按 API 兼容的小版本段拆分。
> 构建工具链：1.20.1 用 ModDevGradle `legacyforge 2.0.91`（即 Forge 1.20.1 的 artifact，NeoForge 1.20.1 与其二进制兼容）；1.20.2-1.20.4 用 ModDevGradle `2.0.91` 以 NeoForge 20.4.251 构建（NeoForge 1.20.2/1.20.3 太老、未发布新版 ModDevGradle 所需的 `moddev-bundle` 变体，改用 API 相同的 1.20.4 构建并在 mods.toml 声明 `>=1.20.2 <1.20.5`）；1.20.5-1.20.6 与 1.21.1 用 ModDevGradle `0.1.131`。
> NeoForge 1.20.2+ 运行时使用 mojmap 名，mixin 注解全部 `remap = false`、无需 refmap。

## 构建

每个目录独立构建（需要对应 JDK：1.20.x 用 JDK 17+，1.21.x 用 JDK 21+）：

```bat
cd fabric\1.21.1-1.21.8
gradlew.bat build
```

产物在各自 `build/libs/` 下，放入 `.minecraft/mods` 即可。

- Gradle 发行版走华为云镜像（各项目 `gradle/wrapper/gradle-wrapper.properties`）
- Fabric 依赖解析已加入阿里云 Maven 镜像
- Forge / NeoForge 依赖来自 maven.minecraftforge.net / maven.neoforged.net

## 代码结构（以 Fabric 1.21.1 为例）

| 文件 | 作用 |
| --- | --- |
| `client/.../Charlie_zoomClient.java` | 注册按键绑定，tick 时同步缩放状态 |
| `client/.../ZoomState.java` | 缩放状态、目标/当前 FOV、平滑动画、滚轮调整 |
| `mixin/client/GameRendererMixin.java` | 缩放时用动画 FOV 覆盖原版 getFov（Fabric） |
| `mixin/client/MouseMixin.java` | 缩放时把鼠标增量除以缩放比例（Fabric） |
| `CharlieZoom.java` | Forge/NeoForge 入口：事件（FOV/滚轮/tick/按键注册） |
| `mixin/MouseHandlerMixin.java` | 缩放时把鼠标增量除以缩放比例（Forge/NeoForge） |

Forge/NeoForge 版用官方事件 `ViewportEvent.ComputeFov`（FOV）与 `InputEvent.MouseScrolled`（滚轮），只有鼠标灵敏度需要 mixin。

## 鸣谢

- 模组图标为脚本生成的简笔放大镜。
