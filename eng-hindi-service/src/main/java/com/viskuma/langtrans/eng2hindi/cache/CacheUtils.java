package com.viskuma.langtrans.eng2hindi.cache;

import org.springframework.cache.CacheManager;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

public class CacheUtils {

    private static final String CACHE_NAME = "LANGUAGEMAP_CACHE";

    private HazelcastInstance hazelcastInstance;
    private IMap<String, Object> cache;

    public static CacheUtils INSTANCE = new CacheUtils();
    
    private CacheUtils() {
        Config config = new Config();
        hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        cache = hazelcastInstance.getMap(CACHE_NAME);
    }

    public void put(String key, Object value) {
        cache.put(key, value);
    }

    public Object get(String key) {
        return cache.get(key);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public void clearCache() {
        cache.clear();
    }

    public void shutdown() {
        hazelcastInstance.shutdown();
    }

    public static void main(String[] args) {
    	CacheUtils cacheManager = new CacheUtils();

        // Putting values into cache
        cacheManager.put("key1", "value1");
        cacheManager.put("key2", "value2");
        cacheManager.put("key3", "value3");

        // Retrieving values from cache
        System.out.println("Value for key1: " + cacheManager.get("key1"));
        System.out.println("Value for key2: " + cacheManager.get("key2"));
        System.out.println("Value for key3: " + cacheManager.get("key3"));

        // Removing a value from cache
        cacheManager.remove("key2");
        System.out.println("Value for key2 after removal: " + cacheManager.get("key2"));

        // Clearing cache
        cacheManager.clearCache();
        System.out.println("Cache cleared");

        cacheManager.shutdown();
    }
}
