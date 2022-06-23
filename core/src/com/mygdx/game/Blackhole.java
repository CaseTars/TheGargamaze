package com.mygdx.game;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.elements.BlackholeBlocker;
import com.mygdx.game.interfaces.ISpaceEdit;

public class Blackhole {
	private int x = 15,
				y = 15,
				radius = 3;
	private Array<BlackholeBlocker> bhbs = new Array<BlackholeBlocker>();
	
	public void createBlockers(ISpaceEdit space) {
        for(int dx = -radius; dx<=radius; dx++)
            for(int dy = -radius; dy<=radius; dy++) {
            	BlackholeBlocker bhb = new BlackholeBlocker(x + dx, y + dy);
                space.insert(bhb);
            }
	}
}
