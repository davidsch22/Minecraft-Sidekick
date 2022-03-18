package gr3enmachin3.rosiemod.maps;

import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

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

        instBiomeLogMap.put(Biomes.BADLANDS, NONE);
        instBiomeLogMap.put(Biomes.BADLANDS_PLATEAU, NONE);
        instBiomeLogMap.put(Biomes.BAMBOO_JUNGLE, JUNGLE);
        instBiomeLogMap.put(Biomes.BAMBOO_JUNGLE_HILLS, JUNGLE);
        instBiomeLogMap.put(Biomes.BEACH, NONE);
        instBiomeLogMap.put(Biomes.BIRCH_FOREST, BIRCH);
        instBiomeLogMap.put(Biomes.BIRCH_FOREST_HILLS, BIRCH);
        instBiomeLogMap.put(Biomes.COLD_OCEAN, NONE);
        instBiomeLogMap.put(Biomes.DARK_FOREST, DARK);
        instBiomeLogMap.put(Biomes.DARK_FOREST_HILLS, DARK);
        instBiomeLogMap.put(Biomes.DEEP_COLD_OCEAN, NONE);
        instBiomeLogMap.put(Biomes.DEEP_FROZEN_OCEAN, NONE);
        instBiomeLogMap.put(Biomes.DEEP_LUKEWARM_OCEAN, NONE);
        instBiomeLogMap.put(Biomes.DEEP_OCEAN, NONE);
        instBiomeLogMap.put(Biomes.DEEP_WARM_OCEAN, NONE);
        instBiomeLogMap.put(Biomes.DESERT, NONE);
        instBiomeLogMap.put(Biomes.DESERT_HILLS, NONE);
        instBiomeLogMap.put(Biomes.DESERT_LAKES, NONE);
        instBiomeLogMap.put(Biomes.END_BARRENS, NONE);
        instBiomeLogMap.put(Biomes.END_HIGHLANDS, NONE);
        instBiomeLogMap.put(Biomes.END_MIDLANDS, NONE);
        instBiomeLogMap.put(Biomes.ERODED_BADLANDS, NONE);
        instBiomeLogMap.put(Biomes.FLOWER_FOREST, OAK);
        instBiomeLogMap.put(Biomes.FOREST, OAK);
        instBiomeLogMap.put(Biomes.FROZEN_OCEAN, NONE);
        instBiomeLogMap.put(Biomes.FROZEN_RIVER, SPRUCE);
        instBiomeLogMap.put(Biomes.GIANT_SPRUCE_TAIGA, SPRUCE);
        instBiomeLogMap.put(Biomes.GIANT_SPRUCE_TAIGA_HILLS, SPRUCE);
        instBiomeLogMap.put(Biomes.GIANT_TREE_TAIGA, SPRUCE);
        instBiomeLogMap.put(Biomes.GIANT_TREE_TAIGA_HILLS, SPRUCE);
        instBiomeLogMap.put(Biomes.GRAVELLY_MOUNTAINS, SPRUCE);
        instBiomeLogMap.put(Biomes.ICE_SPIKES, NONE);
        instBiomeLogMap.put(Biomes.JUNGLE, JUNGLE);
        instBiomeLogMap.put(Biomes.JUNGLE_EDGE, JUNGLE);
        instBiomeLogMap.put(Biomes.JUNGLE_HILLS, JUNGLE);
        instBiomeLogMap.put(Biomes.LUKEWARM_OCEAN, NONE);
        instBiomeLogMap.put(Biomes.MODIFIED_BADLANDS_PLATEAU, NONE);
        instBiomeLogMap.put(Biomes.MODIFIED_GRAVELLY_MOUNTAINS, SPRUCE);
        instBiomeLogMap.put(Biomes.MODIFIED_JUNGLE, JUNGLE);
        instBiomeLogMap.put(Biomes.MODIFIED_JUNGLE_EDGE, JUNGLE);
        instBiomeLogMap.put(Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, OAK);
        instBiomeLogMap.put(Biomes.MOUNTAIN_EDGE, SPRUCE);
        instBiomeLogMap.put(Biomes.MOUNTAINS, SPRUCE);
        instBiomeLogMap.put(Biomes.MUSHROOM_FIELD_SHORE, NONE);
        instBiomeLogMap.put(Biomes.MUSHROOM_FIELDS, NONE);
        instBiomeLogMap.put(Biomes.NETHER, NONE);
        instBiomeLogMap.put(Biomes.OCEAN, NONE);
        instBiomeLogMap.put(Biomes.PLAINS, OAK);
        instBiomeLogMap.put(Biomes.RIVER, OAK);
        instBiomeLogMap.put(Biomes.SAVANNA, ACACIA);
        instBiomeLogMap.put(Biomes.SAVANNA_PLATEAU, ACACIA);
        instBiomeLogMap.put(Biomes.SHATTERED_SAVANNA, ACACIA);
        instBiomeLogMap.put(Biomes.SHATTERED_SAVANNA_PLATEAU, ACACIA);
        instBiomeLogMap.put(Biomes.SMALL_END_ISLANDS, NONE);
        instBiomeLogMap.put(Biomes.SNOWY_BEACH, NONE);
        instBiomeLogMap.put(Biomes.SNOWY_MOUNTAINS, SPRUCE);
        instBiomeLogMap.put(Biomes.SNOWY_TAIGA, SPRUCE);
        instBiomeLogMap.put(Biomes.SNOWY_TAIGA_HILLS, SPRUCE);
        instBiomeLogMap.put(Biomes.SNOWY_TAIGA_MOUNTAINS, SPRUCE);
        instBiomeLogMap.put(Biomes.SNOWY_TUNDRA, SPRUCE);
        instBiomeLogMap.put(Biomes.STONE_SHORE, OAK);
        instBiomeLogMap.put(Biomes.SUNFLOWER_PLAINS, OAK);
        instBiomeLogMap.put(Biomes.SWAMP, OAK);
        instBiomeLogMap.put(Biomes.SWAMP_HILLS, OAK);
        instBiomeLogMap.put(Biomes.TAIGA, SPRUCE);
        instBiomeLogMap.put(Biomes.TAIGA_HILLS, SPRUCE);
        instBiomeLogMap.put(Biomes.TAIGA_MOUNTAINS, SPRUCE);
        instBiomeLogMap.put(Biomes.TALL_BIRCH_FOREST, BIRCH);
        instBiomeLogMap.put(Biomes.TALL_BIRCH_HILLS, BIRCH);
        instBiomeLogMap.put(Biomes.THE_END, NONE);
        instBiomeLogMap.put(Biomes.THE_VOID, NONE);
        instBiomeLogMap.put(Biomes.WARM_OCEAN, NONE);
        instBiomeLogMap.put(Biomes.WOODED_BADLANDS_PLATEAU, OAK);
        instBiomeLogMap.put(Biomes.WOODED_HILLS, OAK);
        instBiomeLogMap.put(Biomes.WOODED_MOUNTAINS, OAK);
    }

    public static String get(Biome key) {
        return biomeLogMap.get(key);
    }
}
