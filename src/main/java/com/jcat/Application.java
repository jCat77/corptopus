package com.jcat;

import com.jcat.domain.accounting.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final AccountingService accountingService;

    public Application(AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public void run(String... args) throws IOException {
        accountingService.test();
    }
}