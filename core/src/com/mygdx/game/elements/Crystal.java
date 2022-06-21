package com.mygdx.game.elements;

import com.mygdx.game.interfaces.ISpaceCrystal;
import com.mygdx.game.interfaces.ILantern;
import com.mygdx.game.interfaces.IPlayerInteraction;

public class Crystal extends Element {
    private ISpaceCrystal space;
	private char variation;
	private ILantern lantern;
	
	public Crystal(int x, int y, char variation) {
		super(x, y);
		this.variation = variation;
	}
	
	public void connect(ISpaceCrystal space) {
	    this.space = space;
	}
	
    public void connect(ILantern lantern) {
        this.lantern = lantern;
        lantern.setRadius(1);
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
	    lantern.turnOn();
	    space.insert(this);
	}
	
	public void leave() {
        lantern.turnOff();
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
