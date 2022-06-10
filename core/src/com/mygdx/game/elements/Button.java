package com.mygdx.game.elements;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SoundManager;
import com.mygdx.game.interfaces.IGate;

public class Button extends Element{
	private char allowed;
	private boolean hasSpring;
    private Array<IGate> gates = new Array<IGate>();
	
	public Button(int x, int y, boolean hasSpring, char allowed) {
		super(x, y);
		this.allowed = allowed;
		this.hasSpring = hasSpring;
	}
	
	public Button(int x, int y, boolean hasSpring) {
		this(x, y, hasSpring, 'A');
	}

	public void connect(IGate gate) {
		gates.add(gate); 
	}
	
	public void action(char variation) {
		if(variation == allowed || allowed == 'A') {
			for(IGate gate: gates) {
				gate.open();     
				SoundManager.playDoorOpening();
			}
		}
		else {
			System.out.println("Jogador sem permissao para abrir");
		}
	}
    
    public void deaction() {
    	if(hasSpring) {
    		for(IGate gate: gates) {
    			gate.close();	
    			SoundManager.playDoorClosing();
    		}
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
