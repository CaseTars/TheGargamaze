package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class ViewTutorial extends View {
    private Rectangle button;
    private Texture imgButton;
    
    public ViewTutorial() {
        super();
        
        fadingIn = false;
        fadingOut = false;
        showCommands = true;
        imgButton = new Texture(Gdx.files.internal("./images/screens/backtomenu.png"));

        float width = 115;
        float height = width*115f/366f;
        button = new Rectangle((800-width)/2f,10,width,height);
    }
    
    public void connect(ControlTutorial control) {
        control.connectScreen(this);
        control.connect(game);
        control.setButtonPos(button);
    }
    
    public void showCommands(boolean show) {
        // não altera, sempre mostrando os comandos.
    }
    
    @Override
    public void render(float delta) {
        super.render(delta);
        drawButton();
    }

    private void drawButton() {
        batch.begin();
        batch.setColor(1f,1f,1f,1f);
        batch.draw(imgButton, button.x, button.y, button.width, button.height);
        batch.end();
    }
}
