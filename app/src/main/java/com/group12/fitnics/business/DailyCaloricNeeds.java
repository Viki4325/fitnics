package com.group12.fitnics.business;

import com.group12.fitnics.objects.User;

public class DailyCaloricNeeds {

    private User user;
    private double bmr;
    private double dailyCaloricNeeds;

    public DailyCaloricNeeds(User user) {
        this.user = user;

        if (Character.compare(this.user.getGender(), 'M') == 0) {
            this.bmr = 66 + (6.3 * this.user.getWeight()) + (12.9 * this.user.getHeight()) - (6.8 * this.user.getAge());
        } else if (Character.compare(this.user.getGender(), 'F') == 0) {
            this.bmr = 655 + (4.3 * this.user.getWeight()) + (4.7 * this.user.getHeight()) - (4.7 * this.user.getAge());
        }

        if(this.user.getActivityLevel() == 0) {
            this.dailyCaloricNeeds = this.bmr * 1.2;
        } else if(this.user.getActivityLevel() == 1) {
            this.dailyCaloricNeeds = this.bmr * 1.55;
        } else if(this.user.getActivityLevel() == 2) {
            this.dailyCaloricNeeds = this.bmr * 1.725;
        } else if(this.user.getActivityLevel() == 3) {
            this.dailyCaloricNeeds = this.bmr * 1.9;
        }

        if (this.user.getGoal() == 0) {
            // If the goal is weight loss, create a caloric deficit of 750 kCal
            this.dailyCaloricNeeds -= 750;
        } else if (this.user.getGoal() == 2) {
            // If the goal is weight gain, create a caloric surplus of 750 kCal
            this.dailyCaloricNeeds += 750;
        }
    }

    public void updateDailyCaloricNeeds() {
        if (Character.compare(user.getGender(), 'M') == 0) {
            bmr = 66 + (6.3 * user.getWeight()) + (12.9 * user.getHeight()) - (6.8 * this.user.getAge());
        } else if (Character.compare(user.getGender(), 'F') == 0) {
            bmr = 655 + (4.3 * user.getWeight()) + (4.7 * user.getHeight()) - (4.7 * this.user.getAge());
        }

        if (user.getGoal() == 0) {
            // If the goal is weight loss, create a caloric deficit of 750 kCal
            dailyCaloricNeeds -= 750;
        } else if (user.getGoal() == 2) {
            // If the goal is weight gain, create a caloric surplus of 750 kCal
            dailyCaloricNeeds += 750;
        }
    }

    public int getDailyCaloricNeeds() {
        return (int) dailyCaloricNeeds;
    }

    public void addToDailyCalories(double amount) {
        dailyCaloricNeeds += amount;
    }

    public void subFromDailyCalories(double amount) {
        dailyCaloricNeeds -= amount;
    }

}
