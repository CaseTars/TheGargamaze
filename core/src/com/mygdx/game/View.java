package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.interfaces.IViewCell;
import com.mygdx.game.interfaces.IVisual;

public class View {
    public static final int size = Space.size;
    
    private ViewCell[][] cells;
    private OrthographicCamera camera;
    private SpriteBatch batch;

    public View() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();
        ViewCell.loadImages();
        
        for(int x = 0;x < size;x++)
            for(int y = 0;y < size;y++)
                cells[x][y] = new ViewCell(x,y);
    }

    public void show() {
        ScreenUtils.clear(0, 0, 0.2f, 1); //cor do fundo
        camera.update();
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        
        for(int x = 0;x < size;x++) {
        	for(int y = 0;y < size;y++) {
        		ViewCell aux = cells[x][y];
        		batch.draw(aux.getTexture(), aux.getX(), aux.getY());
        	}	
        }

        batch.end();
    }   
    
    public ViewCell getCell(int x, int y) {
        return cells[x][y];
    }
}