package com.splitwise.beans;

import java.util.List;

public class EqualExpense extends  Expense{

    public EqualExpense(String expenseId, User paidBy, double totalExpense, List<Split> splits) {
        super(expenseId, paidBy, totalExpense, splits);
    }

    @Override
    public boolean validate() {
        for(Split split:splits){
            if(!(split instanceof  EqualSplit))
                return false;
        }
        return true;
    }
}
