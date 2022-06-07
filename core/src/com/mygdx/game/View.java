package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

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
        
        cells = new ViewCell[size][size];
        for(int x = 0;x < size;x++)
            for(int y = 0;y < size;y++)
                cells[x][y] = new ViewCell(x,y);
    }

    public void show() {
        ScreenUtils.clear(.1f, 0, .1f, 1); //cor do fundo
        camera.update();
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        
//        for(int x = 0;x < size;x++) {
//        	for(int y = 0;y < size;y++) {
//        		ViewCell aux = cells[x][y];
//        		batch.draw(aux.getTexture(), aux.getX(), aux.getY(), ViewCell.size, ViewCell.size);
//        	}	
//        }
        
        for(int y = size-1;y >= 0;y--) {
        	for(int x = 0;x < size;x++) {
        		ViewCell aux = cells[x][y];
    			batch.draw(aux.getTexture(), aux.getX(), aux.getY(), ViewCell.size, ViewCell.size);
        	}
        }
        batch.end();
    }   
    
    public void dispose() {
    	ViewCell.dispose();
    	batch.dispose();
    }
    
    public ViewCell getCell(int x, int y) {
        return cells[x][y];
    }
}