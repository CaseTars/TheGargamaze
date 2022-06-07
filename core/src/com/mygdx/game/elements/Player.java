package com.mygdx.game.elements;

import com.mygdx.game.exceptions.ObstructedCell;
import com.mygdx.game.interfaces.ILantern;
import com.mygdx.game.interfaces.IMove;
import com.mygdx.game.interfaces.IPlayer;

public class Player extends Element implements IPlayer{
	private IMove space;
	private char variation;
	private ILantern lantern;
	
	public Player(int x, int y, char variation) {
		super(x, y);
		this.variation = variation;
	}
	
	public void connect(IMove space) {
		this.space = space;
	}
	
    public void connect(ILantern lantern) {
        this.lantern = lantern;
    }
	
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
            x = xi;
            y = yi;
        }
	}
	
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

}
