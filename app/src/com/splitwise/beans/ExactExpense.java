package com.splitwise.beans;

import java.util.List;

public class ExactExpense extends Expense{

    public ExactExpense(String expenseId, User paidBy, double totalExpense, List<Split> splits) {
        super(expenseId, paidBy, totalExpense, splits);
    }

    @Override
    public boolean validate() {
        for(Split split:splits){
            if(!(split instanceof  ExactSplit))
                return false;
        }
        double totalAmount = 0;
        for(Split split:splits){
            totalAmount += split.getAmount();
        }
        if(totalAmount != totalExpense)
            return false;
        return true;
    }
}
