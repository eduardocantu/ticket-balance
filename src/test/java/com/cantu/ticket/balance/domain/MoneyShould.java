package com.cantu.ticket.balance.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyShould {

    @Test
    public void haveTheValueIncreasedAfterAddingMoney() {
        Money beforeAdd = Money.MoneyBuilder
                .aMoney()
                .withAmmount(50)
                .build();

        Money toAdd = Money.MoneyBuilder
                .aMoney()
                .withAmmount(50)
                .build();

        Money afterAdition = Money.MoneyBuilder
                .aMoney()
                .withAmmount(100)
                .build();

        assertEquals(afterAdition, beforeAdd.add(toAdd));
    }

    @Test
    public void haveTheValueDecreasedAfterRemovingMoney() {
        Money beforeRemove = Money.MoneyBuilder
                .aMoney()
                .withAmmount(50)
                .build();

        Money toRemove = Money.MoneyBuilder
                .aMoney()
                .withAmmount(50)
                .build();

        Money afterRemoval = Money.MoneyBuilder
                .aMoney()
                .withAmmount(0)
                .build();

        assertEquals(afterRemoval, beforeRemove.remove(toRemove));
    }
}
