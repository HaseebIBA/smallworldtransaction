package com.smallworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.smallworld.*")
public class TransactionMain {


    public static void main(String[] args) {
        System.out.println("Hi");
        SpringApplication.run(TransactionMain.class, args);

    }

}
