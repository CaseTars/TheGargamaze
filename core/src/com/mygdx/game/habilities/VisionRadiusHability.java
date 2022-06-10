package com.mygdx.game.habilities;

import com.mygdx.game.interfaces.IPlayerEffect;

public class VisionRadiusHability extends Hability {
    int radiusGain;
    IPlayerEffect player;
    
    public VisionRadiusHability(float duration, float cooldownDuration,
                                int radiusGain) {
        super(duration, cooldownDuration);
        this.radiusGain = radiusGain;
    }
    
    public void connect(IPlayerEffect player) {
        this.player = player;
    }

    @Override
    protected void applyEffect() {
        VisualEffect effect = new VisualEffect(player, radiusGain, duration);
        player.addEffect(effect);
    }
}
