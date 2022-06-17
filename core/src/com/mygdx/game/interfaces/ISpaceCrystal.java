package com.mygdx.game.interfaces;

import com.mygdx.game.elements.Element;

public interface ISpaceCrystal {
    public void insert(Element e);
    public void remove(Element e);
    public int obstructionLevel(int x, int y);
}
