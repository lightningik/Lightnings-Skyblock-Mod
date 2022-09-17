package me.lightningz.lightningsb;

import me.lightningz.lightningsb.commands.testCommand;
import me.lightningz.lightningsb.listeners.EventListener;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MODID, version = Main.VERSION, clientSideOnly = true)
public class Main
{
    public static final String MODID = "lightningsb";
    public static final String VERSION = "1.0";
    private EventListener listener;
    public static Main INSTANCE;

    public Main() {
        INSTANCE = this;
    }

    private static class WrongForgeVersion extends RuntimeException {
        public WrongForgeVersion(String message) {
            super(message);
        }
    }

    public EventListener getListener() {
        return listener;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (ForgeVersion.getBuildVersion() != 2318)
            throw new WrongForgeVersion("[LBS] Incorrect forge version" + ForgeVersion.getVersion() + ", Please use this mod on 1.8.9 - 11.15.1.2318");
        ClientCommandHandler.instance.registerCommand(new testCommand());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
}
