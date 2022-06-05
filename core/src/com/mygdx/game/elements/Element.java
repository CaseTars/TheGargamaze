package com.mygdx.game.elements;

import com.mygdx.game.interfaces.IVisual;

public abstract class Element implements IVisual {
	protected int x;
	protected int y;
	
	public Element(int x, int y) {
		this.x = x;
		this.y = y;
	}

    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
    
    public boolean isObstructed() {
    	return false;
    }

    // From IVisual
    public abstract char type();
    public abstract char variation();
    public abstract char state();
}
