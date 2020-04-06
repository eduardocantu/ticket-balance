package com.cantu.ticket.balance.infraestructure;

import com.cantu.ticket.balance.domain.Account;
import com.cantu.ticket.balance.domain.AccountId;
import com.cantu.ticket.balance.domain.AccountRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountRepositoryInMemory implements AccountRepository {

    private final Map<AccountId, Account> accounts;

    public AccountRepositoryInMemory() {
        accounts = new HashMap<>();
    }

    @Override
    public Account getAccountByAccountId(AccountId accountId) {
        return accounts.get(accountId);
    }

    @Override
    public Account getAccountByUserName(String userName) {
        return accounts.values().stream()
                .filter(
                        account -> account.getOwner().getName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void add(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    @Override
    public void remove(Account account) {
        if (accounts.get(account.getAccountId()) != null) {
            accounts.remove(account.getAccountId());
        }
    }
}
