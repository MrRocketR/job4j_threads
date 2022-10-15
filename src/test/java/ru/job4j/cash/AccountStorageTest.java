package ru.job4j.cash;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.synch.cash.Account;
import ru.job4j.synch.cash.AccountStorage;

import java.util.Optional;

public class AccountStorageTest {

    @Test
    public void whenAdd() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        Assert.assertEquals(firstAccount.getAmount(), 100);
    }

    @Test
    public void whenUpdate() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.update(new Account(1, 200));
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        Assert.assertEquals(firstAccount.getAmount(), 200);
    }


    @Test
    public void whenDelete() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.delete(1);
        Assert.assertEquals(Optional.empty(), storage.getById(1));

    }

    @Test
    public void whenTransfer() {
        var storage = new AccountStorage();
        storage.add(new Account(1, 100));
        storage.add(new Account(2, 100));
        storage.transfer(1, 2, 100);
        var firstAccount = storage.getById(1)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        var secondAccount = storage.getById(2)
                .orElseThrow(() -> new IllegalStateException("Not found account by id = 1"));
        Assert.assertEquals(firstAccount.getAmount(), 0);
        Assert.assertEquals(secondAccount.getAmount(), 200);
    }
}