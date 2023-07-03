package com.example.demo.factory;

public class CarToys implements IToys{

    @Override
    public void desc() {
        System.out.println("I am a car. I can run fast.");
    }
}
