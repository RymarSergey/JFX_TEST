package com.rymar.Lab2;

public class Snake extends Creature implements Wriggle {

    @Override
    public String wriggle() {
        return "I can wriggle !!";
    }

    @Override
    public String creep() {
        return "I can creep !!";
    }

    @Override
    public String whoAmI() {
        return "I am a Snake";

    }
}
