package com.mygdx.game.interfaces;

public interface IPlayerSwitchHability extends IPosition, ICommand {
    public void leave();
    public void enter();
    public boolean hasCrystal(char variation);
}
