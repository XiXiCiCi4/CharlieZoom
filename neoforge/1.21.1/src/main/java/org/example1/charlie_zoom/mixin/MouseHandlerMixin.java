package org.example1.charlie_zoom.mixin;

import net.minecraft.client.MouseHandler;
import org.example1.charlie_zoom.client.ZoomState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * 缩放时在 turnPlayer 计算前把鼠标增量除以缩放比例，使实际视角旋转速度
 * 精确变为 原视野/当前视野 分之一（动画过程中同步平滑变化）。
 *
 * 字段会在本帧末尾由原版清零，不会残留到下一帧。
 *
 * NeoForge 1.20.2+ 运行时直接使用 mojmap 名，无需 refmap 重映射，
 * 因此所有注解关闭 remap。
 */
@Mixin(MouseHandler.class)
public class MouseHandlerMixin {

    @Shadow
    private double accumulatedDX;

    @Shadow
    private double accumulatedDY;

    @Inject(method = "turnPlayer", at = @At("HEAD"), remap = false)
    private void charlieZoom$zoomMouse(CallbackInfo ci) {
        if (ZoomState.INSTANCE.isZooming()) {
            double scale = ZoomState.INSTANCE.getSensitivityScale();
            this.accumulatedDX /= scale;
            this.accumulatedDY /= scale;
        }
    }
}
