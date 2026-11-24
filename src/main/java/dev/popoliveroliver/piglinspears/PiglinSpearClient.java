package dev.popoliveroliver.piglinspears;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public final class PiglinSpearClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!world.isClient() || hand != Hand.MAIN_HAND || !player.isSneaking()
                    || !(entity instanceof ZombifiedPiglinEntity)) {
                return ActionResult.PASS;
            }

            MinecraftClient.getInstance().setScreen(new GiveItemScreen(entity.getId()));
            return ActionResult.SUCCESS;
        });
    }
}
