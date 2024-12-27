package org.example.service;

import org.example.utill.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService {
    public int createAccount(String holderName, double amount){
        String sql = "INSERT INTO accounts (holder_name, balance) VALUES (?, ?) ";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,holderName);
            preparedStatement.setDouble(2,amount);
            int rawAffected = preparedStatement.executeUpdate(); //execute the sql statement

            if(rawAffected > 0){
                ResultSet generateKey = preparedStatement.getGeneratedKeys();
                if(generateKey.next()){
                    return generateKey.getInt(1); //return account id
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1; //if account created is failed
    }

    public boolean depositAmount(int accNum, double depAmount){
        String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,depAmount);
            preparedStatement.setInt(2,accNum);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean withdrawAmount(int accNum, double withdAmount){
        String sql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ? AND balance >= ?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,withdAmount);
            preparedStatement.setInt(2,accNum);
            preparedStatement.setDouble(3,withdAmount);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double checkBalance(int accNo){
        String sql = "SELECT balance FROM accounts WHERE account_id = ?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,accNo);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public boolean transferAmount(int fromAcc, int toAcc, double amount){
        String withdrwSql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ? AND balance >= ?";
        String depSql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

        try(Connection connection = DatabaseConnection.getConnection()){
            connection.setAutoCommit(false);

            //Withdraw Money
            try(PreparedStatement withStatement = connection.prepareStatement(withdrwSql)){
                withStatement.setDouble(1,amount);
                withStatement.setInt(2,fromAcc);
                withStatement.setDouble(3,amount);
                if(withStatement.executeUpdate() == 0){
                    connection.rollback(); //rollbaclk if withdraw failed.
                    return false;
                }
            }

            //Deposit money
            try(PreparedStatement depStatement = connection.prepareStatement(depSql)){
                depStatement.setDouble(1,amount);
                depStatement.setInt(2,toAcc);
                if(depStatement.executeUpdate() == 0){
                    connection.rollback();
                    return false;
                }
            }

            connection.commit();
            return true;
        }catch (SQLException e){
            System.out.println("Error Transaction funds "+ e.getMessage());
        }
        return false;
    }




}
