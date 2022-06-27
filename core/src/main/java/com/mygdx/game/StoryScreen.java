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

public class StoryScreen implements Screen {
    private Texture imgStory;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    
    private boolean fadingOut = false;
    private float fadeDuration = 1f;
    private float fadeTime = 0;
    private Texture imgBlack;
    
    private Rectangle button;
    private Texture imgButton;
    
    IGameControl game;
    
    public StoryScreen(IGameControl game) {
        this.game = game;
        
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.update();
        viewport = new FitViewport(800, 480, camera);
        batch = new SpriteBatch();
        
        loadImages();
        
        float width = 115;
        float height = width*115f/366f;
        button = new Rectangle((800-width)/2f,10,width,height);
        
        StoryControl control = new StoryControl();
        control.connect(this);
        control.setButtonPos(button);
        
        Gdx.input.setInputProcessor(control);
    }
    
    private void loadImages() {
        imgBlack  = new Texture(Gdx.files.internal("black.png"));
        imgButton = new Texture(Gdx.files.internal("continue2.png"));
        imgStory  = new Texture(Gdx.files.internal("story.png"));
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
        
        if(fadingOut) {
            drawFadeOut();
            updateFadeOut(delta);
        }
        
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true); // Restore viewport.
    }
    
    private void draw() {
        batch.begin();
        batch.setColor(1f,1f,1f,1f);
        batch.draw(imgStory, 0, 0, 800, 480);
        batch.draw(imgButton, button.x, button.y, button.width, button.height);
        batch.end();
    }
    
    private void drawFadeOut() {
        batch.begin();
        batch.setColor(1f,1f,1f, fadeTime/fadeDuration);
        batch.draw(imgBlack, 0, 0, 800, 480);
        batch.end();
    }
    
    public void fadeOut() {
        fadingOut = true;
    }
    
    private void updateFadeOut(float delta) {
        fadeTime += delta;
        if(fadeTime > fadeDuration)
            game.setScreen(2);
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
        // TODO Auto-generated method stub
        
    }

}
