package com.group12.fitnics.objects.User;

public class Male implements IGender{

    @Override
    public double getHeightFactor() {
        return 12.9;
    }

    @Override
    public double getWeightFactor() {
        return 6.3;
    }

    @Override
    public double getAgeFactor() {
        return 6.8;
    }

    @Override
    public int getAddingTerm() {
        return 66;
    }
}
