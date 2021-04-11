package com.group12.fitnics.business;

import com.group12.fitnics.objects.User;

public class DailyCaloricNeeds {

    public static double resetDailyCaloricNeeds(User user) {
        double bmr = 0;
        double dailyCaloricNeeds = 0;

        if (Character.compare(user.getGender(), 'M') == 0) {
            if (user.getUnits()[0] == 0) {
                //BMR = 66.47 + ( 6.24 × weight in pounds ) + ( 12.7 × height in inches ) − ( 6.755 × age in years )
                bmr = 66.47 + (6.24 * user.getWeight()) + (12.7 * user.getHeight()) - (6.755 * user.getAge());
            } else if (user.getUnits()[0] == 1) {
                //BMR = 66.47 + ( 13.75 × weight in kg ) + ( 5.003 × height in cm ) − ( 6.755 × age in years )
                bmr = 66.47 + (13.75 * user.getWeight()) + (5.003 * user.getHeight()) - (6.755 * user.getAge());
            }
        } else if (Character.compare(user.getGender(), 'F') == 0) {
            if (user.getUnits()[0] == 0) {
                //BMR = 655.1 + ( 4.35 × weight in pounds ) + ( 4.7 × height in inches ) − ( 4.7 × age in years )
                bmr = 655.1 + (4.35 * user.getWeight()) + (4.7 * user.getHeight()) - (4.7 * user.getAge());
            } else if (user.getUnits()[0] == 1) {
                //BMR = 655.1 + ( 9.563 × weight in kg ) + ( 1.85 × height in cm ) − ( 4.676 × age in years )
                bmr = 655 + (9.563 * user.getWeight()) + (1.85 * user.getHeight()) - (4.676 * user.getAge());
            }
        }

        if(user.getActivityLevel() == 0) {
            dailyCaloricNeeds = bmr * 1.2;
        } else if(user.getActivityLevel() == 1) {
            dailyCaloricNeeds = bmr * 1.55;
        } else if(user.getActivityLevel() == 2) {
            dailyCaloricNeeds = bmr * 1.725;
        } else if(user.getActivityLevel() == 3) {
            dailyCaloricNeeds = bmr * 1.9;
        }

        if (user.getGoal() == 0) {
            // If the goal is weight loss, create a caloric deficit of 750 kCal
            dailyCaloricNeeds -= 750;
        } else if (user.getGoal() == 2) {
            // If the goal is weight gain, create a caloric surplus of 750 kCal
            dailyCaloricNeeds += 750;
        }

        return dailyCaloricNeeds;
    }


}
