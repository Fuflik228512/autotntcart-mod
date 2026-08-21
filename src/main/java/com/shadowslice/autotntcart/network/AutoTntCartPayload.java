package com.shadowslice.autotntcart.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.protocol.common.custom.CustomPayload;
import net.minecraft.resources.ResourceLocation;

public record AutoTntCartPayload(boolean enabled) implements CustomPayload {

    public static final Id<AutoTntCartPayload> ID = new Id<>(ResourceLocation.fromIdNamespaceAndPath("autotntcart", "toggle"));
    
    public static final PacketCodec<RegistryFriendlyByteBuf, AutoTntCartPayload> CODEC = PacketCodec.of(
            (value, buf) -> buf.writeBoolean(value.enabled()),
            buf -> new AutoTntCartPayload(buf.readBoolean())
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}