package com.mygdx.game.screens.tutorial;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.app.IGameControl;
import com.mygdx.game.screens.game.control.Control;

public class ControlTutorial extends Control {
    private Rectangle buttonPos;
    private IGameControl game;
    private ViewTutorial screen;

    public void connect(IGameControl game) {
        this.game = game;
    }
    
    public void connectScreen(ViewTutorial screen) {
        this.screen = screen;
    }

    public void setButtonPos(Rectangle buttonPos) {
        this.buttonPos = buttonPos;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3();
        touchPos.set(screenX, screenY, 0);
        
        screen.unproject(touchPos);
        float posX = touchPos.x;
        float posY = touchPos.y;
        
        if(buttonPos.contains(posX, posY)) {
            game.setScreen(0);
        }
    
        return false;
    }

}
