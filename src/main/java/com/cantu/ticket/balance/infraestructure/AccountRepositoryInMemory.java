package com.cantu.ticket.balance.infraestructure;

import com.cantu.ticket.balance.com.cantu.ticket.ddd.EntityId;
import com.cantu.ticket.balance.domain.Account;
import com.cantu.ticket.balance.domain.AccountId;
import com.cantu.ticket.balance.domain.AccountRepository;
import com.cantu.ticket.balance.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountRepositoryInMemory implements AccountRepository {

    private final Map<AccountId, Account> accounts;

    public AccountRepositoryInMemory() {
        accounts = new HashMap<>();
    }

    @Override
    public Optional<Account> getByEntityId(EntityId id) {
        if (accounts.containsKey(id)) {
            return Optional.of(accounts.get(id));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Account> getAccountsByOwner(User owner) {
        return accounts.values().stream()
                .filter(
                        account -> account.getOwner().equals(owner))
                .collect(Collectors.toList());
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
