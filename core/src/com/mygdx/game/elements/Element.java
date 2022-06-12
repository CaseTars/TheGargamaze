package com.mygdx.game.elements;

import com.mygdx.game.interfaces.IElement;
import com.mygdx.game.interfaces.IPlayerEffect;

public abstract class Element implements IElement {
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
    
    public void action(char variation) {}
    
    public void deaction() {}
    
    public void interact(IPlayerEffect player) {}
}
