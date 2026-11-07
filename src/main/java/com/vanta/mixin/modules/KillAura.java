package com.vanta.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import java.util.Comparator;
import java.util.List;

public class KillAura {
    private static int cooldown = 0;

    public static void tick(MinecraftClient client) {
        if (client.player == null || client.world == null) return;
        if (client.player.isUsingItem()) return;
        
        if (cooldown > 0) {
            cooldown--;
            return;
        }

        List<Entity> targets = client.world.getEntitiesByClass(Entity.class, 
                client.player.getBoundingBox().expand(3.8), 
                e -> (e instanceof LivingEntity) && e != client.player && e.isAlive() && !(e instanceof PlayerEntity && ((PlayerEntity)e).isCreative()));

        if (targets.isEmpty()) return;

        Entity target = targets.stream().min(Comparator.comparingDouble(e -> e.distanceTo(client.player))).orElse(null);
        
        if (target != null) {
            client.interactionManager.attackEntity(client.player, target);
            client.player.swingHand(Hand.MAIN_HAND);
            cooldown = 4; 
        }
    }
}
