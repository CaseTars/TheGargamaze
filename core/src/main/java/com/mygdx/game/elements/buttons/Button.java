package com.mygdx.game.elements.buttons;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.elements.Element;
import com.mygdx.game.elements.player.IPlayerInteraction;
import com.mygdx.game.interfaces.IGate;

public class Button extends Element{
    private Array<IGate> gates = new Array<IGate>();
	boolean pressed = false;
	private char playerPressing;
	
	public Button(int x, int y) {
		super(x, y);
	}

	public void connect(IGate gate) {
		gates.add(gate); 
	}
	
	@Override
	public void action(IPlayerInteraction player) {
	    playerPressing = player.variation();
	    pressed = true;
		for(IGate gate: gates)
			gate.open();
        cell.update();
	}
    
	@Override
    public void deaction(IPlayerInteraction player) {
	    if(player.variation() != playerPressing) return;
        pressed = false;
        for(IGate gate: gates)
            gate.close();
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
		return pressed ? 'p' : 'f';
	}
    
	
    public static Button create(int x, int y, boolean hasSpring, char allowed) {
        if(hasSpring) {
            if(allowed == 'A')
                return new Button(x,y);
            else
                return new ButtonSpecific(x,y,allowed);
        }
        else {
            if(allowed == 'A')
                return new ButtonNoSpring(x,y);
            else
                return new ButtonSpecificNoSpring(x,y,allowed);
        }
    }

}
