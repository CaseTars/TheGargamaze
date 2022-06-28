package com.mygdx.game.habilities;

import com.mygdx.game.interfaces.ITime;

public interface IVisualEffect extends ITime {
    public void applyEffect();
    public void removeEffect();
    public boolean isOver();
}
