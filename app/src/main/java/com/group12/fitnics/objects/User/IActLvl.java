package com.group12.fitnics.objects.User;

public interface IActLvl {

    // Harris Benedict Formula
    // Once you’ve calculated your BMR, this is then put into the Harris Benedict Formula ,
    // which calculates your total calorie intake (required to maintain your current weight.)
    // The formula is: dailyCaloricNeeds = bmr * HBFactor

    double getHBFactor();

}
