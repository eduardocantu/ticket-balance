package com.cantu.ticket.balance.domain;

import java.util.Objects;

public class Account {

    private AccountId accountId;

    private User owner;

    private Money balance;

    private Account() {
    }

    public AccountId getAccountId() {
        return AccountId.aAccountId(accountId);
    }

    public User getOwner() {
        return User.UserBuilder.anUser(owner).build();
    }

    public Money currentBalance() {
        return Money.MoneyBuilder.aMoney(balance).build();
    }

    public void addMoney(Money ammount) {
        balance = balance.add(ammount);
    }

    public void withdrawMoney(Money ammount) {
        balance = balance.remove(ammount);
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
        private AccountId accountId;
        private User owner;
        private Money balance;

        private AccountBuilder() {
        }

        public static AccountBuilder anAccount() {
            AccountBuilder builder = new AccountBuilder();
            builder.withAccountId(AccountId.aAccountId());
            return builder;
        }

        public AccountBuilder withAccountId(AccountId accountId) {
            this.accountId = accountId;
            return this;
        }

        public AccountBuilder withOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public AccountBuilder withBalance(Money balance) {
            this.balance = balance;
            return this;
        }

        public AccountBuilder withEmptyBalance() {
            this.balance = Money.MoneyBuilder.aMoneyWithNoAmmount()
                    .build();

            return this;
        }

        public Account build() {
            Account account = new Account();
            account.accountId = this.accountId;
            account.balance = this.balance;
            account.owner = this.owner;
            return account;
        }
    }
}
