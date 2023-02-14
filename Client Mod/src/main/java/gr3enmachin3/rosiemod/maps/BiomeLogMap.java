package gr3enmachin3.rosiemod.maps;

import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.biome.NetherBiomes;
import net.minecraft.data.worldgen.biome.EndBiomes;
import net.minecraft.world.level.biome.Biome;

import java.util.HashMap;
import java.util.Map;

public class BiomeLogMap {
    private final Map<Biome, String> instBiomeLogMap = new HashMap<>();

    private static final Map<Biome, String> biomeLogMap = new BiomeLogMap().instBiomeLogMap;

    private BiomeLogMap() {
        final String OAK = "oak_";
        final String SPRUCE = "spruce_";
        final String BIRCH = "birch_";
        final String DARK = "dark_oak_";
        final String ACACIA = "acacia_";
        final String JUNGLE = "jungle_";
        final String NONE = "none";

        instBiomeLogMap.put(OverworldBiomes.dripstoneCaves(), );
        instBiomeLogMap.put(OverworldBiomes.frozenPeaks(), );
        instBiomeLogMap.put(OverworldBiomes.grove(), );
        instBiomeLogMap.put(OverworldBiomes.jaggedPeaks(), );
        instBiomeLogMap.put(OverworldBiomes.lushCaves(), );
        instBiomeLogMap.put(OverworldBiomes.meadow(), );
        instBiomeLogMap.put(OverworldBiomes.forest(), ); // Old growth birch forest
        instBiomeLogMap.put(OverworldBiomes.oldGrowthTaiga(), ); // Old growth pine taiga
        instBiomeLogMap.put(OverworldBiomes.oldGrowthTaiga(), ); // Old growth spruce taiga
        instBiomeLogMap.put(OverworldBiomes.plains(), ); // Snowy plains
        instBiomeLogMap.put(OverworldBiomes.snowySlopes(), );
        instBiomeLogMap.put(OverworldBiomes.sparseJungle(), );
        instBiomeLogMap.put(OverworldBiomes.stonyPeaks(), );
        instBiomeLogMap.put(OverworldBiomes.beach(), ); // Stony shore
        instBiomeLogMap.put(OverworldBiomes.forest(), ); // Warped forest
        instBiomeLogMap.put(OverworldBiomes.forest(), ); // Windswept forest
        instBiomeLogMap.put(OverworldBiomes.windsweptHills(), ); // Windswept gravelly hills
        instBiomeLogMap.put(OverworldBiomes.windsweptHills(), ); // Windswept hills
        instBiomeLogMap.put(OverworldBiomes.savanna(), ); // Windswept savanna
        instBiomeLogMap.put(OverworldBiomes.badlands(), ); // Wooded badlands
        instBiomeLogMap.put(OverworldBiomes.badlands(), NONE); // Badlands
        instBiomeLogMap.put(OverworldBiomes.bambooJungle(), JUNGLE);
        instBiomeLogMap.put(OverworldBiomes.beach(), NONE); // Beach
        instBiomeLogMap.put(OverworldBiomes.forest(), BIRCH); // Birch forest
        instBiomeLogMap.put(OverworldBiomes.coldOcean(), NONE); // Cold ocean
        instBiomeLogMap.put(OverworldBiomes.darkForest(), DARK);
        instBiomeLogMap.put(OverworldBiomes.coldOcean(), NONE); // Deep cold ocean
        instBiomeLogMap.put(OverworldBiomes.frozenOcean(), NONE); // Deep frozen ocean
        instBiomeLogMap.put(OverworldBiomes.lukeWarmOcean(), NONE); // Deep lukewarm ocean
        instBiomeLogMap.put(OverworldBiomes.ocean(), NONE); // Deep ocean
        instBiomeLogMap.put(OverworldBiomes.desert(), NONE);
        instBiomeLogMap.put(OverworldBiomes.badlands(), NONE); // Eroded badlands
        instBiomeLogMap.put(OverworldBiomes.forest(), OAK); // Flower forest
        instBiomeLogMap.put(OverworldBiomes.forest(), OAK); // Forest
        instBiomeLogMap.put(OverworldBiomes.frozenOcean(), NONE); // Frozen ocean
        instBiomeLogMap.put(OverworldBiomes.river(), SPRUCE); // Frozen river
        instBiomeLogMap.put(?, NONE); // Ice spikes
        instBiomeLogMap.put(OverworldBiomes.jungle(), JUNGLE);
        instBiomeLogMap.put(OverworldBiomes.lukeWarmOcean(), NONE); // Lukewarm ocean
        instBiomeLogMap.put(OverworldBiomes.mushroomFields(), NONE);
        instBiomeLogMap.put(OverworldBiomes.ocean(), NONE); // Ocean
        instBiomeLogMap.put(OverworldBiomes.plains(), OAK); // Plains
        instBiomeLogMap.put(OverworldBiomes.river(), OAK); // River
        instBiomeLogMap.put(OverworldBiomes.savanna(), ACACIA); // Savanna
        instBiomeLogMap.put(OverworldBiomes.savanna(), ACACIA); // Savanna plateau
        instBiomeLogMap.put(OverworldBiomes.beach(), NONE); // Snowy beach
        instBiomeLogMap.put(OverworldBiomes.taiga(), SPRUCE); // Snowy taiga
        instBiomeLogMap.put(OverworldBiomes.plains(), OAK); // Sunflower plains
        instBiomeLogMap.put(OverworldBiomes.swamp(), OAK);
        instBiomeLogMap.put(OverworldBiomes.taiga(), SPRUCE); // Taiga
        instBiomeLogMap.put(OverworldBiomes.theVoid(), NONE);
        instBiomeLogMap.put(OverworldBiomes.warmOcean(), NONE);
        instBiomeLogMap.put(NetherBiomes.soulSandValley(), );
        instBiomeLogMap.put(NetherBiomes.basaltDeltas(), );
        instBiomeLogMap.put(NetherBiomes.crimsonForest(), );
        instBiomeLogMap.put(NetherBiomes.netherWastes(), );
        instBiomeLogMap.put(EndBiomes.theEnd(), NONE);
        instBiomeLogMap.put(EndBiomes.endBarrens(), NONE);
        instBiomeLogMap.put(EndBiomes.endHighlands(), NONE);
        instBiomeLogMap.put(EndBiomes.endMidlands(), NONE);
        instBiomeLogMap.put(EndBiomes.smallEndIslands(), NONE);
    }

    public static String get(Biome key) {
        return biomeLogMap.get(key);
    }
}
