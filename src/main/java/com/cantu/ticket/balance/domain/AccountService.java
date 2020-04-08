package com.cantu.ticket.balance.domain;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addMoneyToAccount(Money amount, AccountId accountId) {
        Optional<Account> account = getAccount(accountId);
        account.get().addMoney(amount);
        accountRepository.update(account.get());
    }

    public void removeMoneyFromAccount(Money amount, AccountId accountId) {
        Optional<Account> account = getAccount(accountId);
        account.get().withdrawMoney(amount);
        accountRepository.update(account.get());
    }

    public Optional<Account> getAccount(AccountId accountId) {
        return accountRepository.getByEntityId(accountId);
    }

    public List<Account> getUserAccounts(User owner) {
        return accountRepository.getAccountsByOwner(owner);
    }
}
