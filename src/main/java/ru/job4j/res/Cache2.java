package ru.job4j.res;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Cache2 {
    private final ConcurrentHashMap<Integer, String> dic = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger();

    public Cache2() {
        dic.put(ids.incrementAndGet(), "Petr Arsentev");
        dic.put(ids.incrementAndGet(), "Ivan Ivanov");
    }

    public void add(String name) {
        dic.put(ids.incrementAndGet(), name);
    }

    public boolean contains(String name) {
        return dic.containsValue(name);
    }
}
