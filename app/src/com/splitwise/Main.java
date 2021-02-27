package com.splitwise;

import com.splitwise.beans.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome To application");
        ExpenseManager exManager = new ExpenseManager();
        User user = new User.UserBuilder()
                .setUserId("u1")
                .setUserName("user1")
                .setEmail("mymail.com")
                .setMobileNo("8895411544")
                .build();
        exManager.addUser(user);

        user = new User.UserBuilder()
                .setUserId("u2")
                .setUserName("user2")
                .setEmail("mymail.com")
                .setMobileNo("8895411544")
                .build();
        exManager.addUser(user);

        user = new User.UserBuilder()
                .setUserId("u3")
                .setUserName("user3")
                .setEmail("mymail.com")
                .setMobileNo("8895411544")
                .build();
        exManager.addUser(user);

        user = new User.UserBuilder()
                .setUserId("u4")
                .setUserName("user4")
                .setEmail("mymail.com")
                .setMobileNo("8895411544")
                .build();
        exManager.addUser(user);

        while(true){
            String input = null;
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] command = input.split(" ");
            switch(command[0]){
                case "SHOW":{
                    if(command.length>1)
                        exManager.showBalance(command[1]);
                    else
                        exManager.showAllBalances();
                    break;
                }
                case "EXPENSE":{
                    String userId = command[1];
                    Double totalAmount = Double.parseDouble(command[2]);
                    int noOfUsers = Integer.parseInt(command[3]);
                    String expenseType = command[4+noOfUsers];
                    List<Split> splits = new ArrayList<>();
                    switch(expenseType){
                        case "EQUAL":{
                            for(int i=4;i<(4+noOfUsers);i++){
                                splits.add(new EqualSplit(exManager.usersMap.get(command[i])));
                            }
                            exManager.addExpense(ExpenseType.EQUAL,userId,totalAmount,splits);
                            break;
                        }
                        case "EXACT":{
                            for(int i=4;i<(4+noOfUsers);i++){
                                splits.add(new ExactSplit(exManager.usersMap.get(command[i]),
                                        Double.parseDouble(command[i+noOfUsers+1])));
                            }
                            exManager.addExpense(ExpenseType.EXACT,userId,totalAmount,splits);
                            break;
                        }
                        case "PERCENT":{
                            for(int i=4;i<(4+noOfUsers);i++){
                                splits.add(new PercentSplit(exManager.usersMap.get(command[i]),
                                        Double.parseDouble(command[i+noOfUsers+1])));
                            }
                            exManager.addExpense(ExpenseType.PERCENT,userId,totalAmount,splits);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }
}
