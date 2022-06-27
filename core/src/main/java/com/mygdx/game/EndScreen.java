package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.interfaces.IGameControl;

public class EndScreen implements Screen {
    private Texture img;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    
    private boolean fadingIn = true;
    private float fadeDuration = 1f;
    private float fadeTime = 0;
    private Texture imgBlack;
    
    private Rectangle button;
    private Texture imgButton;
    
    public EndScreen(IGameControl game, boolean sucess) {
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.update();
        viewport = new FitViewport(800, 480, camera);
        batch = new SpriteBatch();
        
        loadImages(sucess);
        
        float width = 115;
        float height = width*115f/366f;
        button = new Rectangle((800-width)/2f,10,width,height);
        
        EndControl control = new EndControl();
        control.connect(this);
        control.connect(game);
        control.setButtonPos(button);
        
        SoundManager.playEnd();
        
        Gdx.input.setInputProcessor(control);
    }
    
    private void loadImages(boolean sucess) {
        String path = "images/screens/";
        imgBlack  = new Texture(Gdx.files.internal(path + "black.png"));
        imgButton = new Texture(Gdx.files.internal(path + "backtomenu.png"));
        if(sucess)
            img   = new Texture(Gdx.files.internal(path + "sucessScreen.png"));
        else
            img   = new Texture(Gdx.files.internal(path + "failScreen.png"));
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1); //cor do fundo
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        
        draw();
        
        if(fadingIn) {
            drawFadeIn();
            updateFadeIn(delta);
        }
        
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true); // Restore viewport.
    }

    private void draw() {
        batch.begin();
        batch.setColor(1f,1f,1f,1f);
        batch.draw(img, 0, 0, 800, 480);
        batch.draw(imgButton, button.x, button.y, button.width, button.height);
        batch.end();
    }

    private void drawFadeIn() {
        batch.begin();
        batch.setColor(1f,1f,1f, 1-fadeTime/fadeDuration);
        batch.draw(imgBlack, 0, 0, 800, 480);
        batch.end();
    }
    
    private void updateFadeIn(float delta) {
        fadeTime += delta;
        if(fadeTime > fadeDuration)
            fadingIn = false;
    }
    
    public void unproject(Vector3 touchPos) {
        viewport.unproject(touchPos);
    }
    
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        img.dispose();
    }

}
