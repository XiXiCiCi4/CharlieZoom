package org.example1.charlie_zoom.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Constructor;

public class Charlie_zoomClient implements ClientModInitializer {

    /** 一键放大按键：默认 C，可在"选项 → 控制 → 按键绑定"中修改。 */
    public static final KeyBinding ZOOM_KEY = registerZoomKey();

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client ->
                ZoomState.INSTANCE.setZooming(client.player != null && ZOOM_KEY.isPressed())
        );
    }

    /**
     * 注册按键绑定。
     *
     * 1.21.9+ 的 KeyBinding 构造签名随版本变化，需要反射兼容：
     * - 1.21.9 / 1.21.10：(String, InputUtil.Type, int, KeyBinding$Category)
     * - 1.21.11+：(String, InputUtil.Type, int, KeyBinding$Category, int)
     * 分类统一用 KeyBinding$Category.create(String) 创建。
     */
    private static KeyBinding registerZoomKey() {
        try {
            Class<?> keyBindingClass = Class.forName("net.minecraft.client.option.KeyBinding");
            Class<?> categoryClass = Class.forName("net.minecraft.client.option.KeyBinding$Category");
            Object category = categoryClass.getMethod("create", String.class)
                    .invoke(null, "category.charlie_zoom");

            try {
                Constructor<?> ctor = keyBindingClass.getConstructor(
                        String.class, InputUtil.Type.class, int.class, categoryClass);
                return (KeyBinding) ctor.newInstance(
                        "key.charlie_zoom.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, category);
            } catch (NoSuchMethodException e) {
                Constructor<?> ctor = keyBindingClass.getConstructor(
                        String.class, InputUtil.Type.class, int.class, categoryClass, int.class);
                return (KeyBinding) ctor.newInstance(
                        "key.charlie_zoom.zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, category, 0);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Charlie Zoom: 无法注册按键绑定", e);
        }
    }
}
