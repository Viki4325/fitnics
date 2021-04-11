package com.group12.fitnics.objects.User;

public class Female implements IGender{

    @Override
    public double getHeightFactor() {
        return 4.7;
    }

    @Override
    public double getWeightFactor() {
        return 4.3;
    }

    @Override
    public double getAgeFactor() {
        return 4.7;
    }

    @Override
    public int getAddingTerm() {
        return 655;
    }
}
