package com.cantu.ticket.balance.domain;

import java.math.BigDecimal;

public class UserAccount {

    private String userName;

    private String accountId;

    private BigDecimal currentBalance;

    public String getUserName() {
        return new String(userName);
    }

    public String getAccountId() {
        return new String(accountId);
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public static final class UserAccountBuilder {
        private String userName;
        private String accountId;
        private BigDecimal currentBalance;

        private UserAccountBuilder() {
        }

        public static UserAccountBuilder anUserAccount() {
            return new UserAccountBuilder();
        }

        public UserAccountBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public UserAccountBuilder withAccountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public UserAccountBuilder withCurrentBalance(BigDecimal currentBalance) {
            this.currentBalance = currentBalance;
            return this;
        }

        public UserAccount build() {
            UserAccount userAccount = new UserAccount();
            userAccount.userName = this.userName;
            userAccount.currentBalance = this.currentBalance;
            userAccount.accountId = this.accountId;
            return userAccount;
        }
    }
}
