package com.cantu.ticket.balance.domain;

import com.cantu.ticket.balance.com.cantu.ticket.ddd.Repository;

import java.util.List;

public interface AccountRepository extends Repository<Account> {

    List<Account> getAccountsByOwner(User owner);

}
