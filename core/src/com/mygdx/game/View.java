package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.interfaces.IVisualPlayer;

public class View {
    public static final int size = Space.size;

    private ShapeRenderer shapeRenderer;
    private ViewCell[][] cells;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    
    private IVisualPlayer Vcase;
    private IVisualPlayer Vtars;

    public View() {
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.update();
        shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(800, 480, camera);
        batch = new SpriteBatch();
        ViewCell.loadImages();
        
        cells = new ViewCell[size][size];
        for(int x = 0;x < size;x++)
            for(int y = 0;y < size;y++)
                cells[x][y] = new ViewCell(x,y);
    }
    
    public void connect(IVisualPlayer Vcase, IVisualPlayer Vtars) {
        this.Vcase = Vcase;
        this.Vtars = Vtars;
    }

    public void show() {
        ScreenUtils.clear(0, 0, 0, 1); //cor do fundo
        camera.update();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        
        batch.setProjectionMatrix(camera.combined);
        
        drawMap();
        drawStatus(Vtars, 0);
        drawStatus(Vcase, 160+480);
        
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true); // Restore viewport.
    }
    
    private void drawMap() {
//    	drawBaseMap();
        batch.begin();
        for(int x = 0;x < size;x++) {
            for(int y = 0;y < size;y++) {
                ViewCell aux = cells[x][y];
                batch.draw(aux.getDefaultTexture(), aux.getX(), aux.getY(), ViewCell.size, ViewCell.size);
                batch.draw(aux.getTexture(), aux.getX(), aux.getY(), ViewCell.size, ViewCell.size);
            }
        }
        batch.end();
    }
    
    
    private void drawStatus(IVisualPlayer player, int xRef) {
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.GRAY);
        shapeRenderer.rect(xRef+18, 360, 160-36, 24);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(xRef+20, 360+2, 160-40, 20);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(xRef+22, 360+4, player.timeRemaining()*(160-44), 16);
        shapeRenderer.end();
    }
    
    public void dispose() {
    	ViewCell.dispose();
    	batch.dispose();
    }
    
    public ViewCell getCell(int x, int y) {
        return cells[x][y];
    }
    
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}