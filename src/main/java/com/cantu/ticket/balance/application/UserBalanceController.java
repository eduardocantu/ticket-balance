package com.cantu.ticket.balance.application;

import com.cantu.ticket.balance.domain.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserBalanceController {

    private final AccountService accountService;

    public UserBalanceController(AccountService accountService) {
        this.accountService = accountService;
    }

    public UserAccount getUserAccount(String userName) {
        Money currentBalance = accountService.getCurrentBalanceOfUsersAccount(userName);

        UserAccount userAccount = UserAccount.UserAccountBuilder.anUserAccount()
                .withUserName(userName)
                .withCurrentBalance(currentBalance.getAmmount())
                .build();

        return userAccount;
    }
}
