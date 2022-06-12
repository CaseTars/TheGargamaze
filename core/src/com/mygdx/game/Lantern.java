package com.mygdx.game;

import com.mygdx.game.interfaces.ILantern;
import com.mygdx.game.interfaces.IPosition;
import com.mygdx.game.interfaces.ISpaceIluminate;

public class Lantern implements ILantern{
    private int x, y, radius = 1;
    private ISpaceIluminate space;
    private IPosition player;
    
    void connect(IPosition player) {
        this.player = player;
    }
    
    void connect(ISpaceIluminate space) {
        this.space = space;
    }
    
    private int getX() {
        if(player == null) return x;
        return player.getX();
    }
    
    private int getY() {
        if(player == null) return y;
        return player.getY();
    }
    
    @Override
    public void iluminate() {
        for(int dx = -radius; dx<=radius; dx++)
            for(int dy = -radius; dy<=radius; dy++)
                space.iluminate(getX() + dx, getY() + dy);
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public void setRadius(int newRadius) {
        radius = newRadius;
        space.updateVisibility();
    }

    @Override
    public void changeRadius(int change) {
        setRadius(radius + change);
    }

}
