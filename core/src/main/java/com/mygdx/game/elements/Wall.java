package com.mygdx.game.elements;

import com.mygdx.game.elements.element.Element;

public class Wall extends Element{
    public Wall(int x, int y) {
        super(x, y);
    }
    
    @Override
    public int obstructionLevel() {
        return 2;
    }

    @Override
    public char type() {
        return 'W';
    }

    @Override
    public char variation() {
        return 'N';
    }

    @Override
    public char state() {
        return 0;
    }
    
}
