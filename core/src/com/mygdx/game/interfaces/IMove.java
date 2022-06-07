package com.mygdx.game.interfaces;

import com.mygdx.game.elements.Element;
import com.mygdx.game.exceptions.ObstructedCell;

public interface IMove {
	public void move(Element e, int xi, int yi) throws ObstructedCell;
}
