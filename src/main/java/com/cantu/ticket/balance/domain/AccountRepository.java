package com.cantu.ticket.balance.domain;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> getAccountByAccountId(AccountId accountId);

    Optional<Account> getAccountByUserName(String userName);

    void add(Account account);

    void update(Account account);

    void remove(Account account);
}
