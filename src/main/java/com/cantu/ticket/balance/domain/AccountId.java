package com.cantu.ticket.balance.domain;

import com.cantu.ticket.balance.com.cantu.ticket.ddd.EntityId;

import java.util.UUID;

public class AccountId implements EntityId {

    private String id;

    private AccountId() {
    }

    public String id() {
        return this.id;
    }

    public static AccountId aAccountId() {
        AccountId aAccountId = new AccountId();
        aAccountId.id = UUID.randomUUID().toString();
        return aAccountId;
    }

    public static AccountId aAccountId(String id) {
        AccountId aAccountId = new AccountId();
        aAccountId.id = id;
        return aAccountId;
    }
}
