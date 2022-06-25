package com.mygdx.game.habilities;

import com.mygdx.game.SoundManager;
import com.mygdx.game.interfaces.IControllerTimeOut;
import com.mygdx.game.interfaces.IPlayerSwitchHability;
import com.mygdx.game.interfaces.ISpaceSwitchHability;
import com.mygdx.game.interfaces.IViewSwitchHability;

public class SwitchPlacesHability extends Hability {
    private IPlayerSwitchHability pCase, pTars;
    private IControllerTimeOut control;
    private IViewSwitchHability view;
    private ISpaceSwitchHability space;
    
    private boolean trocou;
    private int cX, cY, tX, tY;
    
    public SwitchPlacesHability(float duration, float cooldownDuration) {
        super(duration, cooldownDuration);
    }
    
    public void connect(IPlayerSwitchHability pCase, IPlayerSwitchHability pTars) {
        this.pCase = pCase;
        this.pTars = pTars;
    }
    
    public void connect(IViewSwitchHability view) {
        this.view = view;
    }
    
    public void connect(ISpaceSwitchHability space) {
        this.space = space;
    }

    @Override
    public void update(float t) {
        super.update(t);
        if(!isRunning) return;
        
        if(time < (0.45f)*duration) {
            view.setOpacity(time/(0.45f * duration));
        }
        else if(time < (0.55f)*duration) {
            switchPlaces();
            view.setOpacity(1);
        }
        else {
            view.setOpacity((duration-time)/(0.45f * duration));
        }
    }

    @Override
    protected void applyEffect() {
        cX = pCase.getX();
        cY = pCase.getY();
        tX = pTars.getX();
        tY = pTars.getY();
        
        pCase.leave();
        pTars.leave();
        
        if(space.obstructionLevel(cX, cY) > 1 || space.obstructionLevel(tX, tY) > 1) { // cancelar
            time = 0;
            isRunning = false;
            onCooldown = false;
        }
        else {
            SoundManager.playPlayerTeleport();
            trocou = false;
            control.setTimeOut(duration);
            view.startAnimation(cX, cY, tX, tY);
        }
        
        pCase.enter();
        pTars.enter();
    }
    
    protected void removeEffect() {
        view.stopAnimation();
    }
    
    private void switchPlaces() {
        if(trocou) return;
        pCase.moveTo(tX, tY);
        pTars.moveTo(cX, cY);
        trocou = true;
    }

    public void connect(IControllerTimeOut control) {
        this.control = control;
    }

    @Override
    public void update() {
        if(pCase.hasCrystal('1') && pTars.hasCrystal('1'))
            unlock();
        else
            lock();
    }

	@Override
	public char type() {
		// TODO Auto-generated method stub
		return 'T';
	}

}
