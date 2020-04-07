package com.cantu.ticket.balance.domain;

public interface AccountRepository {

    Account getAccountByAccountId(AccountId accountId);

    Account getAccountByUserName(String userName);

    void add(Account account);

    void update(Account account);

    void remove(Account account);
}
