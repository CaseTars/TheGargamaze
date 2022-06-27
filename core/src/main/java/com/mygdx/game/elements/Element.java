package com.mygdx.game.elements;

import com.mygdx.game.interfaces.IElement;
import com.mygdx.game.interfaces.IPlayerInteraction;
import com.mygdx.game.interfaces.IUpdate;

public abstract class Element implements IElement{
	protected int x;
	protected int y;
	protected IUpdate cell;
	
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
    
    public int obstructionLevel() {
    	return 0;
    }
    
    public void setCell(IUpdate cell) {
        this.cell = cell;
    }
    
    public void action(IPlayerInteraction player) {}
    
    public void deaction(IPlayerInteraction player) {}
    
    public void interact(IPlayerInteraction player) {}
}
