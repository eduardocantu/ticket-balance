package com.cantu.ticket.balance.application;

import com.cantu.ticket.balance.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UserBalanceService {

    private final AccountToUserAccountFunction accountToUserAccountFunction;

    private final AccountService accountService;
    private final UserService userService;

    public UserBalanceService(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
        this.accountToUserAccountFunction = new AccountToUserAccountFunction();
    }

    public List<UserAccount> getUserAccounts(String accountsOwnerUsername) throws UserDoesNotExistsException {
        User accountsOwner = getUser(accountsOwnerUsername);

        return accountService.getUserAccounts(accountsOwner)
                .stream()
                .map(account -> {
                    return accountToUserAccountFunction.apply(account);
                })
                .collect(Collectors.toList());
    }

    public void addBalanceToUserAccount(double amountToAddToAccount, String accountOwnerUsername, String accountId)
            throws UserDoesnHaveAnAccountException, UserDoesNotExistsException {

        User accountsOwner = getUser(accountOwnerUsername);
        Account account = getAccount(AccountId.aAccountId(accountId));

        if (!account.getOwner().equals(accountsOwner)) {
            throw new UserDoesnHaveAnAccountException();
        }

        accountService.addMoneyToAccount(
                Money.MoneyBuilder.aMoney()
                        .withAmmount(amountToAddToAccount)
                        .build(),
                account.getAccountId());
    }

    public UserAccount getUserAccount(String accountOwnerUsername, String accountId)
            throws UserDoesNotExistsException, UserDoesnHaveAnAccountException {

        User accountsOwner = getUser(accountOwnerUsername);
        Account account = getAccount(AccountId.aAccountId(accountId));

        if (!account.getOwner().equals(accountsOwner)) {
            throw new UserDoesnHaveAnAccountException();
        }

        return accountToUserAccountFunction.apply(account);
    }

    private Account getAccount(AccountId accountId) throws UserDoesnHaveAnAccountException {
        Optional<Account> account = accountService.getAccount(accountId);
        if (!account.isPresent()) {
            throw new UserDoesnHaveAnAccountException();
        }

        return account.get();
    }

    private User getUser(String accountsOwnerUsername) throws UserDoesNotExistsException {
        Optional<User> accountOwner = userService.getUserByUsername(accountsOwnerUsername);

        if (!accountOwner.isPresent()) {
            throw new UserDoesNotExistsException();
        }
        return accountOwner.get();
    }

    private class AccountToUserAccountFunction implements Function<Account, UserAccount> {

        @Override
        public UserAccount apply(Account account) {
            return UserAccount.UserAccountBuilder.anUserAccount()
                    .withUserName(account.getOwner().getName())
                    .withCurrentBalance(account.currentBalance().getAmmount())
                    .withAccountId(account.getAccountId().id())
                    .build();
        }
    }
}
