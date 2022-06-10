package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.interfaces.ICommand;

public class Control implements InputProcessor {
    private ICommand pCase;
    private ICommand pTars;
    
    public void conectCase(ICommand pCase) {
    	this.pCase = pCase;
    }
    
    public void conectTars(ICommand pTars) {
    	this.pTars = pTars;
    }
    
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.LEFT)
            pCase.moveLeft();
        else if(keycode == Input.Keys.RIGHT)
        	pCase.moveRight();
        else if(keycode == Input.Keys.UP)
        	pCase.moveUp();
        else if(keycode == Input.Keys.DOWN)
        	pCase.moveDown();
        
        else if(keycode == Input.Keys.A)
        	pTars.moveLeft();
        else if(keycode == Input.Keys.D)
        	pTars.moveRight();
        else if(keycode == Input.Keys.W)
        	pTars.moveUp();
        else if(keycode == Input.Keys.S)
        	pTars.moveDown();
        
        else if(keycode == Input.Keys.ENTER)
        	pCase.commandAction();
        else if(keycode == Input.Keys.E)
        	pTars.commandAction();
        return false;
    }

    public boolean keyUp(int keycode) {
    	if(keycode == Input.Keys.ENTER)
        	pCase.commandDeaction();
        else if(keycode == Input.Keys.E)
        	pTars.commandDeaction();
        return false;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved(int x, int y) {
        return false;
    }

    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}