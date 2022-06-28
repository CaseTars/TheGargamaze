package com.mygdx.game.elements.blackhole;

import com.mygdx.game.SoundManager;
import com.mygdx.game.app.IGameControl;
import com.mygdx.game.elements.player.IPlayerBH;
import com.mygdx.game.interfaces.IPosition;
import com.mygdx.game.interfaces.ITime;
import com.mygdx.game.interfaces.IUpdate;
import com.mygdx.game.screens.game.space.ISpaceEdit;

public class Blackhole implements IUpdate, ITime, IVisualBH {
	private int x = 15,
				y = 15,
				radius = 1;
	
	private IPlayerBH pCase, pTars;
	private float incCase = 0, incTars = 0;
	private IPosition posCase, posTars;
	
	private IGameControl game;
	
	boolean gameOver = false;
	
	public Blackhole(ISpaceEdit space) {
	    
        for(int dx = -radius; dx<=radius; dx++)
            for(int dy = -radius; dy<=radius; dy++)
                space.insert(new BlackholePart(x+dx,y+dy));
	}
	
	public void connect(IPlayerBH pCase, IPlayerBH pTars) {
	    this.pCase = pCase;
	    this.pTars = pTars;
	}
	
	public void connect(IGameControl game) {
        this.game = game;
    }

    @Override
    public void update() {
        posCase = pCase.getIPosition();
        posTars = pTars.getIPosition();
        boolean caseIn = inside(posCase);
        boolean tarsIn = inside(posTars);
        
        if(caseIn)
            incCase = 20000;
        else
            incCase = - (float) distanceFactor(posCase);
        
        if(tarsIn)
            incTars = 20000;
        else
            incTars = - (float) distanceFactor(posTars);
        
        if(caseIn && tarsIn && pCase.hasCrystal('3') && pTars.hasCrystal('4') && !gameOver) {
            gameOver = true;
            SoundManager.playVictory();
            game.gameOver(true);
        }
    }

    private double distanceFactor(IPosition player) {
        double distToCenter = distance(player.getX(),player.getY(),x,y);
        return Math.pow(distToCenter, 3);
    }
    
    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }

    private boolean inside(IPosition player) {
        int xp = player.getX(),
            yp = player.getY();
        if(xp < x-radius || xp > x+radius || yp < y-radius || yp > y+radius)
            return false;
        return true;
    }
    
    @Override
    public void update(float t) {
        pCase.updateTimeRemaining(t * incCase);
        pTars.updateTimeRemaining(t * incTars);
    }
    
    // From IVisualBH
    @Override
    public int getX() {
        return x-radius;
    }

    @Override
    public int getY() {
        return y-radius;
    }

    @Override
    public int getSize() {
        return 2*radius+1;
    }
}