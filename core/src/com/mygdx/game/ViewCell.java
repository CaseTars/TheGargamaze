package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.interfaces.IUpdate;
import com.mygdx.game.interfaces.IVisualCell;
import com.mygdx.game.interfaces.IVisualEffect;

public class ViewCell implements IUpdate {
    public static final float size = 480f/Space.size;
    
	private static Texture imgCase; 
	private static Texture imgLittleCase; 
	private static Texture imgTars;	
	private static Texture imgLittleTars;	
    private static Texture imgWall;
    private static Texture imgGround;
    private static Texture imgDark;
    private static Texture imgGateClosed; 
    private static Texture imgGateOpen;  
    private static Texture imgButtonFree; 
    private static Texture imgButtonFreeCase;	 
    private static Texture imgButtonFreeTars; 
    private static Texture imgButtonPressed;
    private static Texture imgButtonPressedCase;
    private static Texture imgButtonPressedTars;
    private static Texture imgCrystalYellow;
    private static Texture imgCrystalBlue;
    private static Texture imgCrystalRed;
    private static Texture imgCrystalGreen;

    public static Texture imgDarkness;
    private static Texture imgHappySantanche;
        
    private Array<Texture> textures = new Array<Texture>();

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
        imgCase     			  = new Texture(Gdx.files.internal("case2blue2.png"));	//check
        imgLittleCase     		  = new Texture(Gdx.files.internal("case2blue2.png"));	
        imgTars     			  = new Texture(Gdx.files.internal("case2red2.png"));   //check
        imgLittleTars   		  = new Texture(Gdx.files.internal("case2red2.png"));   
        imgWall        			  = new Texture(Gdx.files.internal("muro.png"));     //check
        imgGround   			  = new Texture(Gdx.files.internal("ground.png"));   //check
        imgDark	        		  = new Texture(Gdx.files.internal("dark.png"));	//check
        imgGateClosed 		      = new Texture(Gdx.files.internal("gate.png"));	//check
        imgGateOpen 	          = new Texture(Gdx.files.internal("gateOpen.png"));   //check
        imgButtonFree 			  = new Texture(Gdx.files.internal("buttonFree.png")); //check
        imgButtonFreeCase 		  = new Texture(Gdx.files.internal("buttonFree.png")); 
        imgButtonFreeTars 		  = new Texture(Gdx.files.internal("buttonFree.png")); 
        imgButtonPressed		  = new Texture(Gdx.files.internal("buttonPressed.png"));  //check    
        imgButtonPressedCase	  = new Texture(Gdx.files.internal("buttonPressed.png"));   
        imgButtonPressedTars	  = new Texture(Gdx.files.internal("buttonPressed.png"));    
        imgCrystalYellow		  = new Texture(Gdx.files.internal("case2blue.png"));
        imgCrystalBlue 			  = new Texture(Gdx.files.internal("case2blue.png"));
        imgCrystalRed  			  = new Texture(Gdx.files.internal("case2blue.png"));
        imgCrystalGreen			  = new Texture(Gdx.files.internal("case2blue.png"));
        imgDarkness               = new Texture(Gdx.files.internal("darkness.png"));
        imgHappySantanche		  = new Texture(Gdx.files.internal("HappySantache.png"));  //check
    }
	
    public static void dispose() {
    	imgCase.dispose();
        imgTars.dispose();
        imgWall.dispose();
        imgGround.dispose();
        imgDark.dispose();
        imgButtonPressed.dispose();
        imgButtonFree.dispose();
        imgLittleCase.dispose();
        imgLittleTars.dispose();
        imgButtonFreeCase.dispose();
        imgButtonFreeTars.dispose();
        imgButtonPressedCase.dispose();
        imgButtonPressedTars.dispose();
        imgCrystalYellow.dispose();
        imgCrystalBlue.dispose();
        imgCrystalRed.dispose();
        imgCrystalGreen.dispose();
        imgHappySantanche.dispose();
    }
    
	public void update() {
			   
	   int nbElements = cell.nElements();
	   
	   textures.add(imgGround); 
	   if(!cell.visible()) {  
		   if(nbElements <= 1) textures.add(imgDark);  //talvez de BO 
		   else {
			   for(int i = 1;i < nbElements;i++) {
				   if(cell.visual(i).variation() == 'C') textures.add(imgCase); 
				   if(cell.visual(i).variation() == 'T') textures.add(imgTars);
			   }
			   textures.add(imgDarkness);
		   }  
	   }
	   else {
		   for(int i = 0;i < nbElements;i++) {
			   if(cell.visual(i).type() == 'B') {
				   if(cell.visual(i).variation() == 'c') {
					   if(cell.visual(i).state() == 'p') textures.add(imgButtonPressedCase); 
			           else if(cell.visual(i).state() == 'f') textures.add(imgButtonFreeCase);
				   }
				   else {
					   if(cell.visual(i).state() == 'p') textures.add(imgButtonPressedTars); 
			           else if(cell.visual(i).state() == 'f') textures.add(imgButtonFreeTars);
				   }
			   }
			   else if(cell.visual(i).type() == 'G') {
				   if(cell.visual(0).state() == 'c') textures.add(imgGateClosed);
				   else if(cell.visual(0).state() == 'o') textures.add(imgGateOpen);
			   }
			   else if(cell.visual(i).type() == 'W') textures.add(imgWall);
			   else if(cell.visual(i).type() == 'D') textures.add(imgGround);
			   else if(cell.visual(i).type() == 'C') {
				   if(cell.visual(i).variation() == 'b')textures.add(imgCrystalBlue); 
				   else if(cell.visual(i).variation() == 'r')textures.add(imgCrystalRed);
				   else if(cell.visual(i).variation() == 'y')textures.add(imgCrystalYellow);
				   else if(cell.visual(i).variation() == 'g')textures.add(imgCrystalGreen);
			   }
			   else if(cell.visual(i).type() == 'P') {
				   if(nbElements < 3 && contains(2)) {
					   if(cell.visual(i).variation() == 'C') textures.add(imgCase); //normal
					   if(cell.visual(i).variation() == 'T') textures.add(imgTars);
				   }
				   else if(nbElements > 1){
					   if(cell.visual(i).variation() == 'C') textures.add(imgCase); //pequeno
					   if(cell.visual(i).variation() == 'T') textures.add(imgTars);
				   }
				   else {
					   if(cell.visual(i).variation() == 'C') textures.add(imgCase); //normal
					   if(cell.visual(i).variation() == 'T') textures.add(imgTars);
				   }
			   }
			   else {
		    	   textures.add(imgHappySantanche);
		       }
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
	
	public static Texture getDefaultTexture() {
	    return imgGround;
	}
	
	public Array<Texture> getTexture() {
	    return textures;
	}
	
	public float getX() {
        return x;
    }
	
	public float getY() {
        return y;
    }
}
