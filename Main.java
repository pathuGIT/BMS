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
            System.out.println("===================================");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Holder Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Initial Deposit Amount: ");
                    double initialDeposit = scanner.nextDouble();
                    if (validator.validateAmount(initialDeposit)) {
                        int accountNumber = accountService.createAccount(name, initialDeposit);
                        System.out.println("Account successfully created!");
                        System.out.println("Your Account Number: " + accountNumber);
                    } else {
                        System.out.println("Invalid amount. Account creation failed.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Account Number: ");
                    int accountNumberForDeposit = scanner.nextInt();
                    System.out.print("Enter Deposit Amount: ");
                    double depositAmount = scanner.nextDouble();
                    if (accountService.deposit(accountNumberForDeposit, depositAmount)) {
                        System.out.println("Deposit Successful!");
                    } else {
                        System.out.println("Deposit Failed. Check Account Number or Amount.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    int accountNumberForWithdraw = scanner.nextInt();
                    System.out.print("Enter Withdraw Amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (accountService.withdraw(accountNumberForWithdraw, withdrawAmount)) {
                        System.out.println("Withdrawal Successful!");
                    } else {
                        System.out.println("Withdrawal Failed. Check Account Number or Balance.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    int accountNumberForBalance = scanner.nextInt();
                    Double balance = accountService.checkBalance(accountNumberForBalance);
                    if (balance != null) {
                        System.out.println("Current Balance: " + balance);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Your Account Number: ");
                    int fromAccount = scanner.nextInt();
                    System.out.print("Enter Recipient Account Number: ");
                    int toAccount = scanner.nextInt();
                    System.out.print("Enter Transfer Amount: ");
                    double transferAmount = scanner.nextDouble();
                    if (accountService.transferFunds(fromAccount, toAccount, transferAmount)) {
                        System.out.println("Transfer Successful!");
                    } else {
                        System.out.println("Transfer Failed. Check Account Numbers or Balance.");
                    }
                    break;
                case 6:
                    System.out.println("Thank you for using the Banking Management System!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

