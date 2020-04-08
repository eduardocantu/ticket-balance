package com.cantu.ticket.balance.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountServiceShould {

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    @Test
    public void decreaseAccountBalanceAfterRemoveMoney() {
        final Account accountToHaveTheAmountDecreased = getAccountWithInitialBalance(100);
        final AccountId accountId = accountToHaveTheAmountDecreased.getAccountId();

        when(accountRepository.getByEntityId(accountId))
                .thenReturn(Optional.of(accountToHaveTheAmountDecreased));

        accountService.removeMoneyFromAccount(getMoneyWithAmmountOf(40), accountId);

        verify(accountRepository, times(1))
                .update(accountToHaveTheAmountDecreased);
    }

    @Test
    public void increaseAccountBalanceAfterAddMoney() {
        final Account accountToHaveTheAmountIncreased = getAccountWithInitialBalance(0);
        final AccountId accountId = accountToHaveTheAmountIncreased.getAccountId();

        when(accountRepository.getByEntityId(accountId))
                .thenReturn(Optional.of(accountToHaveTheAmountIncreased));

        accountService.addMoneyToAccount(getMoneyWithAmmountOf(50), accountId);

        verify(accountRepository, times(1))
                .update(accountToHaveTheAmountIncreased);
    }

    @Test
    public void notRetrieveAnAccountWhenAskedWithWrongId() {
        Account account = getAccountWithInitialBalance(0);
        AccountId aRandomAccountId = AccountId.aAccountId();

        when(accountRepository.getByEntityId(account.getAccountId())).thenReturn(Optional.of(account));
        assertFalse(accountService.getAccount(aRandomAccountId).isPresent());
    }

    @Test
    public void listAllUsersAccounts() {
        final String accountsOwnerUsername = "Some username";
        final User accountsOwner = getUserWithName(accountsOwnerUsername);

        List<Account> accounts = Arrays.asList(
                new Account[]{
                        getAccountWithInitialBalanceForUsername(0, accountsOwnerUsername),
                        getAccountWithInitialBalanceForUsername(0, accountsOwnerUsername),
                        getAccountWithInitialBalanceForUsername(0, accountsOwnerUsername)
                });

        when(accountRepository.getAccountsByOwner(accountsOwner)).thenReturn(accounts);

        assertEquals(accountService.getUserAccounts(getUserWithName(accountsOwnerUsername)).size(), 3);
    }

    @BeforeEach
    public void setUp() {
        accountService = new AccountService(accountRepository);
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
