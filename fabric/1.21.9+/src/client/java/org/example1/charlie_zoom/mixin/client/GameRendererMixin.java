package org.example1.charlie_zoom.mixin.client;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.example1.charlie_zoom.client.ZoomState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    /**
     * 缩放时用平滑动画视野替代原版 FOV（约 0.1~0.2s 缓动到目标值，先快后慢）；
     * 非缩放时返回原版视野并记录，供灵敏度缩放与下次缩放起点使用。
     *
     * 兼容性说明：getFov 的返回类型在 1.21.1 为 double、1.21.2+ 为 float，
     * 因此这里使用 raw CallbackInfoReturnable + 运行时类型分派，跨版本可用。
     */
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    @SuppressWarnings("rawtypes")
    private void charlieZoom$handleZoomFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable cir) {
        if (ZoomState.INSTANCE.isZooming()) {
            ZoomState.INSTANCE.updateAnimation();
            if (cir.getReturnValue() instanceof Double) {
                cir.setReturnValue(ZoomState.INSTANCE.getCurrentFov());
            } else {
                cir.setReturnValue((float) ZoomState.INSTANCE.getCurrentFov());
            }
        } else if (cir.getReturnValue() instanceof Double d) {
            ZoomState.INSTANCE.setLastFov(d);
        } else if (cir.getReturnValue() instanceof Float f) {
            ZoomState.INSTANCE.setLastFov(f);
        }
    }
}
