package ru.job4j.synch.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public boolean add(Account account) {
        synchronized (accounts) {
            account = accounts.putIfAbsent(account.getId(), account);
        }
        return account != null;
    }

    public boolean update(Account account) {
        synchronized (accounts) {
            account = accounts.replace(account.getId(), account);
            return account != null;
        }
    }

    public boolean delete(int id) {
        synchronized (accounts) {
            Account account = accounts.remove(id);
            return account != null;
        }
    }

    public Optional<Account> getById(int id) {
        synchronized (accounts) {
            return Optional.ofNullable(accounts.get(id));
        }
    }

    public boolean transfer(int fromId, int toId, int amount) {
        synchronized (accounts) {
            Account accountFrom = accounts.get(fromId);
            Account accountTo = accounts.get(toId);
            if (accountFrom != null
                    && accountTo != null
                    && accountFrom.getAmount() >= amount) {
                accountFrom.setAmount(accountFrom.getAmount() - amount);
                accountTo.setAmount(accountTo.getAmount() + amount);
                return true;
            }
            return false;
        }
    }
}
