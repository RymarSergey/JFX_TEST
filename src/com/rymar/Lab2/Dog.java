package com.rymar.Lab2;

public class Dog extends Creature implements Creep{

    @Override
    public String creep() {
        return "I can creep!";
    }

    @Override
    public String whoAmI() {
        return "I am a Dog!";
    }
}
