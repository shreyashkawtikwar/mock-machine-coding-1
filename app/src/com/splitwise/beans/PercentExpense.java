package com.splitwise.beans;

import java.util.List;

public class PercentExpense extends Expense{

    public PercentExpense(String expenseId, User paidBy, double totalExpense, List<Split> splits) {
        super(expenseId, paidBy, totalExpense, splits);
    }

    @Override
    public boolean validate() {
        for(Split split:splits){
            if(!(split instanceof  PercentSplit))
                return false;
        }

        double totalPercent = 100;
        double givenPercentSum = 0;
        for(Split split:splits){
            givenPercentSum += ((PercentSplit)split).getPercent();
        }
        if(givenPercentSum!=totalPercent)
            return false;
        return true;
    }
}
