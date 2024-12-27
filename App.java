package com.banking;

import com.banking.service.AccountService;
import com.banking.util.InputValidator;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountService accountService = new AccountService();
        InputValidator validator = new InputValidator();

        while (true) {
            System.out.println("===================================");
            System.out.println("       Banking Management System");
            System.out.println("===================================");
            System.out.println("1. Create an Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Exit");
        }
    }
}
