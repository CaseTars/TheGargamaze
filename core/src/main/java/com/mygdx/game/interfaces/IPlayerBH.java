package com.mygdx.game.interfaces;

public interface IPlayerBH {
    public void updateTimeRemaining(float t);
    public IPosition getIPosition();
    public boolean hasCrystal(char variation);
}
