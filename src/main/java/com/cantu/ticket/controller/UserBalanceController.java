package com.cantu.ticket.controller;

import com.cantu.ticket.balance.application.UserBalanceService;
import com.cantu.ticket.balance.domain.UserAccount;
import com.cantu.ticket.balance.domain.UserDoesNotExistsException;
import com.cantu.ticket.balance.domain.UserDoesnHaveAnAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserBalanceController {

    private final UserBalanceService userBalanceService;

    @Autowired
    public UserBalanceController(UserBalanceService userBalanceService) {
        this.userBalanceService = userBalanceService;
    }

    @RequestMapping(value = "/user-account-balance")
    public ResponseEntity<UserAccount> getUsersAccountBalance(
            @RequestParam("username") @Valid String username,
            @RequestParam("accountId") @Valid String accountId) {

        try {
            return ResponseEntity.ok(userBalanceService.getUserAccount(username, accountId));
        } catch (UserDoesNotExistsException | UserDoesnHaveAnAccountException e) {
            return CustomExceptionHandler
                    .handleCustomException(e, "User does not have an account", HttpStatus.NOT_FOUND);
        }
    }
}
