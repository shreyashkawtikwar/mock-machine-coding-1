package com.splitwise.beans;

import java.util.List;

public class ExactSplit extends Split{

    public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }
}
