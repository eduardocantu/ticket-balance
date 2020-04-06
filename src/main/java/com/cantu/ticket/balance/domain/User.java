package com.cantu.ticket.balance.domain;

public class User {

    private String name;

    public String getName() {
        return name;
    }

    public static final class UserBuilder {
        private String name;

        private UserBuilder() {
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
