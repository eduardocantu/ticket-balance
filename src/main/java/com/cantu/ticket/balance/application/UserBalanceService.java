package com.cantu.ticket.balance.application;

import com.cantu.ticket.balance.domain.Account;
import com.cantu.ticket.balance.domain.AccountRepository;
import com.cantu.ticket.balance.domain.Balance;

public class UserBalanceService {

    private final AccountRepository accountRepository;

    public UserBalanceService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Balance getUserBalance(String userName) {
        Account account = accountRepository.getAccountByUserName(userName);
        return account.getCurrentBalance();
    }
}
