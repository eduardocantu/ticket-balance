package com.cantu.ticket.balance.infraestructure.dummy;

import com.cantu.ticket.balance.com.cantu.ticket.ddd.EntityId;
import com.cantu.ticket.balance.domain.User;
import com.cantu.ticket.balance.domain.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryInMemory implements UserRepository {

    private final Map<String, User> users;

    public UserRepositoryInMemory() {
        this.users = new HashMap<>();
    }

    @Override
    public Optional<User> getByEntityId(EntityId id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getByUsername(String username) {
        if (!users.containsKey(username)) {
            return Optional.empty();
        }
        return Optional.of(users.get(username));
    }

    @Override
    public void add(User itemToAdd) {
        users.put(itemToAdd.getName(), itemToAdd);
    }

    @Override
    public void update(User itemToUpdate) {
        users.put(itemToUpdate.getName(), itemToUpdate);
    }

}
