package com.gr3enmachin3.rosiemod.maps;

import java.util.HashMap;
import java.util.Map;

public class BlockItemMap {
    private final Map<String, String> instBlockItemMap = new HashMap<>();

    private static final Map<String, String> blockItemMap = new BlockItemMap().instBlockItemMap;

    private BlockItemMap() {
        instBlockItemMap.put("coal_ore", "coal");
        instBlockItemMap.put("diamond_ore", "diamond");
    }

    public static String get(String key) {
        String value = blockItemMap.get(key);
        if (value == null) {
            return key;
        }
        return value;
    }
}
