package com.mygdx.game.elements;

import com.mygdx.game.interfaces.IGate;

public class Button extends Element{
	IGate gate;
	private char allowed;
	
	public Button(int x, int y, char allowed) {
		super(x, y);
		this.allowed = allowed;
	}

	public void connectGate(IGate gate) {
		this.gate = gate;
	}
	
	public void openGate(char variation) {
		if(variation == allowed) {
			gate.open();     //e se for de segurar?
		}
		else {
			System.out.println("Jogador sem permissao para abrir");
		}
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
