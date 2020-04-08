package com.cantu.ticket.balance.domain;

import com.cantu.ticket.balance.infraestructure.AccountRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceShould {

    private AccountRepository accountRepository;
    private AccountService accountService;

    @Test
    public void decreaseAccountBalanceAfterRemoveMoney() {
        final Account accountToHaveTheAmountDecreased = getAccountWithInitialBalance(100);
        final AccountId accountId = accountToHaveTheAmountDecreased.getAccountId();

        accountRepository.add(accountToHaveTheAmountDecreased);

        accountService.removeMoneyFromAccount(getMoneyWithAmmountOf(40), accountId);
        final Account accountWithAmountDecreased = accountService.getAccount(accountId).get();

        assertEquals(accountWithAmountDecreased.currentBalance(), getMoneyWithAmmountOf(60));
    }

    @Test
    public void increaseAccountBalanceAfterAddMoney() {
        final Account accountToHaveTheAmountIncreased = getAccountWithInitialBalance(0);
        final AccountId accountId = accountToHaveTheAmountIncreased.getAccountId();

        accountRepository.add(accountToHaveTheAmountIncreased);

        accountService.addMoneyToAccount(getMoneyWithAmmountOf(50), accountId);
        final Account accountWithAmountIncreased = accountService.getAccount(accountId).get();

        assertEquals(accountWithAmountIncreased.currentBalance(), getMoneyWithAmmountOf(50));
    }

    @Test
    public void anAccountShouldNotBeRetrievedWithWrongId() {
        accountRepository.add(getAccountWithInitialBalance(0));

        AccountId aRandomAccountId = AccountId.aAccountId();
        assertFalse(accountService.getAccount(aRandomAccountId).isPresent());
    }

    @Test
    public void listAllUsersAccounts() {
        final String accountsOwner = "Some username";

        accountRepository.add(getAccountWithInitialBalanceForUsername(0, accountsOwner));
        accountRepository.add(getAccountWithInitialBalanceForUsername(0, accountsOwner));
        accountRepository.add(getAccountWithInitialBalanceForUsername(0, accountsOwner));

        assertEquals(accountService.getUserAccounts(getUserWithName(accountsOwner)).size(), 3);
    }

    @BeforeEach
    public void setUp() {
        cleanUpAccountRepository();
        accountService = new AccountService(accountRepository);
    }

    private void cleanUpAccountRepository() {
        accountRepository = new AccountRepositoryInMemory();
    }

    private Account getAccountWithInitialBalance(int initialBalance) {
        return getAccountWithInitialBalanceForUsername(initialBalance, "Some random user name");
    }

    private Account getAccountWithInitialBalanceForUsername(int initialBalance, String username) {
        return Account.
                AccountBuilder.anAccount()
                .withOwner(
                        getUserWithName(username))
                .withBalance(
                        getMoneyWithAmmountOf(initialBalance))
                .build();
    }

    private User getUserWithName(String owner) {
        return User.UserBuilder.anUser()
                .withName(owner)
                .build();
    }

    private Money getMoneyWithAmmountOf(int amountOfMoney) {
        return Money.MoneyBuilder.aMoney()
                .withAmmount(amountOfMoney)
                .build();
    }
}
