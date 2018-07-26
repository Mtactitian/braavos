package com.braavos.service;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class CreditService {

    private final List<String> bannedUsers;
    private final Map<String, Long> userDebts = new HashMap<>();
    private long balance;

    public CreditService(final List<String> bannedUsers, final long balance) {
        this.bannedUsers = bannedUsers;
        this.balance = balance;
    }

    public boolean getCredit(final String userName, final long amount) {
        if (bannedUsers.contains(userName) || balance < amount) {
            return false;
        }

        Long userDebt = userDebts.computeIfPresent(userName, (user, value) -> value + amount);
        if (userDebt == null) {
            userDebts.put(userName, amount);
        }
        balance -= amount;

        return true;
    }

    public boolean makeDeposit(final String userName, final long amount) {
        if (!userDebts.containsKey(userName)) {
            return false;
        }

        Long debt = userDebts.get(userName);
        if (debt < amount) {
            userDebts.remove(userName);
            balance += amount;
        } else {
            userDebts.put(userName, debt - amount);
            balance += amount;
        }

        return true;
    }

}
