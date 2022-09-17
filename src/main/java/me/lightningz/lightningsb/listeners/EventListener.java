package me.lightningz.lightningsb.listeners;

import net.minecraft.client.gui.GuiScreen;

public class EventListener {
    private GuiScreen toOpen;

    public void openGui(GuiScreen guiScreen) {
        toOpen = guiScreen;
    }
}
