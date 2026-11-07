package com.vanta.mixin;

import com.vanta.FunTimeCheat;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class PlayerReachMixin {

    @Inject(method = "getReachDistance()F", at = @At("HEAD"), cancellable = true)
    private void onGetReachDistance(CallbackInfoReturnable<Float> cir) {
        if (FunTimeCheat.reachEnabled) {
            cir.setReturnValue(3.8f);
        }
    }
}
