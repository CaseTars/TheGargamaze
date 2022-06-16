package com.mygdx.game.elements;

public class Wall extends Element{
    public Wall(int x, int y) {
        super(x, y);
    }
    
    @Override
    public int obstructionLevel() {
        return 1;
    }

    @Override
    public char type() {
        return 'W';
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
