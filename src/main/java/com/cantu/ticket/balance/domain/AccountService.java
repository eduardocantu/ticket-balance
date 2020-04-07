package com.cantu.ticket.balance.domain;

import java.util.Optional;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addMoneyToUsersAccount(Money ammount, String accountsOwnerUserName) throws UserDoesnHaveAnAccountException {
        Optional<Account> account = accountRepository.getAccountByUserName(accountsOwnerUserName);

        if (!account.isPresent()) {
            throw new UserDoesnHaveAnAccountException();
        }

        account.get().addMoney(ammount);
        accountRepository.update(account.get());
    }

    public Money getCurrentBalanceOfUsersAccount(String accountsOwnerUserName) throws UserDoesnHaveAnAccountException {
        Optional<Account> account = accountRepository.getAccountByUserName(accountsOwnerUserName);

        if (!account.isPresent()) {
            throw new UserDoesnHaveAnAccountException();
        }

        return account.get().currentBalance();
    }
}
