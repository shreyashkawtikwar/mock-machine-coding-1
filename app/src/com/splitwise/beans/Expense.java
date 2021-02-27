package com.splitwise.beans;

import java.util.List;

public abstract class Expense {
    private String expenseId;
    private User paidBy;
    List<Split> splits;
    double totalExpense;

    public List<Split> getSplits() {
        return splits;
    }

    public Expense(String expenseId, User paidBy, double totalExpense, List<Split> splits){
        this.expenseId = expenseId;
        this.paidBy = paidBy;
        this.totalExpense = totalExpense;
        this.splits = splits;
    }

    public void setTotalExpense(double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public abstract boolean validate();
}
