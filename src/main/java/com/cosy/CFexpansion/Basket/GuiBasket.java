package com.cosy.CFexpansion.Basket;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiBasket extends GuiContainer {
    //tekstura hoppera
    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/hopper.png");

    public GuiBasket(ContainerBasket container) {
        super(container);
        this.xSize = 176;
        this.ySize = 133;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        //nazwa gui
        this.fontRendererObj.drawString("Basket", 8, 6, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURE);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        //"rysowanie" tekstury
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}