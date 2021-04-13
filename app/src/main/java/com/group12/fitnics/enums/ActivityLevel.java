package com.group12.fitnics.enums;

import java.util.HashMap;
import java.util.Map;

public enum ActivityLevel {
    NOT_ACTIVE(0){
        @Override
        public double getHBFactor() {
            return 1.2;
        }
    },
    ACTIVE(2){
        @Override
        public double getHBFactor() {
            return 1.725;
        }
    },
    SOMEWHAT_ACTIVE(1){
        @Override
        public double getHBFactor() {
            return 1.55;
        }
    },
    VERY_ACTIVE(3){
        @Override
        public double getHBFactor() {
            return 1.9;
        }
    };

    public double getHBFactor() {
        return 0;
    }

    private ActivityLevel(int value) {
        this.value = value;
    }

    private final int value;
    private static final Map map = new HashMap<>();

    static {
        for (ActivityLevel level : ActivityLevel.values()) {
            map.put(level.value, level);
        }
    }

    public static ActivityLevel valueOf(int activity) {
        return (ActivityLevel) map.get(activity);
    }

    public int getValue() {
        return value;
    }
}
