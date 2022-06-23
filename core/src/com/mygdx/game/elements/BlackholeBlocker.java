package com.mygdx.game.elements;

public class BlackholeBlocker extends Element {
	private boolean open = false;
	
	public BlackholeBlocker(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int obstructionLevel() {
    	return open ? 0 : 3;
    }
	
	public void open() {
		open = true;
	}
	
	public void close() {
		open = false;
	}

	@Override
	public char type() {
		return 0;
	}

	@Override
	public char variation() {
		return 0;
	}

	@Override
	public char state() {
		return 0;
	}
}
