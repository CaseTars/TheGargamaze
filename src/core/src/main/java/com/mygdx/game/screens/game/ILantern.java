package com.mygdx.game.screens.game;

public interface ILantern {
    public void iluminate();
    public int getRadius();
    public void setRadius(int newRadius);
    public void changeRadius(int change);
    public void turnOff();
    public void turnOn();
}
