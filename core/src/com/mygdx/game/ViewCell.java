package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.interfaces.IUpdate;
import com.mygdx.game.interfaces.IVisualCell;

public class ViewCell implements IUpdate {
    public static final float size = 480f/Space.size;
    
	private static Texture imgCase;
    private static Texture imgTars;
    private static Texture imgCaseTars;
    private static Texture imgWall;
    private static Texture imgGround;
    private static Texture imgDark;

    private Texture current = imgGround;

    private float x;
    private float y;
    private IVisualCell cell;
    
    public ViewCell(int x, int y) {
        this.x = (x*size) + 160;
        this.y = y*size;
    }
    
    public void connect(IVisualCell cell) {
        this.cell = cell;
    }
    
    public static void loadImages() {
        imgCase     = new Texture(Gdx.files.internal("case.png"));
        imgTars     = new Texture(Gdx.files.internal("tars.png"));
        //imgCaseTars = new Texture(Gdx.files.internal("case+tars.png"));
        imgWall     = new Texture(Gdx.files.internal("wall.png"));
        imgGround   = new Texture(Gdx.files.internal("ground.png"));
        imgCaseTars = new Texture(Gdx.files.internal("HappySantache.png"));
        imgDark     = new Texture(Gdx.files.internal("dark.png"));
    }
	
    public static void dispose() {
    	imgCase.dispose();
        imgTars.dispose();
        imgCaseTars.dispose();
        imgWall.dispose();
        imgGround.dispose();
        imgDark.dispose();
    }
    
	public void update() {
	   if(!cell.visible())
	       current = imgDark;
	   else {
	       int n = cell.nElements();
	       if(n == 0)
	           current = imgGround;
	       else if(n == 1) {
	           if (cell.visual(0).type() == 'P') {
	              if (cell.visual(0).variation() == 'C')
	                  current = imgCase;
	              if (cell.visual(0).variation() == 'T')
	                    current = imgTars;
	          }
	          else if (cell.visual(0).type() == 'W')
	              current = imgWall;
	       }
	       else if(n == 2) {
	           if(contains(2));
	               current = imgCaseTars;
	       }
	   }
	}
	
	private boolean contains(int P) {
	    int nP = 0;
	    for(int i = 0; i<cell.nElements(); i++) {
	        if(cell.visual(i).type() == 'P')
	            nP++;
	    }
	    return nP == P;
	}
	
	public Texture getTexture() {
	    return current;
	}
	
	public float getX() {
        return x;
    }
	
	public float getY() {
        return y;
    }
}
