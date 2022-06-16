package com.mygdx.game.habilities;

import com.mygdx.game.interfaces.IHability;

public abstract class Hability implements IHability{
    private boolean isUnlocked = false;
    protected boolean onCooldown = false;
    protected boolean isRunning  = false;
    protected float duration;
    private float cooldownDuration;
    protected float time;
    
    public Hability(float duration, float cooldownDuration) {
        this.duration = duration;
        this.cooldownDuration = cooldownDuration;
    }
    
    public void update(float t) {
        if(time==0) return;
        time -= t;
        if(time < 0) {
            if(onCooldown) {
                onCooldown = false;
                time = 0;
            }
            else if(isRunning) {
                isRunning = false;
                onCooldown = true;
                time = cooldownDuration;
                removeEffect();
            }
                
        }
    }
    
    public void use() {
        if(!isUnlocked || isRunning || onCooldown) return;
        isRunning = true;
        time = duration;
        applyEffect();
    }
    
    protected abstract void applyEffect();
    protected void removeEffect() {}
    
    public boolean isRunning() {
        return isRunning;
    }
    
    public boolean onCooldown() {
        return onCooldown;
    }
    
    public float time() {
        if(isRunning)
            return time/duration;
        return time/cooldownDuration;
    }
    
    public void unlock() {
        isUnlocked = true;
    }
}
