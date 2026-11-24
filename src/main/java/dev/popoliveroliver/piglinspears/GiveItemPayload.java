package dev.popoliveroliver.piglinspears;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record GiveItemPayload(int entityId) implements CustomPayload {
    public static final Id<GiveItemPayload> ID = new Id<>(Identifier.of(PiglinSpearMod.MOD_ID, "give_item"));
    public static final PacketCodec<RegistryByteBuf, GiveItemPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.VAR_INT,
            GiveItemPayload::entityId,
            GiveItemPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
