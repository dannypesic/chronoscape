package com.dpesic.mycoscape.client.screen;

import com.dpesic.mycoscape.inventory.BiogeneratorMenu;
import com.dpesic.mycoscape.client.screen.widget.TextureButton;

import com.dpesic.mycoscape.network.ToggleBiogeneratorPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;

public class BiogeneratorScreen extends AbstractContainerScreen<BiogeneratorMenu> {

    public BiogeneratorScreen(BiogeneratorMenu menu, Inventory playerInv, Component title) {
        super(menu, playerInv, title);
        imageWidth = 176;
        imageHeight = 166;
    }
    private static final Identifier BTN_TEX =
            Identifier.fromNamespaceAndPath("mycoscape", "textures/gui/biogenerator_buttons.png");

    private static final Identifier BG_TEXTURE =
            Identifier.fromNamespaceAndPath("mycoscape", "textures/gui/biogenerator.png");

    private static final Identifier NECRO_TEXTURE =
            Identifier.fromNamespaceAndPath("mycoscape", "container/biogenerator/biogenerator_necro");
    private static final Identifier FUEL_TANK_TEXTURE =
            Identifier.fromNamespaceAndPath("mycoscape", "container/biogenerator/biogenerator_fuel_tank");

    public void init() {
        super.init();
        titleLabelX = (imageWidth - font.width(title)) / 2;
        int x = leftPos + 26;
        int y = topPos + 17;
        addRenderableWidget(new TextureButton(
                x, y, 33, 15,
                Component.literal("Toggle"),
                BTN_TEX,
                () -> {
                    ClientPacketDistributor.sendToServer(
                            new ToggleBiogeneratorPacket(menu.getBlockPos())
                    );
                },
                () -> menu.isToggled() ? 0 : 1   // ContainerData-driven mode, example
        ));
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {

        graphics.blit(RenderPipelines.GUI_TEXTURED, BG_TEXTURE, leftPos, topPos, 0.0F, 0.0F, imageWidth, imageHeight, 256, 256);

        int necroProgressHeight = Mth.ceil(menu.getNecroProgress() * 14.0F);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, NECRO_TEXTURE, 15, 14, 0, 14 - necroProgressHeight, leftPos + 80, topPos + 38 + 14 - necroProgressHeight, 15, necroProgressHeight);

        int burnProgressHeight = Mth.ceil(menu.getBurnProgress() * 28.0F);
        graphics.blitSprite(RenderPipelines.GUI_TEXTURED, FUEL_TANK_TEXTURE, 9, 28, 0, 28 - burnProgressHeight, leftPos + 121, topPos + 30 + 28 - burnProgressHeight, 9, burnProgressHeight);

    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBg(graphics, partialTick, mouseX, mouseY);
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
    }

}
