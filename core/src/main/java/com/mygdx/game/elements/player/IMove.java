package com.mygdx.game.elements.player;

import com.mygdx.game.exceptions.ObstructedCell;

public interface IMove {
	public void move(Player e, int xi, int yi, int xf, int yf, boolean forced) throws ObstructedCell;
	public void insert(Player e);
    public void remove(Player e);
}
