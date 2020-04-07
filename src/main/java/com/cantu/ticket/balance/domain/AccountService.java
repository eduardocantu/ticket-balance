package com.cantu.ticket.balance.domain;

public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addMoneyToUsersAccount(Money ammount, String accountsOwnerUserName) {
        Account account = accountRepository.getAccountByUserName(accountsOwnerUserName);
        account.addMoney(ammount);
        accountRepository.update(account);
    }

    public Money getCurrentBalanceOfUsersAccount(String accountsOwnerUserName) {
        Account account = accountRepository.getAccountByUserName(accountsOwnerUserName);
        return account.currentBalance();
    }
}
