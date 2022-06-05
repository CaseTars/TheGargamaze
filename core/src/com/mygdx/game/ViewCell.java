package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.interfaces.IViewCell;
import com.mygdx.game.interfaces.IVisual;

public class ViewCell implements IViewCell {
    public static final int size = 16;
    
	private static Texture imgCase;
    private static Texture imgTars;
    private static Texture imgCaseTars;
    private static Texture imgWall;
    private static Texture imgGround;
    
    private Texture current = imgGround;
    private int x;
    private int y;
    
    public ViewCell(int x, int y) {
        this.x = x*size;
        this.y = y*size;
    }
    
    public static void loadImages() {
        imgCase     = new Texture(Gdx.files.internal("case.png"));
        imgTars     = new Texture(Gdx.files.internal("tars.png"));
        imgCaseTars = new Texture(Gdx.files.internal("case+tars.png"));
        imgWall     = new Texture(Gdx.files.internal("wall.png"));
        imgGround   = new Texture(Gdx.files.internal("ground.png"));
    }
	
    public static void dispose() {
    	imgCase.dispose();
        imgTars.dispose();
        imgCaseTars.dispose();
        imgWall.dispose();
        imgGround.dispose();
    }
    
	public void update(Array<IVisual> textures) {
		if(textures.size == 0)
		    current = imgGround;
		else if (textures.size == 1) {
		    if (textures.get(0).type() == 'P') {
		        if (textures.get(0).variation() == 'C')
		            current = imgCase;
		        if (textures.get(0).variation() == 'T')
                    current = imgTars;
		    }
		    else if (textures.get(0).type() == 'W')
		        current = imgWall;
		}
		else if (textures.size == 2)
		    current = imgCaseTars;
	}
	
	public Texture getTexture() {
	    return current;
	}
	
	public int getX() {
        return x;
    }
	
	public int getY() {
        return y;
    }
}
