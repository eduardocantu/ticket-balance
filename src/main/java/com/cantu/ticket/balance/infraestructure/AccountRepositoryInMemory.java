package com.cantu.ticket.balance.infraestructure;

import com.cantu.ticket.balance.domain.Account;
import com.cantu.ticket.balance.domain.AccountId;
import com.cantu.ticket.balance.domain.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class AccountRepositoryInMemory implements AccountRepository {

    private final Map<AccountId, Account> accounts;

    public AccountRepositoryInMemory() {
        accounts = new HashMap<>();
    }

    @Override
    public Optional<Account> getAccountByAccountId(AccountId accountId) {
        if (accounts.containsKey(accountId)) {
            return Optional.of(accounts.get(accountId));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Account> getAccountByUserName(String userName) {
        return accounts.values().stream()
                .filter(
                        account -> account.getOwner().getName().equals(userName))
                .findFirst();
    }

    @Override
    public void add(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    @Override
    public void update(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    @Override
    public void remove(Account account) {
        if (accounts.get(account.getAccountId()) != null) {
            accounts.remove(account.getAccountId());
        }
    }
}
