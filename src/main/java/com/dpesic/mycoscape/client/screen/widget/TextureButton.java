package com.dpesic.mycoscape.client.screen.widget;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.InputWithModifiers;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class TextureButton extends AbstractButton {

    private final Identifier texture;
    private final Runnable onClick;
    private static final int TEX_W = 33;
    private static final int TEX_H = 60; // 4 modes * 15 tall each

    private final java.util.function.IntSupplier stateSupplier;

    public TextureButton(
            int x, int y, int w, int h,
            Component label,
            Identifier texture,
            Runnable onClick,
            java.util.function.IntSupplier stateSupplier
    ) {
        super(x, y, w, h, label);
        this.texture = texture;
        this.onClick = onClick;
        this.stateSupplier = stateSupplier;
    }

    @Override
    public void onPress(InputWithModifiers input) {
        this.onClick.run();
    }


    @Override
    protected void updateWidgetNarration(NarrationElementOutput out) {
        out.add(NarratedElementType.TITLE, this.getMessage());

        if (this.active) {
            out.add(NarratedElementType.USAGE, Component.translatable("narration.button.usage"));
        }
    }

    @Override
    protected void renderContents(GuiGraphics g, int mouseX, int mouseY, float partialTick) {

        int mode = Math.max(0, this.stateSupplier.getAsInt()); // 0 or 1
        int v = mode * 30 + (this.isHovered()? 15: 0);

        g.blit(
                RenderPipelines.GUI_TEXTURED,
                this.texture,
                this.getX(), this.getY(),
                0, v,
                this.getWidth(), this.getHeight(),
                TEX_W, TEX_H
        );
    }


}
