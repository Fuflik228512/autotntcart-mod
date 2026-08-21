package com.shadowslice.autotntcart.network;

import net.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.chat.Component;

public class AutoTntCartReceiver {
    
    public static void register() {
        PayloadTypeRegistry.playC2S().register(AutoTntCartPayload.ID, AutoTntCartPayload.CODEC);

        ServerPlayNetworking.registerReceiver(AutoTntCartPayload.ID, (payload, context) -> {
            context.server().execute(() -> {
                boolean isEnabled = payload.enabled();
                context.player().sendSystemMessage(
                    Component.literal("§6[AutoTntCart] §fРежим изменен на: " + (isEnabled ? "§aВКЛ" : "§cВЫКЛ"))
                );
            });
        });
    }
}