package org.example1.charlie_zoom;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.example1.charlie_zoom.client.ZoomState;
import org.lwjgl.glfw.GLFW;

/**
 * Charlie Zoom（NeoForge 1.20.1）
 *
 * 功能：
 * - 按住 C 键（可在按键绑定中修改）放大视野，FOV 平滑过渡到目标值（约 0.1~0.2s）
 * - 放大时滚动滚轮调整缩放倍数（目标视野 ±10°，范围 10°~170°）
 * - 放大期间鼠标灵敏度按 原FOV/当前FOV 比例同步降低（由 MouseHandlerMixin 实现）
 */
@Mod(CharlieZoom.MOD_ID)
public class CharlieZoom {

    public static final String MOD_ID = "charlie_zoom";

    public static final KeyMapping ZOOM_KEY = new KeyMapping(
            "key.charlie_zoom.zoom",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "category.charlie_zoom"
    );

    public CharlieZoom() {
        // MOD 事件总线：注册按键绑定
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onRegisterKeyMappings);
        // 主事件总线：tick / 滚轮 / FOV
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ZOOM_KEY);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        Minecraft mc = Minecraft.getInstance();
        ZoomState.INSTANCE.setZooming(mc.player != null && ZOOM_KEY.isDown());
    }

    @SubscribeEvent
    public void onMouseScroll(InputEvent.MouseScrollingEvent event) {
        if (ZoomState.INSTANCE.isZooming() && Minecraft.getInstance().screen == null) {
            ZoomState.INSTANCE.adjustFovByScroll(event.getScrollDelta());
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onComputeFov(ViewportEvent.ComputeFov event) {
        if (ZoomState.INSTANCE.isZooming()) {
            ZoomState.INSTANCE.updateAnimation();
            event.setFOV(ZoomState.INSTANCE.getCurrentFov());
        } else {
            ZoomState.INSTANCE.setLastFov(event.getFOV());
        }
    }
}
