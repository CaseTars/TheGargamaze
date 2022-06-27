package com.mygdx.game.elements;

public class HardGate extends Gate {

    public HardGate(int x, int y) {
        super(x, y);
    }

    public int obstructionLevel() {
        return closed ? 3 : 0;
    }
    
    public char variation() {
    	return 'H';
    }

}
