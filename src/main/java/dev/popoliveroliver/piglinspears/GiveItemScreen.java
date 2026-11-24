package dev.popoliveroliver.piglinspears;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class GiveItemScreen extends Screen {
    private final int piglinEntityId;

    public GiveItemScreen(int piglinEntityId) {
        super(Text.literal("Give item to zombified piglin"));
        this.piglinEntityId = piglinEntityId;
    }

    @Override
    protected void init() {
        int buttonWidth = 120;
        int buttonX = (this.width - buttonWidth) / 2;
        int buttonY = this.height / 2 + 12;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Give held item"), button -> {
            ClientPlayNetworking.send(new GiveItemPayload(this.piglinEntityId));
            this.close();
        }).dimensions(buttonX, buttonY, buttonWidth, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Cancel"), button -> this.close())
                .dimensions(buttonX, buttonY + 26, buttonWidth, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title,
                this.width / 2, this.height / 2 - 18, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer,
                Text.literal("Transfers one item from your main hand."),
                this.width / 2, this.height / 2 - 4, 0xAAAAAA);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
