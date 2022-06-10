package com.mygdx.game.elements;

import com.mygdx.game.habilities.VisualEffect;
import com.mygdx.game.interfaces.IPlayerEffect;

public class Darkness extends Element {

    public Darkness(int x, int y) {
        super(x,y);
    }

    public void interact(IPlayerEffect player) {
        VisualEffect effect = new VisualEffect(player, -1, player.getIPosition());
        player.addEffect(effect);
    }
    
    @Override
    public char type() {
        return 'D';
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
