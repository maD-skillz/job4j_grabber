package ru.job4j.cache;


import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) throws IOException {
        SoftReference<V> softNull = null;
        SoftReference<V> softCache = cache.getOrDefault(key, softNull);
        V getRef = softCache.get();
        if (getRef == null) {
          V loadV = load(key);
            cache.put(key, softCache);
        } else {
            return getRef;
        }
        return getRef;
    }

    protected abstract V load(K key) throws IOException;

}
