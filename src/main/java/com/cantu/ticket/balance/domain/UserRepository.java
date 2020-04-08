package com.cantu.ticket.balance.domain;

import com.cantu.ticket.balance.com.cantu.ticket.ddd.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User> {

    Optional<User> getByUsername(String username);
}
