package com.splitwise.beans;

import java.util.List;

public class PercentSplit extends Split{

    private double percent;
    public PercentSplit(User user, double percent) {
        super(user);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
