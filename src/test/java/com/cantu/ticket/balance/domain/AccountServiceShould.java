package com.cantu.ticket.balance.domain;

import com.cantu.ticket.balance.infraestructure.AccountRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AccountServiceShould {

    private static final String ACCOUNT_OWNER_USER_NAME = "Some name";

    private AccountService accountService;

    @Test
    public void increaseAccountBalanceAfterAddMoney() {
        final Money amountToAddToUsersAccount = Money.MoneyBuilder.aMoney()
                .withAmmount(50).build();

        accountService.addMoneyToUsersAccount(amountToAddToUsersAccount, ACCOUNT_OWNER_USER_NAME);

        assertEquals(accountService.getCurrentBalanceOfUsersAccount(ACCOUNT_OWNER_USER_NAME),
                amountToAddToUsersAccount);
    }

    @BeforeEach
    public void setUp() {
        accountService = new AccountService(getAccountRepositoryWithInitialData());
    }

    private AccountRepository getAccountRepositoryWithInitialData() {
        AccountRepository accountRepository = new AccountRepositoryInMemory();
        accountRepository.add(getAccountForOwnerWithInitialBalance());
        return accountRepository;
    }

    private Account getAccountForOwnerWithInitialBalance() {
        return Account.
                AccountBuilder.anAccount()
                .withOwner(
                        User.UserBuilder.anUser()
                                .withName(ACCOUNT_OWNER_USER_NAME)
                                .build()
                ).withBalance(
                        Money.MoneyBuilder.aMoney()
                                .withAmmount(BigDecimal.valueOf(0))
                                .build()
                ).build();
    }
}
