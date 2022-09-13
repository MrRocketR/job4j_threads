package ru.job4j.cache;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAdded() {
        Cache cache = new Cache();
        boolean tested =  cache.add(new Base(1, 1));
        Assert.assertTrue(tested);
    }

    @Test
    public void whenDeleted() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        Base tested  =  cache.delete(base);
        Assert.assertEquals(base, tested);
    }

    @Test
    public void whenChanged() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        boolean tested = cache.update(base);
        Assert.assertTrue(tested);
    }

}