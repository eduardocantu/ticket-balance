package com.cantu.ticket.balance.infraestructure;

import com.cantu.ticket.balance.application.UserBalanceService;
import com.cantu.ticket.balance.domain.AccountRepository;
import com.cantu.ticket.balance.domain.AccountService;
import com.cantu.ticket.balance.domain.UserRepository;
import com.cantu.ticket.balance.domain.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class SpringBeanConfiguration {

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

    protected abstract UserRepository getUserRepository();

    protected abstract AccountRepository getAccountRepository();
}
