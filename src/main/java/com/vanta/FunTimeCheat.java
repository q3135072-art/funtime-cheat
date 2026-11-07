package com.vanta;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import com.vanta.modules.KillAura;

public class FunTimeCheat implements ClientModInitializer {
    public static boolean reachEnabled = true;
    public static boolean killAuraEnabled = false;
    
    private static KeyBinding killAuraKey;
    private static KeyBinding reachKey;

    @Override
    public void onInitializeClient() {
        killAuraKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.funtimecheat.killaura", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "category.funtimecheat"
        ));
        
        reachKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.funtimecheat.reach", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F, "category.funtimecheat"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            
            while (killAuraKey.wasPressed()) {
                killAuraEnabled = !killAuraEnabled;
                client.player.sendMessage(net.minecraft.text.Text.literal("[V] KillAura: " + (killAuraEnabled ? "ON" : "OFF")), true);
            }
            while (reachKey.wasPressed()) {
                reachEnabled = !reachEnabled;
                client.player.sendMessage(net.minecraft.text.Text.literal("[V] Reach: " + (reachEnabled ? "ON" : "OFF")), true);
            }
            
            if (killAuraEnabled) {
                KillAura.tick(client);
            }
        });
    }
}
