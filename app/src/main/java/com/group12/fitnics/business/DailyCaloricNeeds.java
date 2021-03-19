package com.group12.fitnics.business;

import com.group12.fitnics.objects.User;

public class DailyCaloricNeeds {

    public static double resetDailyCaloricNeeds(User user) {
        double bmr = 0;
        double dailyCaloricNeeds = 0;

        if (Character.compare(user.getGender(), 'M') == 0) {
            bmr = 66 + (6.3 * user.getWeight()) + (12.9 * user.getHeight()) - (6.8 * user.getAge());
        } else if (Character.compare(user.getGender(), 'F') == 0) {
            bmr = 655 + (4.3 * user.getWeight()) + (4.7 * user.getHeight()) - (4.7 * user.getAge());
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
