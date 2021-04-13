package com.group12.fitnics.enums;

import java.util.HashMap;
import java.util.Map;

public enum Gender {
    MALE('M',0){
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
    },
    FEMALE('F',1){
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
    },
    OTHER('O',2){
        @Override
        public double getHeightFactor() {
            return 0;
        }

        @Override
        public double getWeightFactor() {
            return 0;
        }

        @Override
        public double getAgeFactor() {
            return 0;
        }

        @Override
        public int getAddingTerm() {
            return 0;
        }
    };


    private Gender(char value,int pos) {
        this.value = value;
        this.pos = pos;
    }

    private char value;
    private int pos;

    private static Map map = new HashMap<>();

    static {
        for (Gender level : Gender.values()) {
            map.put(level.value, level);
        }
    }

    public static Gender valueOf(char gender) {
        return (Gender) map.get(gender);
    }

    public static Gender valueOf(int gender) {
        return (Gender) map.get(gender);
    }

    public char getValue() {
        return value;
    }

    public int getPos(){
        return pos;
    }

    public  double getHeightFactor() {
        return 0;
    }

    public  double getWeightFactor() {
        return 0;
    }

    public  double getAgeFactor() {
        return 0;
    }

    public  int getAddingTerm() {
        return 0;
    }
}
