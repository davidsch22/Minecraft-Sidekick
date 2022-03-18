package gr3enmachin3.rosiemod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(RosieMod.MOD_ID)
public class RosieMod
{
    public static final String MOD_ID = "rosiemod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RosieMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::loaded);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
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
