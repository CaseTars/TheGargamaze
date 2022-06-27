package com.mygdx.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.interfaces.IGameControl;

public class EndControl implements InputProcessor {
    private Rectangle buttonPos;
    private EndScreen screen;
    private IGameControl game;
    
    public void connect(EndScreen screen) {
        this.screen = screen;
    }

    public void connect(IGameControl game) {
        this.game = game;
    }
    
    public void setButtonPos(Rectangle buttonPos) {
        this.buttonPos = buttonPos;
    }
    
    @Override
    public boolean keyDown(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY, 0);
        
        screen.unproject(touchPos);
        float posX = touchPos.x;
        float posY = touchPos.y;
        
        if(buttonPos.contains(posX, posY)) {
            SoundManager.stopEnd();
            game.setScreen(0);
        }
    
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        // TODO Auto-generated method stub
        return false;
    }

}
