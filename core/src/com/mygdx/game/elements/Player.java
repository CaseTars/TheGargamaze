package com.mygdx.game.elements;

import com.mygdx.game.SoundManager;
import com.mygdx.game.exceptions.ObstructedCell;
import com.mygdx.game.interfaces.ILantern;
import com.mygdx.game.interfaces.IPlayer;
import com.mygdx.game.interfaces.ISpaceCommand;

public class Player extends Element implements IPlayer{
	private ISpaceCommand space;
	private char variation;
	private ILantern lantern;
	double timeRemaining = 30000;
	double totalTime = timeRemaining;
	
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
            space.move(this, xi, yi);
        } catch (ObstructedCell e) {
    	    SoundManager.playWallHit();
            x = xi;
            y = yi;
        }
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
        // Mandar tempo para habilidades.
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
        return (float)(timeRemaining/totalTime);
    }

	@Override
	public void commandAction() {
		space.action(x, y, variation);
	}

	@Override
	public void commandDeaction() {
		space.deaction(x, y);
	}
    
}
