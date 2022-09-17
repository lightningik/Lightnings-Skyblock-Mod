package me.lightningz.lightningsb.overlays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class Overlay extends GuiScreen {

    private int guiLeft, guiTop;
    private int offsetX, offsetY;
    private float guiScale;
    private boolean isDragging;



    @Override
    public void initGui() {
        buttonList.add(new GuiButton(0, 10, height-90, "Test"));
        super.initGui();
    }

    public void calculateOffset(int mouseX, int mouseY) {
        offsetX = guiLeft - mouseX;
        offsetY = guiTop - mouseY;
    }

    public void tick(int mouseX, int mouseY) {
        int lastGuiLeft = guiLeft, lastGuiTop = guiTop;
        guiLeft = Math.round(Math.max(Math.min(isDragging ? mouseX + offsetX : guiLeft, width - 256 * guiScale), 0));
        guiTop = Math.round(Math.max(Math.min(isDragging ? mouseY + offsetY : guiTop, height - 256 * guiScale), 0));

        if (wouldRenderOutOfBoundsX(guiLeft, guiScale)) {
            guiLeft = lastGuiLeft;
            calculateOffset(mouseX, mouseY);
        }
        if (wouldRenderOutOfBoundsY(guiTop, guiScale)) {
            guiTop = lastGuiTop;
            calculateOffset(mouseX, mouseY);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.color(0.3f, 0.3f, 0.3f, 0.5f);
        drawTexturedModalRect((width-176)/2, (height-222)/2, 0, 0, 176, 222);
        GlStateManager.disableBlend();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.popMatrix();
        // main editor
        GlStateManager.pushMatrix();
        GlStateManager.translate(guiLeft, guiTop, 0);
        GlStateManager.scale(guiScale, guiScale, guiScale);
        tick(mouseX, mouseY);
        drawTexturedModalRect(96, 164, 0, 32, 64, 64);
        drawTexturedModalRect(96, 32, 64, 32, 64, 64);
        drawCenteredString(fontRendererObj, "Drag To Move", 128, 124, 0x00ff00);
        GlStateManager.popMatrix();
        for (GuiButton guiButton : this.buttonList) {
            drawButton(guiButton, this.mc, mouseX, mouseY);
        }

    }

    public static void drawButton(GuiButton button, Minecraft mc, int mouseX, int mouseY) {
        button.drawButton(mc, mouseX, mouseY);
    }

    private boolean wouldRenderOutOfBoundsX(int x, float sf) {
        return (x == 0 || x >= width - 1 - 256 * sf);
    }

    private boolean wouldRenderOutOfBoundsY(int y, float sf) {
        return (y <= 32 * sf || y >= height - 1 - 256 * sf);
    }

}


