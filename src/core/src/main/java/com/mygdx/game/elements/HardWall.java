package com.mygdx.game.elements;

public class HardWall extends Wall {

    public HardWall(int x, int y) {
        super(x, y);
    }

    @Override
    public int obstructionLevel() {
        return 3;
    }

    @Override
    public char variation() {
        return 'H';
    }
}
