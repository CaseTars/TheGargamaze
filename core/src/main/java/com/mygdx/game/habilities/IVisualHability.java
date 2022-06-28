package com.mygdx.game.habilities;

public interface IVisualHability {
    public boolean isRunning();
    public boolean onCooldown();
    public float time();
    public boolean unlocked();	
    public char type();
}
