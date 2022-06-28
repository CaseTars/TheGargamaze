package com.mygdx.game.elements.player;

import com.mygdx.game.interfaces.ICommand;
import com.mygdx.game.interfaces.IPosition;

public interface IPlayerSwitchHability extends IPosition, ICommand {
    public void leave();
    public void enter();
    public boolean hasCrystal(char variation);
}
