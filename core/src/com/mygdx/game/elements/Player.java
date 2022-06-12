package com.mygdx.game.elements;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SoundManager;
import com.mygdx.game.exceptions.ObstructedCell;
import com.mygdx.game.habilities.Hability;
import com.mygdx.game.habilities.VisionRadiusHability;
import com.mygdx.game.habilities.VisualEffect;
import com.mygdx.game.interfaces.ILantern;
import com.mygdx.game.interfaces.IPlayer;
import com.mygdx.game.interfaces.IPosition;
import com.mygdx.game.interfaces.ISpaceCommand;
import com.mygdx.game.interfaces.IVisualEffect;

public class Player extends Element implements IPlayer{
	private ISpaceCommand space;
	private char variation;
	private ILantern lantern;
	private double timeRemaining = 30000;
	private double totalTime = timeRemaining;
	private Array<IVisualEffect> effects = new Array<IVisualEffect>();
	private Hability[] habilities = new Hability[3];
	
	public Player(int x, int y, char variation) {
		super(x, y);
		this.variation = variation;
		
		VisionRadiusHability hability1 = new VisionRadiusHability(10, 5, 2);
		hability1.connect(this);
		hability1.unlock();
		this.habilities[0] = hability1;
		
        //this.habilities[1] = new 
        //this.habilities[2] = new 
	}
	
	public void connect(ISpaceCommand space) {
		this.space = space;
	}
	
    public void connect(ILantern lantern) {
        this.lantern = lantern;
    }
	
    // From ICommand
	@Override
	public void moveLeft() {
        move(-1,0);
	}

	@Override
	public void moveRight() {
        move(+1,0);
	}

	@Override
	public void moveUp() {
        move(0,+1);
	}

	@Override
	public void moveDown() {
        move(0,-1);
	}
	
	private void move(int dx, int dy) {
	    int xi = x, yi = y;
	    
	    x += dx;
        y += dy;
        try {
            space.move(this, xi, yi, x, y);
            checkEffects();
        } catch (ObstructedCell e) {
    	    SoundManager.playWallHit();
            x = xi;
            y = yi;
        }
	}

    @Override
    public void commandAction() {
        space.action(x, y, variation);
    }

    @Override
    public void commandDeaction() {
        space.deaction(x, y);
    }

    @Override
    public void useHability(int i) {
        habilities[i].use();
    }
	
	// From IVisual
	@Override
	public char type() {
		return 'P';
	}

	@Override
	public char variation() {
		return variation;
	}

	@Override
	public char state() {
		return 0;
	}
	
	// From ITime
    @Override
    public void update(float t) {
        timeRemaining -= t*distanceFactor();
        updateEffects(t);
        updateHabilities(t);
    }

    private double distanceFactor() {
        double distToCenter = distance(x,y,15,15);
        return Math.pow(distToCenter, 2);
    }
    
    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }
    // From IVisualPlayer
    @Override
    public float timeRemaining() {
        return (float) (timeRemaining/totalTime);
    }
	
	// From IPlayerEffect
    @Override
    public void addEffect(IVisualEffect effect) {
        effects.add(effect);
        effect.applyEffect();
    }

    @Override
    public void changeRadius(int change) {
        lantern.changeRadius(change);
    }
    
    @Override
    public IPosition getIPosition() {
        return this;
    }
    
    private void checkEffects() {
        for(IVisualEffect effect : effects)
            if(effect.isOver()) {
                effect.removeEffect();
                effects.removeValue(effect, true);
            }
    }
    
    private void updateEffects(float t) {
        for(IVisualEffect effect : effects)
            effect.update(t);
        checkEffects();
    }
    
    private void updateHabilities(float t) {
        for(Hability hability : habilities) {
            if(hability == null) break;
            hability.update(t);
        }
    }

}
