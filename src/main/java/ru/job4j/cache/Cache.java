package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {
        int k = model.getId();
        boolean output = false;
         memory.computeIfPresent(
                model.getId(),
                (key, value) -> {
                    if (model.getVersion() != value.getVersion()) {
                        throw new OptimisticException(
                                "Versions don't match!"
                        );
                    }
                    memory.replace(model.getId(),
                           new Base(model.getId(), model.getVersion() + 1));
                    return memory.get(model.getId());
                }
        );
         if (memory.get(k)  != null) {
             output = true;
         }
       return output;
    }

    public Base delete(Base model) {
        return memory.remove(model.getId());
    }
}
