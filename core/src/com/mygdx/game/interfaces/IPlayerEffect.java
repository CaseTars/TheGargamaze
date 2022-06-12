package com.mygdx.game.interfaces;

public interface IPlayerEffect {
    public void addEffect(IVisualEffect effect);
    public void changeRadius(int change);
    public IPosition getIPosition();
}
