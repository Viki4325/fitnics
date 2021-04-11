package com.group12.fitnics.business;

import com.group12.fitnics.objects.User.User;

public class DailyCaloricNeeds {

    public static double resetDailyCaloricNeeds(User user) {
        double bmr = user.getGender().getAddingTerm()
                + (user.getGender().getWeightFactor() * user.getWeight())
                + (user.getGender().getHeightFactor() * user.getHeight())
                + (user.getGender().getAgeFactor() * user.getAge());

        return bmr * user.getActivityLevel().getHBFactor() + user.getGoal().getAddingTerm();
    }


}
