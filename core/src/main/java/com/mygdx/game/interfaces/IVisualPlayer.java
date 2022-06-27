package com.mygdx.game.interfaces;

import com.badlogic.gdx.utils.Array;

public interface IVisualPlayer extends ITime{
    public float timeRemaining();
    
    public Array<Character> getInventory();
    
    public Array<IVisualHability> getHabilities();
}
