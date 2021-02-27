package com.splitwise.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Split {
    private User user;
    double amount;

    public Split(User user){
        this.user = user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }
}
