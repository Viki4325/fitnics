package com.group12.fitnics.enums;

import java.util.HashMap;
import java.util.Map;

public enum Goal {
    NO_GOAL(-1){

    },
    GOAL_GAIN(0){
        @Override
        public double getAddingTerm() {
            return 750;
        }
    },
    GOAL_MAINTAIN(2){
        @Override
        public double getAddingTerm(){
            return 0;
        }
    },
    GOAL_LOOSE(1){
        @Override
        public double getAddingTerm(){
            return -750;
        }
    };

    public double getAddingTerm() {
        return 0;
    }

    private Goal(int value) {
        this.value = value;
    }

    private final int value;
    private static Map map = new HashMap<>();

    static {
        for (Goal goal : Goal.values()) {
            map.put(goal.value, goal);
        }
    }

    public static Goal valueOf(int goal) {
        return (Goal) map.get(goal);
    }

    public int getValue() {
        return value;
    }
}
