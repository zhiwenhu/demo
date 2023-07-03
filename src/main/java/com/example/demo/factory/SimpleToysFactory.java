package com.example.demo.factory;

public class SimpleToysFactory {
    public  static final  int INT_TOY_TYPE = 1; // CarToy

    public static IToys createToys(int type) {
        switch (type) {
            case 1:
                return  new CarToys();
            default:
                return null;
        }
    }
}
