package com.cantu.ticket.balance.domain;

import java.util.Objects;

public class Account {

    private AccountId accountId;

    private User owner;

    private Balance currentBalance;

    public User getOwner() {
        return owner;
    }

    public Balance getCurrentBalance() {
        return currentBalance;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    public static final class AccountBuilder {
        private User owner;
        private Balance currentBalance;

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            return new AccountBuilder();
        }

        public AccountBuilder withOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public AccountBuilder withCurrentBalance(Balance currentBalance) {
            this.currentBalance = currentBalance;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.accountId = AccountId.aAccountId();
            account.currentBalance = this.currentBalance;
            account.owner = this.owner;
            return account;
        }
    }
}
