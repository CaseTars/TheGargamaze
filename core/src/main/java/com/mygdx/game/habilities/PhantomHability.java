package com.mygdx.game.habilities;

import com.mygdx.game.elements.player.IPlayerInteraction;

public class PhantomHability extends Hability {
    IPlayerInteraction player;

    public PhantomHability(float duration, float cooldownDuration) {
        super(duration, cooldownDuration);
    }
    
    public void connect(IPlayerInteraction player) {
        this.player = player;
    }

    @Override
    protected void applyEffect() {
        player.setPhantom(true);
    }

    @Override
    protected void removeEffect() {
    	super.removeEffect();
        player.setPhantom(false);
    }

    @Override
    public void update() {
        if(player.hasCrystal('2'))
            unlock();
        else {
            lock();
            if(isRunning)
                removeEffect();
        }
    }

	@Override
	public char type() {
		// TODO Auto-generated method stub
		return 'P';
	}

}
