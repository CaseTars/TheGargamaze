package com.mygdx.game.elements.player;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.habilities.IVisualHability;
import com.mygdx.game.interfaces.ITime;

public interface IVisualPlayer extends ITime{
    public float timeRemaining();
    
    public Array<Character> getInventory();
    
    public Array<IVisualHability> getHabilities();
}
