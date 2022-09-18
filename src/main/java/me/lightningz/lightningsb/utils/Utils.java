package me.lightningz.lightningsb.utils;

import me.lightningz.lightningsb.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Utils {
    public static int getGuiLeft() {
        int savedGuiLeft = Main.INSTANCE.getConfig().OverlayConfig.guiLeft;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        return Main.INSTANCE.getConfig().OverlayConfig.relativeGui ? Math.round(sr.getScaledWidth() * (((float) savedGuiLeft) / 1000)) : savedGuiLeft;
    }
    public static int getGuiTop() {
        int savedGuiTop = Main.INSTANCE.getConfig().OverlayConfig.guiTop;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        return Main.INSTANCE.getConfig().OverlayConfig.relativeGui ? Math.round(sr.getScaledHeight() * (((float) savedGuiTop) / 1000)) : savedGuiTop;
    }

    public static float getScaleFactor() {
        float savedSf = Main.INSTANCE.getConfig().OverlayConfig.guiScale;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        return Main.INSTANCE.getConfig().OverlayConfig.relativeGui ? (savedSf * sr.getScaledWidth()) / 255f : savedSf;

    }
}
