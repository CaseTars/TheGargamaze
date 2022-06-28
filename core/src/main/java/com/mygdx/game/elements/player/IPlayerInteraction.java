package com.mygdx.game.elements.player;

import com.mygdx.game.elements.Crystal;
import com.mygdx.game.interfaces.IPosition;
import com.mygdx.game.interfaces.IVisualEffect;

public interface IPlayerInteraction {
    public char variation();
    
    public void addEffect(IVisualEffect effect);
    public void changeRadius(int change);
    public IPosition getIPosition();
    
    public void setPhantom(boolean phantom);
    
    public void addCrystal(Crystal c);
    public boolean hasCrystal(char variation);
}
