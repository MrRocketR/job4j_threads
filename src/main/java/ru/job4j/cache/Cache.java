package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) throws OptimisticException {
       Base out =  memory.computeIfPresent(
                model.getId(),
                (key, value) -> {
                    if (model.getVersion() != value.getVersion()) {
                        throw new OptimisticException(
                                "Versions don't match!"
                        );
                    }
                    Base base = new Base(model.getId(), model.getVersion() + 1);
                    base.setName(model.getName());
                    return base;
                }
        );
        return out != null;
        }



    public Base delete(Base model) {
        return memory.remove(model.getId());
    }
}
