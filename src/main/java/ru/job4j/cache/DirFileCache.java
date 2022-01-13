package ru.job4j.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.file.Files;
import java.nio.file.Paths;


public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, new SoftReference<>(value));
    }

    @Override
    public String get(String key) {
        SoftReference<String> getKey = cache.get(key);
        return getKey != null ? getKey.get() : null;
    }

    @Override
    protected String load(String key) throws IOException {
        return new String(Files.readAllBytes(Paths.get(key)));
    }

}
