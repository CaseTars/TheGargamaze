package com.mygdx.game.interfaces;

import com.mygdx.game.elements.Player;
import com.mygdx.game.exceptions.ObstructedCell;

public interface IMove {
	public void move(Player e, int xi, int yi, int xf, int yf) throws ObstructedCell;
	public void insert(Player e);
    public void remove(Player e);
}
