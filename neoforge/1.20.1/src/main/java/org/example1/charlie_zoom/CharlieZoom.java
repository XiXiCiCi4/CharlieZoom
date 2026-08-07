package org.example1.charlie_zoom;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.example1.charlie_zoom.client.ZoomState;
import org.lwjgl.glfw.GLFW;

/**
 * Charlie Zoom锛團orge 1.20.1~1.20.4 / 1.20.6锛? *
 * 鍔熻兘锛? * - 鎸変綇 C 閿紙鍙湪鎸夐敭缁戝畾涓慨鏀癸級鏀惧ぇ瑙嗛噹锛孎OV 骞虫粦杩囨浮鍒扮洰鏍囧€硷紙绾?0.1~0.2s锛? * - 鏀惧ぇ鏃舵粴鍔ㄦ粴杞皟鏁寸缉鏀惧€嶆暟锛堢洰鏍囪閲?卤10掳锛岃寖鍥?10掳~170掳锛? * - 鏀惧ぇ鏈熼棿榧犳爣鐏垫晱搴︽寜 鍘烣OV/褰撳墠FOV 姣斾緥鍚屾闄嶄綆锛堢敱 MouseHandlerMixin 瀹炵幇锛? */
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
        // MOD 浜嬩欢鎬荤嚎锛氭敞鍐屾寜閿粦瀹?        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onRegisterKeyMappings);
        // 涓讳簨浠舵€荤嚎锛歵ick / 婊氳疆 / FOV
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


