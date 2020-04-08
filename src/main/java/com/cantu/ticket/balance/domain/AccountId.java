package com.cantu.ticket.balance.domain;

import com.cantu.ticket.balance.com.cantu.ticket.ddd.EntityId;

import java.util.Objects;
import java.util.UUID;

public class AccountId implements EntityId {

    private String id;

    private AccountId() {
    }

    public String id() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountId accountId = (AccountId) o;
        return Objects.equals(id, accountId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public static AccountId aAccountId(AccountId accountId) {
        AccountId aAccountId = new AccountId();
        aAccountId.id = accountId.id;
        return aAccountId;
    }
}
