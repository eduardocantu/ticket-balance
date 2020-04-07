package com.cantu.ticket.balance.application;

import com.cantu.ticket.balance.domain.AccountService;
import com.cantu.ticket.balance.domain.Money;
import com.cantu.ticket.balance.domain.UserAccount;
import com.cantu.ticket.balance.domain.UserDoesnHaveAnAccountException;

public class UserBalanceService {

    private final AccountService accountService;

    public UserBalanceService(AccountService accountService) {
        this.accountService = accountService;
    }

    public UserAccount getUsersAccountBalance(String userName) throws UserDoesnHaveAnAccountException {
        Money currentBalance = accountService.getCurrentBalanceOfUsersAccount(userName);

        return UserAccount.UserAccountBuilder.anUserAccount()
                .withUserName(userName)
                .withCurrentBalance(currentBalance.getAmmount())
                .build();
    }
}
