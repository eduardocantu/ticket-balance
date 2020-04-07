package com.cantu.ticket.balance.domain;

import com.cantu.ticket.balance.infraestructure.AccountRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AccountServiceShould {

    private static final String ACCOUNT_OWNER_USER_NAME = "Some name";

    private AccountService accountService;

    @Test
    public void increaseAccountBalanceAfterAddMoney() {
        final Money amountToAddToUsersAccount = getMoneyWithAmmountOf(50);

        try {
            accountService.addMoneyToUsersAccount(amountToAddToUsersAccount, ACCOUNT_OWNER_USER_NAME);

            assertEquals(accountService.getCurrentBalanceOfUsersAccount(ACCOUNT_OWNER_USER_NAME),
                    amountToAddToUsersAccount);
        } catch (UserDoesnHaveAnAccountException e) {
        }
    }

    @Test
    public void throwExceptionWhenUserDoesntExists() {
        assertThrows(UserDoesnHaveAnAccountException.class,
                () -> accountService.addMoneyToUsersAccount(
                        getMoneyWithAmmountOf(50),
                        "Some name that does not exists")
        );
    }

    @BeforeEach
    public void setUp() {
        accountService = new AccountService(getAccountRepositoryWithInitialData());
    }

    private Money getMoneyWithAmmountOf(int amountOfMoney) {
        return Money.MoneyBuilder.aMoney()
                .withAmmount(amountOfMoney)
                .build();
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
