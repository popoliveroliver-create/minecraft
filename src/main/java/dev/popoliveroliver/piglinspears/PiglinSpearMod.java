package dev.popoliveroliver.piglinspears;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public final class PiglinSpearMod implements ModInitializer {
    public static final String MOD_ID = "piglinspears";

    @Override
    public void onInitialize() {
        PayloadTypeRegistry.playC2S().register(GiveItemPayload.ID, GiveItemPayload.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(GiveItemPayload.ID, (payload, context) -> {
            ServerPlayerEntity player = context.player();
            context.server().execute(() -> giveHeldItem(player, payload.entityId()));
        });
    }

    private static void giveHeldItem(ServerPlayerEntity player, int entityId) {
        if (player.isRemoved() || player.isDead()) return;

        var entity = player.getWorld().getEntityById(entityId);
        if (!(entity instanceof ZombifiedPiglinEntity piglin)) return;
        if (player.squaredDistanceTo(piglin) > 36.0) return;
        if (!piglin.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) return;

        ItemStack held = player.getMainHandStack();
        if (held.isEmpty()) return;

        // Copying the complete stack preserves enchantments, durability, and components.
        // Decrement only after the piglin has received its one item.
        ItemStack transferred = held.copyWithCount(1);
        piglin.equipStack(EquipmentSlot.MAINHAND, transferred);
        held.decrement(1);
    }
}
