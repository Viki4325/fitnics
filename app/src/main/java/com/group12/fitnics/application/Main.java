package com.group12.fitnics.application;

public class Main {

    private static String dbName = "FT";

    public static void main(String[] args) {
        System.out.println("Should be doing something in main()?");
    }

    public static void setDBPathName(final String name) {
        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbName = name;
    }

    public static String getDBPathName() {return dbName;}
}
