package com.cantu.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
        "com.cantu.ticket.balance.application",
        "com.cantu.ticket.balance.infraestructure",
        "com.cantu.ticket.balance.domain",
})
@SpringBootApplication
public class TicketBalanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketBalanceApplication.class, args);
    }
}