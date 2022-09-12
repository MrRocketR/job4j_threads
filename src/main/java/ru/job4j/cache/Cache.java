package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public void update(Base model) throws OptimisticException {
        memory.computeIfPresent(
                model.getId(),
                (key, value) -> {
                    if (model.getVersion() != value.getVersion()) {
                        throw new OptimisticException(
                                "Versions don't match!"
                        );
                    }
                    model.setVersion(model.getVersion() + 1);
                    return model;
                }
        );
    }

    public Base delete(Base model) {
        return memory.remove(model.getId());
    }
}
