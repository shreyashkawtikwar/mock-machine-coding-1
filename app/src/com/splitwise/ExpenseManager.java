package com.splitwise;

import com.splitwise.beans.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    Map<String,User> usersMap;
    private List<Expense> expenses;
    private Map<String,Map<String,Double>> balanceSheet;

    public void addUser(User user){
        usersMap.put(user.getUserId(), user);
        balanceSheet.put(user.getUserId(),new HashMap<>());
    }

    public ExpenseManager() {
        usersMap = new HashMap<>();
        expenses = new ArrayList<>();
        balanceSheet = new HashMap<>();
    }

    public void showBalance(String userId) {
        boolean flag = true;
        for(Map.Entry<String,Double> balanceMap : balanceSheet.get(userId).entrySet()){
            if(balanceMap.getValue()!=0){
                flag = false;
                printBalance(userId,balanceMap.getKey(),balanceMap.getValue());
            }
        }
        if(flag)
            System.out.println("No balances");

    }

    private void printBalance(String userId, String key, Double value) {
        if(value<0){
            System.out.println(usersMap.get(userId).getUserName() + " owes " + usersMap.get(key).getUserName() + " " + Math.abs(value));
        } else if(value > 0){
            System.out.println(usersMap.get(key).getUserName() + " owes " + usersMap.get(userId).getUserName() + " " + value);
        }
    }

    public void showAllBalances() {
        boolean flag = true;
        for(Map.Entry<String,Map<String,Double>> allBalances : balanceSheet.entrySet()){
            for(Map.Entry<String,Double> balanceMap : allBalances.getValue().entrySet()){
                if(balanceMap.getValue()>0){
                    flag = false;
                    printBalance(allBalances.getKey(),balanceMap.getKey(),balanceMap.getValue());
                }
            }
        }
        if(flag)
            System.out.println("No balances");
    }

    public void addExpense(ExpenseType expenseType, String paidBy, Double totalAmount, List<Split> splits) {
        Expense expense = ExpenseService.createExpense(expenseType,usersMap.get(paidBy),totalAmount,splits);
        expenses.add(expense);
        for(Split split:expense.getSplits()){
            String borrowerId = split.getUser().getUserId();
            Map<String,Double>balances =  balanceSheet.get(paidBy);
            Double oldBalance = balances.getOrDefault(borrowerId,0.0);
            balances.put(borrowerId,(oldBalance + split.getAmount()));

            Map<String,Double> borrowerBalance = balanceSheet.get(borrowerId);
            oldBalance = borrowerBalance.getOrDefault(paidBy,0.0);
            borrowerBalance.put(paidBy,(oldBalance - split.getAmount()));
        }
    }
}
