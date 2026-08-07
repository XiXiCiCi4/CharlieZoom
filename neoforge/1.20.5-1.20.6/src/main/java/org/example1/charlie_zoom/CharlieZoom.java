package org.example1.charlie_zoom;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import org.example1.charlie_zoom.client.ZoomState;
import org.lwjgl.glfw.GLFW;

/**
 * Charlie Zoom锛圢eoForge 1.20.2~1.20.4锛? */
@Mod(CharlieZoom.MOD_ID)
public class CharlieZoom {

    public static final String MOD_ID = "charlie_zoom";

    public static final KeyMapping ZOOM_KEY = new KeyMapping(
            "key.charlie_zoom.zoom",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            "category.charlie_zoom"
    );

    public CharlieZoom(IEventBus modEventBus) {
        modEventBus.addListener(this::onRegisterKeyMappings);
        NeoForge.EVENT_BUS.register(this);
    }

    private void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(ZOOM_KEY);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        ZoomState.INSTANCE.setZooming(mc.player != null && ZOOM_KEY.isDown());
    }

    @SubscribeEvent
    public void onMouseScroll(InputEvent.MouseScrollingEvent event) {
        if (ZoomState.INSTANCE.isZooming() && Minecraft.getInstance().screen == null) {
            ZoomState.INSTANCE.adjustFovByScroll(event.getScrollDeltaY());
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

