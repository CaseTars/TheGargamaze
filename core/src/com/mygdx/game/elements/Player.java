package com.mygdx.game.elements;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SoundManager;
import com.mygdx.game.exceptions.ObstructedCell;
import com.mygdx.game.habilities.VisionRadiusHability;
import com.mygdx.game.habilities.VisualEffect;
import com.mygdx.game.interfaces.IHability;
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
	private IHability[] habilities = new IHability[3];
	private boolean phantom = false;
	private Array<Crystal> crystals = new Array<Crystal>();
	
	public Player(int x, int y, char variation) {
		super(x, y);
		this.variation = variation;
	}
	
	public void connect(ISpaceCommand space) {
		this.space = space;
	}
	
    public void connect(ILantern lantern) {
        this.lantern = lantern;
    }
    
    public void connect(IHability h, int i) {
        this.habilities[i] = h;
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
	
	@Override
    public void moveTo(int x, int y) {
        move(x - this.x, y - this.y);
    }
	
	private void move(int dx, int dy) {
	    int xi = x, yi = y;
	    
	    x += dx;
        y += dy;
        try {
            space.move(this, xi, yi, x, y, phantom);
            checkEffects();
        } catch (ObstructedCell e) {
    	    SoundManager.playWallHit();
            x = xi;
            y = yi;
        }
	}

    @Override
    public void commandAction() {
        space.action(x, y, this);
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
		return phantom ? 'P' : 'N';
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
        for(IHability hability : habilities) {
            if(hability == null) break;
            hability.update(t);
        }
    }

    @Override
    public void leave() {
        space.remove(this);
    }

    @Override
    public void enter() {
        space.insert(this);
    }

    @Override
    public void setPhantom(boolean phantom) {
        this.phantom = phantom;
    }

    public void addCrystal(Crystal c) {
        crystals.add(c);
        updateHabilities();
    }
    
    public void dropCrystal() {
        if(crystals.size == 0) return;
        Crystal removed = crystals.removeIndex(crystals.size - 1);
        removed.setX(x);
        removed.setY(y);
        if(removed.canEnter()) {
            removed.enter();
            updateHabilities();
        }
        else {
            crystals.add(removed);
        }
    }

    private void updateHabilities() {
        for(IHability hability : habilities)
            hability.update();
    }

    @Override
    public boolean hasCrystal(char variation) {
        for(Crystal crystal : crystals)
            if(crystal.variation() == variation)
                return true;
        return false;
    }

}
