package com.splitwise;

import com.splitwise.beans.*;

import java.util.List;

public class ExpenseService {

    public static Expense createExpense(ExpenseType expenseType, User paidBy, Double totalAmount, List<Split> splits) {
        switch (expenseType){
            case EQUAL:{
                Expense equalExp = new EqualExpense(null, paidBy,totalAmount,splits);
                int noOfUsers = splits.size();
                double amount = ((double)Math.round(((totalAmount*100)/noOfUsers))/100.0);
                for(int i=0;i<noOfUsers;i++){
                    splits.get(i).setAmount(amount);
                }
                splits.get(0).setAmount(totalAmount-(amount*(noOfUsers-1)));
                return equalExp;
            }
            case EXACT:{
                return new ExactExpense(null,paidBy,totalAmount,splits);
            }
            case PERCENT:{
                Expense percentExp = new PercentExpense(null, paidBy,totalAmount,splits);
                int noOfUsers = splits.size();
                for(int i=0;i<noOfUsers;i++){
                    PercentSplit perSplit = (PercentSplit) splits.get(i);
                    double percentVal = perSplit.getPercent();
                    double amount = ((double)Math.round(totalAmount*percentVal)/100.0);
                    splits.get(i).setAmount(amount);
                }
                return percentExp;
            }
            default:
                return null;
        }
    }
}
