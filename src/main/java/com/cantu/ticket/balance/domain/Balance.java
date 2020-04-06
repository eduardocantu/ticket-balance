package com.cantu.ticket.balance.domain;

import java.math.BigDecimal;

public class Balance {

    private BigDecimal ammount;

    public BigDecimal getAmmount() {
        return ammount;
    }

    public static final class BalanceBuilder {
        private BigDecimal ammount;

        private BalanceBuilder() {
        }

        public static BalanceBuilder aBalance() {
            return new BalanceBuilder();
        }

        public BalanceBuilder withAmmount(BigDecimal ammount) {
            this.ammount = ammount;
            return this;
        }

        public Balance build() {
            Balance balance = new Balance();
            balance.ammount = this.ammount;
            return balance;
        }
    }
}
