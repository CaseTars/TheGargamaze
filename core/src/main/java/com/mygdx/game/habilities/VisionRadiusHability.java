package com.mygdx.game.habilities;

import com.mygdx.game.interfaces.IPlayerInteraction;

public class VisionRadiusHability extends Hability {
    int radiusGain;
    IPlayerInteraction player;
    
    public VisionRadiusHability(float duration, float cooldownDuration,
                                int radiusGain) {
        super(duration, cooldownDuration);
        this.radiusGain = radiusGain;
    }
    
    public void connect(IPlayerInteraction player) {
        this.player = player;
    }

    @Override
    protected void applyEffect() {
        VisualEffect effect = new VisualEffect(player, radiusGain, duration);
        player.addEffect(effect);
    }

    @Override
    public void update() {
        if(player.hasCrystal('0'))
            unlock();
        else
            lock();
    }

	@Override
	public char type() {
		// TODO Auto-generated method stub
		return 'V';
	}
	
}
