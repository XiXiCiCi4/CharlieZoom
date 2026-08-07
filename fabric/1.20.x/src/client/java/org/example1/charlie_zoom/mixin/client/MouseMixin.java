package org.example1.charlie_zoom.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.example1.charlie_zoom.client.ZoomState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {

    @Shadow
    private double cursorDeltaX;

    @Shadow
    private double cursorDeltaY;

    /**
     * 缩放时在 updateMouse 计算前把鼠标增量除以缩放比例，使实际视角旋转速度
     * 精确变为 原视野/当前视野 分之一（动画过程中同步平滑变化），对所有分支
     * （平滑相机、望远镜、普通模式）统一生效，且不改动游戏设置。
     *
     * 字段会在本帧 tick 末尾由原版清零（putfield 0），不会残留到下一帧。
     */
    @Inject(method = "updateMouse", at = @At("HEAD"))
    private void charlieZoom$zoomMouse(CallbackInfo ci) {
        if (ZoomState.INSTANCE.isZooming()) {
            double scale = ZoomState.INSTANCE.getSensitivityScale();
            this.cursorDeltaX /= scale;
            this.cursorDeltaY /= scale;
        }
    }

    /**
     * 缩放且未打开 GUI 时，滚轮调整缩放倍数（目标视野 ±10°，范围 10°~170°），
     * 并取消原版滚轮行为（物品栏切换）。
     */
    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void charlieZoom$handleScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (!ZoomState.INSTANCE.isZooming()) {
            return;
        }
        if (MinecraftClient.getInstance().currentScreen != null) {
            return;
        }
        ZoomState.INSTANCE.adjustFovByScroll(vertical);
        ci.cancel();
    }
}
