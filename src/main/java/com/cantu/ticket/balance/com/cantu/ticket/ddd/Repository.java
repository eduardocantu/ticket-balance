package com.cantu.ticket.balance.com.cantu.ticket.ddd;

import java.util.Optional;

public interface Repository<T> {

    Optional<T> getByEntityId(EntityId id);

    void add(T itemToAdd);

    void update(T itemToUpdate);

    void remove(T itemToRemove);
}
