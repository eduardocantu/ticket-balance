package com.cantu.ticket.balance.domain;

import java.util.UUID;

public class AccountId {

    private UUID id;

    private AccountId() {
    }

    public static AccountId aAccountId() {
        AccountId aAccountId = new AccountId();
        aAccountId.id = UUID.randomUUID();
        return aAccountId;
    }
}
