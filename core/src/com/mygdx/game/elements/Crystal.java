package com.mygdx.game.elements;

public class Crystal extends Element{
	char variation;

	public Crystal(int x, int y, char var) {
		super(x, y);
		this.variation = variation;
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
