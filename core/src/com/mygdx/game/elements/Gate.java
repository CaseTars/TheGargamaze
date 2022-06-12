package com.mygdx.game.elements;

import com.mygdx.game.interfaces.IGate;

public class Gate extends Element implements IGate{
	private boolean closed;
	
	public Gate(int x, int y) {
		super(x, y);
		closed = true;
	}

	public boolean isObstructed() {
		return closed;
	}
	
	public void close() {
		closed = true;
	}
	
	public void open() {
		closed = false;
	}
	
	@Override
	public char type() {
		return 'g';
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
