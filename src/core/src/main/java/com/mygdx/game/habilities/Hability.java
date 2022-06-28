package com.mygdx.game.habilities;

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
            else if(isRunning)
                removeEffect();
        }
    }
    
    public void use() {
        if(!isUnlocked || isRunning || onCooldown) return;
        isRunning = true;
        time = duration;
        applyEffect();
    }
    
    protected abstract void applyEffect();
    
    protected void removeEffect() {
        isRunning = false;
        onCooldown = true;
        time = cooldownDuration;
    }
    
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
    
    protected void unlock() {
        isUnlocked = true;
    }
    
    protected void lock() {
        isUnlocked = false;
    }
    
    public boolean unlocked() {
		// TODO Auto-generated method stub
		return isUnlocked;
	}
}
