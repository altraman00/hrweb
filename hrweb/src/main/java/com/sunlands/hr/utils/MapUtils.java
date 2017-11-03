package com.sunlands.hr.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 方便操作map
 */
public class MapUtils {

	public static MapBuilder<String, String> build() {
        return build(String.class, String.class);
    }

    public static <K, V> MapBuilder<K, V> build(Class<K> keyType, Class<V> valueType) {
        MapBuilder<K, V> mapBuilder = new MapBuilder<K, V>();
        return mapBuilder;
    }

    public static class MapBuilder<K, V> {

        private LinkedHashMap<K, V> cache = new LinkedHashMap<K, V>();

        public MapBuilder<K, V> add(K key, V value) {
            cache.put(key, value);
            return this;
        }

        public MapBuilder<K, V> addAll(Map<K, V> map) {
            if (map != null) {
                cache.putAll(map);
            }
            return this;
        }

        public HashMap<K, V> toHashMap() {
            HashMap<K, V> map = new HashMap<K, V>();
            map.putAll(cache);
            return map;
        }

        public TreeMap<K, V> toTreeMap() {
            TreeMap<K, V> map = new TreeMap<K, V>();
            map.putAll(cache);
            return map;
        }

        public LinkedHashMap<K, V> toLinkedHashMap() {
            LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
            map.putAll(cache);
            return map;
        }
    }
    
    
    
}
