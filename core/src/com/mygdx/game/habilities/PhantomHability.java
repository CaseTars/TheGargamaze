package com.mygdx.game.habilities;

import com.mygdx.game.interfaces.IPlayerEffect;

public class PhantomHability extends Hability {
    IPlayerEffect player;

    public PhantomHability(float duration, float cooldownDuration) {
        super(duration, cooldownDuration);
    }
    
    public void connect(IPlayerEffect player) {
        this.player = player;
    }

    @Override
    protected void applyEffect() {
        player.setPhantom(true);
    }
    
    protected void removeEffect() {
        player.setPhantom(false);
    }
}
