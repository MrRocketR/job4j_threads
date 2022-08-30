package ru.job4j.res;



import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CountTest {

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        var count = new Count();
        var first = new Thread(count::increment);
        var second = new Thread(count::increment);
        /* Запускаем нити. */
        first.start();
        second.start();
        /* Заставляем главную нить дождаться выполнения наших нитей. */
        first.join();
        second.join();
        /* Проверяем результат. */
        Assert.assertEquals(count.get(), 2);
    }
}