package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.interfaces.IViewCommand;
import com.mygdx.game.interfaces.IViewSwitchHability;
import com.mygdx.game.interfaces.IVisualPlayer;

public class View implements IViewSwitchHability, Screen, IViewCommand {
    public static final int size = Space.size;

    private ShapeRenderer shapeRenderer;
    private ViewCell[][] cells;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    
    private float thX1, thY1, thX2, thY2, thopacity;
    private Array<Texture>  th1, th2;
    private boolean thAnimating = false;
    
    private IVisualPlayer Vcase;
    private IVisualPlayer Vtars;
    
    private Texture tarsCommands;
    private Texture caseCommands;
    private boolean showCommands;
    
    private static Texture imgCrystalYellow;
    private static Texture imgCrystalBlue;
    private static Texture imgCrystalRed;
    private static Texture imgCrystalGreen;
    private static Texture imgCrystalViolet;
    private static Texture imgCrystalOrange;
    private static Texture imgCrystalYellowGhost;
    private static Texture imgCrystalBlueGhost;
    private static Texture imgCrystalRedGhost;
    private static Texture imgCrystalGreenGhost;
    private static Texture imgCrystalVioletGhost;
    private static Texture imgCrystalOrangeGhost;
    
    private static Texture imgTeleportHab;
    private static Texture imgTeleportHabGhost;
    private static Texture imgVisionHab;
    private static Texture imgVisionHabGhost;
    private static Texture imgGhostHab;
    private static Texture imgGhostHabGhost;




    public View() {
        camera = new OrthographicCamera();
        camera.position.set(0, 0, 0);
        camera.update();
        shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(800, 480, camera);
        batch = new SpriteBatch();
        ViewCell.loadImages();
        loadCommands();
        showCommands = false;
        
        cells = new ViewCell[size][size];
        for(int x = 0;x < size;x++)
            for(int y = 0;y < size;y++)
                cells[x][y] = new ViewCell(x,y);
    }
    
    public void connect(IVisualPlayer Vcase, IVisualPlayer Vtars) {
        this.Vcase = Vcase;
        this.Vtars = Vtars;
    }

    public void loadCommands() {
    	caseCommands = new Texture(Gdx.files.internal("blueComands.png"));
    	tarsCommands = new Texture(Gdx.files.internal("redComands.png"));
    	  
    	imgCrystalYellow = new Texture(Gdx.files.internal("crystalYellow1.png"));
    	imgCrystalBlue = new Texture(Gdx.files.internal("crystalBlue.png"));
	    imgCrystalRed = new Texture(Gdx.files.internal("crystalRed1.png"));
	    imgCrystalGreen = new Texture(Gdx.files.internal("crystalGreen1.png"));
    	imgCrystalViolet = new Texture(Gdx.files.internal("crystalViolet1.png"));
	    imgCrystalOrange = new Texture(Gdx.files.internal("crystalOrange1.png"));
	    imgCrystalYellowGhost = new Texture(Gdx.files.internal("blueComands.png"));
	    imgCrystalBlueGhost = new Texture(Gdx.files.internal("blueComands.png"));
	    imgCrystalRedGhost = new Texture(Gdx.files.internal("blueComands.png"));
	    imgCrystalGreenGhost = new Texture(Gdx.files.internal("blueComands.png"));
    	imgCrystalVioletGhost = new Texture(Gdx.files.internal("blueComands.png"));
    	imgCrystalOrangeGhost = new Texture(Gdx.files.internal("blueComands.png"));
    	    
    	imgTeleportHab = new Texture(Gdx.files.internal("blueComands.png"));
	    imgTeleportHabGhost = new Texture(Gdx.files.internal("blueComands.png"));
	    imgVisionHab = new Texture(Gdx.files.internal("blueComands.png"));
	    imgVisionHabGhost = new Texture(Gdx.files.internal("blueComands.png"));
	    imgGhostHab = new Texture(Gdx.files.internal("blueComands.png"));
	    imgGhostHabGhost = new Texture(Gdx.files.internal("blueComands.png"));

    }
    
