package com.mygdx.game.elements;

public class BlackholePart extends Element {

    public BlackholePart(int x, int y) {
        super(x, y);
    }
    
    @Override
    public int obstructionLevel() {
        return 1;
    }

    @Override
    public char type() {
        return 'Z';
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
