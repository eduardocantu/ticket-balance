package com.cantu.ticket.balance.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {

    private BigDecimal ammount;

    public BigDecimal getAmmount() {
        return ammount;
    }

    public Money add(Money toAdd) {
        BigDecimal amountToAdd = toAdd.getAmmount();
        BigDecimal increasedAmmount = ammount.add(amountToAdd);

        return Money.MoneyBuilder.aMoney()
                .withAmmount(increasedAmmount)
                .build();
    }

    public Money remove(Money toRemove) {
        BigDecimal amountToRemove = toRemove.getAmmount();
        BigDecimal decreasedAmmount = ammount.add(amountToRemove);

        return Money.MoneyBuilder.aMoney()
                .withAmmount(decreasedAmmount)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return ammount.compareTo(money.ammount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ammount);
    }

    public static final class MoneyBuilder {
        private BigDecimal ammount;

        private MoneyBuilder() {
        }

        public static MoneyBuilder aMoney() {
            return new MoneyBuilder();
        }

        public static MoneyBuilder aMoneyWithNoAmmount() {
            MoneyBuilder moneyBuilder = aMoney();
            moneyBuilder.withAmmount(0);
            return moneyBuilder;
        }

        public MoneyBuilder withAmmount(int ammount) {
            this.ammount = BigDecimal.valueOf(ammount);
            return this;
        }

        public MoneyBuilder withAmmount(double ammount) {
            this.ammount = BigDecimal.valueOf(ammount);
            return this;
        }

        public MoneyBuilder withAmmount(BigDecimal ammount) {
            this.ammount = ammount;
            return this;
        }

        public Money build() {
            Money money = new Money();
            money.ammount = this.ammount;
            return money;
        }
    }
}
