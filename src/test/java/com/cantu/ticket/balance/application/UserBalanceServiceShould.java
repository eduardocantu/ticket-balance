package com.cantu.ticket.balance.application;

import com.cantu.ticket.balance.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserBalanceServiceShould {

    @Mock
    private AccountService accountService;

    @Mock
    private UserService userService;

    private UserBalanceService userBalanceService;

    @Test
    public void retrieveUserAccountsWhenAskedByUsername() {
        final String accountsOwnerUsername = "His username";
        final User accountsOwner = getUserWithUsername(accountsOwnerUsername);

        createTotalNumberOfAccountsToUser(5, accountsOwner);

        try {
            assertEquals(userBalanceService.getUserAccounts(accountsOwnerUsername).size(), 5);
        } catch (UserDoesNotExistsException e) {
            fail("It was expected that user has accounts", e);
        }
    }

    @Test
    public void exceptionShouldBeThrownWhenUserDoesNotExists() throws UserDoesNotExistsException {
        assertThrows(UserDoesNotExistsException.class,
                () -> userBalanceService.getUserAccounts("A username that does not exists"));
    }

    @Test
    public void balanceShouldBeAddedToUserAccountWhenAsked() {
        final String accountsOwnerUsername = "His username";
        final User accountsOwner = getUserWithUsername(accountsOwnerUsername);
        final String accountIdInternalId = "123456789";
        final AccountId accountId = AccountId.aAccountId(accountIdInternalId);
        final Account account = getAccount(accountId, accountsOwner, 0);

        linkAccountsToOwner(Arrays.asList(new Account[]{account}), accountsOwner);
        when(accountService.getAccount(any(AccountId.class))).thenReturn(Optional.of(account));

        final double amountToAddToAccount = 10.45;

        try {
            userBalanceService.addBalanceToUserAccount(amountToAddToAccount, accountsOwnerUsername, accountIdInternalId);
        } catch (UserDoesNotExistsException | UserDoesnHaveAnAccountException e) {
            fail("User was supposed to exist an have accounts", e);
        }

        verify(accountService, times(1))
                .addMoneyToAccount(
                        Money.MoneyBuilder.aMoney().withAmmount(amountToAddToAccount).build(),
                        accountId);
    }

    private User getUserWithUsername(final String username) {
        return User.UserBuilder.anUser()
                .withName(username)
                .build();
    }

    private void createTotalNumberOfAccountsToUser(int totalNumberOfAccounts, User accountsOwner) {
        createTotalNumberOfAccountsToUserWithBalance(totalNumberOfAccounts, accountsOwner, 0);
    }

    private void createTotalNumberOfAccountsToUserWithBalance(int totalNumberOfAccounts, User accountsOwner, int balance) {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < totalNumberOfAccounts; i++) {
            accounts.add(getAccount(AccountId.aAccountId(), accountsOwner, balance));
        }

        linkAccountsToOwner(accounts, accountsOwner);
    }

    private Account getAccount(AccountId accountId, User accountsOwner, int balance) {
        return Account.AccountBuilder.anAccount()
                .withAccountId(accountId)
                .withBalance(Money.MoneyBuilder.aMoney()
                        .withAmmount(balance)
                        .build())
                .withOwner(accountsOwner)
                .build();
    }

    private void linkAccountsToOwner(List<Account> accounts, User accountsOwner) {
        when(userService.getUserByUsername(accountsOwner.getName())).thenReturn(Optional.of(accountsOwner));
        when(accountService.getUserAccounts(accountsOwner)).thenReturn(accounts);
    }

    @BeforeEach
    public void setUp() {
        userBalanceService = new UserBalanceService(accountService, userService);
    }
}
