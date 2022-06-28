package com.mygdx.game.screens.game;

import com.mygdx.game.interfaces.IPosition;
import com.mygdx.game.screens.game.space.ISpaceIluminate;

public class Lantern implements ILantern{
    private int x, y, radius = 2;
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
        float maxDist = radius;//*1.41f;
        
        for(int dx = -radius; dx<=radius; dx++)
            for(int dy = -radius; dy<=radius; dy++) {
                float dist = (float) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)) - 1;
                if(dist>radius-0.5f) continue;
                float clarity = 1f - (dist/maxDist)*(2f/3f);
                space.iluminate(getX() + dx, getY() + dy, clarity);
            }
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
        space.updateVisibility();
    }

    @Override
    public void turnOn() {
        on = true;
        space.updateVisibility();
    }

}
