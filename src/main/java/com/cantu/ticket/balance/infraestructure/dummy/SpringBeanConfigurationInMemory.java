package com.cantu.ticket.balance.infraestructure.dummy;

import com.cantu.ticket.balance.domain.*;
import com.cantu.ticket.balance.infraestructure.SpringBeanConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfigurationInMemory extends SpringBeanConfiguration {

    protected UserRepository getUserRepository() {
        final UserRepositoryInMemory userRepositoryInMemory = new UserRepositoryInMemory();

        userRepositoryInMemory.add(
                User.UserBuilder.anUser()
                        .withName("User1")
                        .build()
        );

        userRepositoryInMemory.add(
                User.UserBuilder.anUser()
                        .withName("User2")
                        .build()
        );

        return userRepositoryInMemory;
    }

    protected AccountRepository getAccountRepository() {
        final AccountRepositoryInMemory accountRepositoryInMemory = new AccountRepositoryInMemory();
        accountRepositoryInMemory.add(
                Account.AccountBuilder.anAccount()
                        .withAccountId(
                                AccountId.aAccountId("1")
                        )
                        .withOwner(
                                User.UserBuilder.anUser()
                                        .withName("User1").build())
                        .withEmptyBalance()
                        .build());

        accountRepositoryInMemory.add(
                Account.AccountBuilder.anAccount()
                        .withAccountId(
                                AccountId.aAccountId("2")
                        )
                        .withOwner(
                                User.UserBuilder.anUser()
                                        .withName("User2")
                                        .build())
                        .withBalance(Money.MoneyBuilder.aMoney()
                                .withAmmount(50)
                                .build())
                        .build());

        return accountRepositoryInMemory;
    }
}
