package org.example;
import org.example.service.AccountService;
import org.example.utill.InputValidator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();
        AccountService accountService = new AccountService();
        while(true){
            System.out.println("\n===================================");
            System.out.println("Banking Management System");
            System.out.println("===================================");
            System.out.println("01. Create Account");
            System.out.println("02. Deposit Money");
            System.out.println("03. Withdraw Money");
            System.out.println("04. Check Balance");
            System.out.println("05. Transfer Funds");
            System.out.println("06. Exit");
            System.out.println("===================================");
            System.out.print("Select Your Option: ");
            int choice = scan.nextInt();
            int accNo;
            switch (choice){
                case 1:
                    System.out.println("~~~~~ Create Account ~~~~~\n");

                    System.out.print("Enter Account Holder Name: ");
                    String holderName = scan.next();

                    System.out.print("Enter Account deposit Amount: ");
                    scan.nextLine();
                    String inputAmount = scan.nextLine();

                    if(inputValidator.isValidateAmount(inputAmount)){
                        double amount = Double.parseDouble(inputAmount);
                        if(inputValidator.isPositiveAmount(amount)){
                            accNo = accountService.createAccount(holderName,amount);
                            System.out.println("Account Successfully created.");
                            System.out.println("Your Acc No: "+accNo);
                        }else{
                            System.out.println("Amount Should Be Positive.");
                        }
                    }else{
                        System.out.println("Enter Valid Number...");
                    }

                    break;
                case 2:
                    System.out.println("~~~~~ Deposit Money  ~~~~~\n");
                    System.out.print("Enter Account Number: ");
                    accNo = scan.nextInt();
                    System.out.print("Enter Deposit Amount: ");
                    double depAmount = scan.nextDouble();

                    if(accountService.depositAmount(accNo,depAmount)){
                        System.out.println("Deposit Siceesful!");
                    }else{
                        System.out.println("Deposit Failed!");
                    }
                    break;
                case 3:
                    System.out.println("~~~~~ Withdraw Money ~~~~~\n");
                    System.out.print("Enter Account Number: ");
                    accNo = scan.nextInt();
                    System.out.print("Enter Withdraw Amount: ");
                    double withdAmount = scan.nextDouble();

                    if(accountService.withdrawAmount(accNo,withdAmount)){
                        System.out.println("Withdraw Siceesful!");
                    }else{
                        System.out.println("Withdraw Failed!");
                    }
                    break;
                case 4:
                    System.out.println("~~~~~ Check Balance ~~~~~\n");
                    System.out.print("Enter Account Number: ");
                    accNo = scan.nextInt();

                    double balance = accountService.checkBalance(accNo);
                    if(balance != -1){
                        System.out.println("Current Balance: "+balance);
                    }else{
                        System.out.println("Account Not Found!");
                    }
                    break;
                case 5:
                    System.out.println("~~~~~ Transfer Funds ~~~~~");
                    System.out.print("Enter Your Account Number: ");
                    int fromAcc = scan.nextInt();
                    System.out.print("Enter Recipient Account Number: ");
                    int toAcc = scan.nextInt();
                    System.out.print("Enter Transfer Amount: ");
                    double transferAmnt = scan.nextDouble();

                    if(accountService.transferAmount(fromAcc,toAcc,transferAmnt)){
                        System.out.println("Transfer Successful");
                    }else {
                        System.out.println("Transfer Failed, Check account number or balance.");
                    }

                    break;
                case 6:
                    System.out.println("Thank You..");
                    return;
                default:
                    System.out.println("Enter Valid Number...");
            }
        }

    }

}