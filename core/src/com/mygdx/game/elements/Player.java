package com.mygdx.game.elements;

import com.mygdx.game.interfaces.ICommand;
import com.mygdx.game.interfaces.IMove;

public class Player extends Element implements ICommand{
	private IMove space;
	private char variation;
	
	public Player(int x, int y, char variation) {
		super(x, y);
		this.variation = variation;
	}
	
	public void connect(IMove space) {
		this.space = space;
	}
	
	@Override
	public void moveLeft() {
		if(space.move(this, x-1, y))
			x -= 1;
	}

	@Override
	public void moveRight() {
		if(space.move(this, x+1, y))
			x += 1;
	}

	@Override
	public void moveUp() {
		if(space.move(this, x, y+1))
			y += 1;
	}

	@Override
	public void moveDown() {
		if(space.move(this, x, y-1))
			y -= 1;
	}
	
	@Override
	public char type() {
		return 'P';
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
