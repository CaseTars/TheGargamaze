package com.mygdx.game.elements.player;

import com.mygdx.game.interfaces.IPosition;

public interface IPlayerBH {
    public void updateTimeRemaining(float t);
    public IPosition getIPosition();
    public boolean hasCrystal(char variation);
}
