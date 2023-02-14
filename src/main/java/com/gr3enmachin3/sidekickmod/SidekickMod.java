package com.gr3enmachin3.sidekickmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SidekickMod.MOD_ID)
public class SidekickMod
{
    public static final String MOD_ID = "sidekickmod";
    public static final Logger LOGGER = LogManager.getLogger();

    public SidekickMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loaded);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Preinit code
    }

    private void loaded(final FMLLoadCompleteEvent event) {
        LOGGER.info("Rosie Mod successfully loaded.");
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // Do client stuff
    }
}
