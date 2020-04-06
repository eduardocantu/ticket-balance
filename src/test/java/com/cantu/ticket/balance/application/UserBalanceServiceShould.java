package com.cantu.ticket.balance.application;

import com.cantu.ticket.balance.application.UserBalanceService;
import com.cantu.ticket.balance.domain.Account;
import com.cantu.ticket.balance.domain.AccountRepository;
import com.cantu.ticket.balance.domain.Balance;
import com.cantu.ticket.balance.domain.User;
import com.cantu.ticket.balance.infraestructure.AccountRepositoryInMemory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;

public class UserBalanceServiceShould {

    private static final String ACCOUNT_OWNER_USER_NAME = "Some name";
    private static final Double ACCOUNT_BALANCE_OF_OWNER_S_ACCOUNT = 50.0;

    private UserBalanceService balanceService;

    @Before
    public void setUp() {
        balanceService = new UserBalanceService(getAccountRepository());
    }

    private AccountRepository getAccountRepository() {
        AccountRepository accountRepository = new AccountRepositoryInMemory();
        accountRepository.add(getAccount());
        return accountRepository;
    }

    private Account getAccount() {
        return Account.
                AccountBuilder.anAccount()
                .withOwner(
                        User.UserBuilder.anUser()
                                .withName(ACCOUNT_OWNER_USER_NAME)
                                .build()
                ).
                        withCurrentBalance(
                                Balance.BalanceBuilder.aBalance()
                                        .withAmmount(new BigDecimal(ACCOUNT_BALANCE_OF_OWNER_S_ACCOUNT))
                                        .build()
                        ).build();
    }

    @Test
    public void retrieveCurrentBalanceWhenAsked() {
        Assert.assertThat(balanceService.getUserBalance(ACCOUNT_OWNER_USER_NAME)
                        .getAmmount().doubleValue(),
                is(ACCOUNT_BALANCE_OF_OWNER_S_ACCOUNT));
    }
}
