package com.mygdx.game.elements;

import com.mygdx.game.SoundManager;
import com.mygdx.game.interfaces.IGate;

public class Gate extends Element implements IGate{
	protected boolean closed;
	
	public Gate(int x, int y) {
		super(x, y);
		closed = true;
	}

	public int obstructionLevel() {
		return closed ? 2 : 0;
	}
	
	public void close() {
	    if(closed == true) return;
	    
		closed = true;
		cell.update();
        SoundManager.playDoorClosing();
	}
	
	public void open() {
        if(closed == false) return;
        
		closed = false;
        cell.update();
        SoundManager.playDoorOpening();
	}
	
	@Override
	public char type() {
		return 'G';
	}

	@Override
	public char variation() {
		return 'N';
	}

	@Override
	public char state() {
		return closed ? 'c' : 'o';
	}
	
	public static Gate create(int x, int y, char variation) {
	    if(variation == 'N')
	        return new Gate(x, y);
	    else
            return new HardGate(x, y);
	}
}
