package com.cantu.ticket.balance.infraestructure;

import com.cantu.ticket.balance.application.UserBalanceService;
import com.cantu.ticket.balance.domain.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBeanConfiguration {

    @Bean
    UserBalanceService userBalanceService(AccountService accountService, UserService userService) {
        return new UserBalanceService(accountService, userService);
    }

    @Bean
    UserService userService() {
        return new UserService(getUserRepository());
    }

    @Bean
    AccountService accountService(AccountRepository accountRepository) {
        return new AccountService(getAccountRepository());
    }

    private UserRepository getUserRepository() {
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

    private AccountRepositoryInMemory getAccountRepository() {
        final AccountRepositoryInMemory accountRepositoryInMemory = new AccountRepositoryInMemory();
        accountRepositoryInMemory.add(
                Account.AccountBuilder.anAccount()
                        .withOwner(
                                User.UserBuilder.anUser()
                                        .withName("User1").build())
                        .withEmptyBalance().build());

        accountRepositoryInMemory.add(
                Account.AccountBuilder.anAccount()
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
