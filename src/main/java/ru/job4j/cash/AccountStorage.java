package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public boolean add(Account account) {
        synchronized (accounts) {
            account = accounts.putIfAbsent(account.getId(), account);
            if (account != null) {
                return true;
            }
        }
        return false;
    }

    public boolean update(Account account) {
        synchronized (accounts) {
            int amount = account.getAmount();
            account = accounts.get(account.getId());
            if (account != null) {
                account.setAmount(amount);
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        synchronized (accounts) {
            Account account = accounts.remove(id);
            if (account != null) {
                return true;
            }
        }
        return false;
    }

    public Optional<Account> getById(int id) {
        synchronized (accounts) {
            return Optional.of(accounts.get(id));
        }
    }

    public boolean transfer(int fromId, int toId, int amount) {
        synchronized (accounts) {
            Account accountFrom = accounts.get(fromId);
            Account accountTo = accounts.get(toId);
            if (accountFrom != null || accountTo != null) {
                accountFrom.setAmount(accountFrom.getAmount() - amount);
                accountTo.setAmount(accountTo.getAmount() + amount);
                return true;
            }
        }
        return false;
    }
}
