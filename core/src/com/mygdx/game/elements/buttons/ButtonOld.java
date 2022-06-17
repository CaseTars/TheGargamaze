package com.mygdx.game.elements.buttons;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.elements.Element;
import com.mygdx.game.interfaces.IGate;

public class ButtonOld extends Element{
    private char allowed;
    private char state;
    private boolean hasSpring;
    private Array<IGate> gates = new Array<IGate>();
    
    public ButtonOld(int x, int y, boolean hasSpring, char allowed) {
        super(x, y);
        this.allowed = allowed;
        this.hasSpring = hasSpring;
        this.state = 'f';
    }

    public void connect(IGate gate) {
        gates.add(gate); 
    }
    
    public void action(char variation) {
        if(variation == allowed || allowed == 'A') {
            state = 'p';
            for(IGate gate: gates)
                gate.open();
        }
        else {
            System.out.println("Jogador sem permissao para abrir");
        }
        cell.update();
    }
    
    public void deaction() {
        if(hasSpring) {
            state = 'f';
            for(IGate gate: gates)
                gate.close();
        }
        cell.update();
    }

    @Override
    public char type() {
        return 'B';
    }

    @Override
    public char variation() {
        return 0;
    }

    @Override
    public char state() { 
        return state;
    }

}
