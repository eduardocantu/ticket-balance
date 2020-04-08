package com.cantu.ticket.balance.domain;

import java.util.Objects;

public class User {

    private User() {
    }

    private String name;

    public String getName() {
        return new String(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static final class UserBuilder {
        private String name;

        private UserBuilder() {
        }

        public static UserBuilder anUser(User user) {
            return anUser()
                    .withName(user.getName());
        }

        public static UserBuilder anUser() {
            return new UserBuilder();
        }

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public User build() {
            User user = new User();
            user.name = this.name;
            return user;
        }
    }
}