    public void show() {
        ScreenUtils.clear(0, 0, 0, 1); //cor do fundo
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        drawMap();
        drawStatus(Vtars, 0);
        drawStatus(Vcase, 160+480);
        drawTeleportHiders();
        drawHabilities();
        if(showCommands) drawCommands();
    
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true); // Restore viewport.
    }
    
    private void drawMap() {
//    	drawBaseMap();
        batch.begin();
        batch.setColor(1f,1f,1f,1f);
        for(int x = 0;x < size;x++) {
            for(int y = 0;y < size;y++) {
                ViewCell aux = cells[x][y];
         	   	for(Texture texture: aux.getTexture()) {
         	   		batch.draw(texture, aux.getX(), aux.getY(), ViewCell.size, ViewCell.size);
         	   	} 
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
    
    public void showCommands(boolean show) { //o tutorial tem que deixar como true
    	showCommands = show;
    }
    
	private void drawCommands() {
		
		float width = viewport.getWorldWidth();
		float height = viewport.getWorldHeight();

		batch.begin();
		
		batch.draw(caseCommands, 0, height*1/3, (367/3)+22, (314/3)+22);
		batch.draw(tarsCommands, (width*5/6)-7, height*1/3, (367/3)+22, (314/3)+22);
		
	    batch.end();
	}
	
	private void drawHabilities() { //habilidades e cristais
		float width = viewport.getWorldWidth();
		float height = viewport.getWorldHeight();
		
		float size = ViewCell.size; //tamanho dos criatis e habilidades
		
		batch.begin();
		batch.draw(imgCrystalYellow, 18, 360-size-10, size, size);
		batch.draw(imgCrystalGreen, 18, 360-size-10, size, size);
		batch.draw(imgCrystalViolet, 18, 360-size-10, size, size);
		batch.draw(imgCrystalOrange, 18, 360-size-10, size, size);

		batch.end();
	}

    public void dispose() {
    	ViewCell.dispose();
    	batch.dispose();
    	
    	caseCommands.dispose();
    	tarsCommands.dispose();
    	imgCrystalYellow.dispose();
    	imgCrystalBlue.dispose();
    	imgCrystalRed.dispose();
    	imgCrystalGreen.dispose();
    	imgCrystalViolet.dispose();
    	imgCrystalOrange.dispose();
    	imgCrystalYellowGhost.dispose();
    	imgCrystalBlueGhost.dispose();
    	imgCrystalRedGhost.dispose();
    	imgCrystalGreenGhost.dispose();
	    imgCrystalVioletGhost.dispose();
	    imgCrystalOrangeGhost.dispose();
	    imgTeleportHab.dispose();
	    imgTeleportHabGhost.dispose();
	    imgVisionHab.dispose();
	    imgVisionHabGhost.dispose();
	    imgGhostHab.dispose();
	    imgGhostHabGhost.dispose();
    }
    
    public ViewCell getCell(int x, int y) {
        return cells[x][y];
    }
    
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    private void drawTeleportHiders() {
        if(!thAnimating) return;
        
        batch.begin();
        batch.setColor(1f,1f,1f,thopacity);
        
     	for(Texture texture: th1)
 	   		batch.draw(texture, thX1, thY1, ViewCell.size, ViewCell.size);
 	   		
     	for(Texture texture: th2)
 	   		batch.draw(texture, thX2, thY2, ViewCell.size, ViewCell.size);

        batch.end();
    }

    @Override
    public void startAnimation(int x1, int y1, int x2, int y2) {
        thAnimating = true;
        
        thX1 = x1*ViewCell.size + 160;
        thY1 = y1*ViewCell.size;
        thX2 = x2*ViewCell.size + 160;
        thY2 = y2*ViewCell.size;
        
        th1 = cells[x1][y1].getTexture();
        th2 = cells[x2][y2].getTexture();
        
        thopacity = 0;
    }

    @Override
    public void setOpacity(float opacity) {
        thopacity = opacity;
    }

    @Override
    public void stopAnimation() {
        thAnimating = false;
    }

	@Override
	public void render(float delta) {
		show();		
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
}