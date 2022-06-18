package com.mygdx.game;

import com.mygdx.game.interfaces.ILantern;
import com.mygdx.game.interfaces.IPosition;
import com.mygdx.game.interfaces.ISpaceIluminate;

public class Lantern implements ILantern{
    private int x, y, radius = 1;
    private ISpaceIluminate space;
    private IPosition element;
    private boolean on = true;
    
    void connect(IPosition element) {
        this.element = element;
    }
    
    void connect(ISpaceIluminate space) {
        this.space = space;
    }
    
    private int getX() {
        if(element == null) return x;
        return element.getX();
    }
    
    private int getY() {
        if(element == null) return y;
        return element.getY();
    }
    
    @Override
    public void iluminate() {
        if(!on) return;
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

    @Override
    public void turnOff() {
        on = false;
    }

    @Override
    public void turnOn() {
        on = true;
    }

}
