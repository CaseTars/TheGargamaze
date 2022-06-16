package com.mygdx.game.elements;

import com.mygdx.game.SoundManager;
import com.mygdx.game.interfaces.IGate;

public class Gate extends Element implements IGate{
	private boolean closed;
	
	public Gate(int x, int y) {
		super(x, y);
		closed = true;
	}

	public int obstructionLevel() {
		return closed ? 1 : 0;
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
		return 0;
	}

	@Override
	public char state() {
		if(closed) return 'c';
		else return 'o';
	}

}
