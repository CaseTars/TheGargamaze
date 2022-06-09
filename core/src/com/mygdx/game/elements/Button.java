package com.mygdx.game.elements;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.interfaces.IGate;

public class Button extends Element{
	private char allowed;
    private Array<IGate> gates = new Array<IGate>();

	
	public Button(int x, int y, char allowed) {
		super(x, y);
		this.allowed = allowed;
	}

	public void connectGate(IGate gate) {
		gates.add(gate); 
	}
	
	public void action(char variation) {
		if(variation == allowed) {
			for(IGate gate: gates)
				gate.open();     //e se for de segurar?
		}
		else {
			System.out.println("Jogador sem permissao para abrir");
		}
	}
    
    public void deaction() {
    	for(IGate gate: gates)
			gate.close();
    }

	@Override
	public char type() {
		return 0;
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
