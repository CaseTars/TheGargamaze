package com.mygdx.game.interfaces;

import com.mygdx.game.elements.Crystal;

public interface IPlayerInteraction {
    public char variation();
    
    public void addEffect(IVisualEffect effect);
    public void changeRadius(int change);
    public IPosition getIPosition();
    
    public void setPhantom(boolean phantom);
    
    public void addCrystal(Crystal c);
    public boolean hasCrystal(char variation);
}
