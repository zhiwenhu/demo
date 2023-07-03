package com.example.demo.factory;

public class CarToysFactory implements IToysFactory{

    @Override
    public IToys createToys() {
        return new CarToys();
    }
}
