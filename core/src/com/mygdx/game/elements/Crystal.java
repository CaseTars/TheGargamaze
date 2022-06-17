package com.mygdx.game.elements;

import com.mygdx.game.interfaces.ISpaceCrystal;
import com.mygdx.game.interfaces.IPlayerInteraction;

public class Crystal extends Element{
    ISpaceCrystal space;
	char variation;

	public Crystal(int x, int y, char var) {
		super(x, y);
		this.variation = var;
	}
	
	public void connect(ISpaceCrystal space) {
	    this.space = space;
	}
	
	@Override
	public void action(IPlayerInteraction player) {
	    player.addCrystal(this);
	    leave();
	}
	
	public void setX(int x) {
	    this.x = x;
	}
    
    public void setY(int y) {
        this.y = y;
    }
	
	public void enter() {
	    space.insert(this);
	}
	
	public void leave() {
        space.remove(this);
    }
	
	public boolean canEnter() {
	    return space.obstructionLevel(x, y) == 0;
	}
	
	@Override
	public int obstructionLevel() {
	    return 1;
	}

	@Override
	public char type() {
		return 'C';
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
