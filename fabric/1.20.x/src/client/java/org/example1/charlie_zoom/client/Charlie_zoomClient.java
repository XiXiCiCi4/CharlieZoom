package org.example1.charlie_zoom.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Charlie_zoomClient implements ClientModInitializer {

    /** 一键放大按键：默认 C，可在"选项 → 控制 → 按键绑定"中修改。 */
    public static final KeyBinding ZOOM_KEY = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                    "key.charlie_zoom.zoom",
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_C,
                    "category.charlie_zoom"
            )
    );

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client ->
                ZoomState.INSTANCE.setZooming(client.player != null && ZOOM_KEY.isPressed())
        );
    }
}
