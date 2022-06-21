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
	private static Texture imgTars;	
	private static Texture imgCasePhantom; 
	private static Texture imgTarsPhantom; 
    private static Texture imgWall;
    private static Texture imgHardWall;
    private static Texture imgGround;
    private static Texture imgDark;
    private static Texture imgGateClosed; 
    private static Texture imgGateOpen;  
    private static Texture imgGateHardClosed;
    private static Texture imgGateHardOpen; 
    private static Texture imgButtonFree; 
    private static Texture imgButtonPressed;
    private static Texture imgCrystalYellow;
    private static Texture imgCrystalBlue;
    private static Texture imgCrystalRed;
    private static Texture imgCrystalGreen;
    private static Texture imgBlueBG;
    private static Texture imgRedBG;
    private static Texture imgButtonFreeFrame;
    private static Texture imgButtonPressedFrame;

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
        imgTars     			  = new Texture(Gdx.files.internal("case2red2.png"));   //check
        imgCasePhantom			  = new Texture(Gdx.files.internal("case2blue2phantom.png"));   //check
        imgTarsPhantom     		  = new Texture(Gdx.files.internal("case2red2phantom.png"));   //check
        imgWall        			  = new Texture(Gdx.files.internal("wall.png"));     //check
        imgHardWall        		  = new Texture(Gdx.files.internal("hardWall.png"));    //check
        imgGround   			  = new Texture(Gdx.files.internal("ground.png"));   //check
        imgDark	        		  = new Texture(Gdx.files.internal("dark.png"));	//check
        imgGateClosed 		      = new Texture(Gdx.files.internal("gate.png"));	//check
        imgGateOpen 	          = new Texture(Gdx.files.internal("gateOpen.png"));   //check
        imgGateHardClosed 		  = new Texture(Gdx.files.internal("hardGate.png"));	//check
        imgGateHardOpen 	      = new Texture(Gdx.files.internal("gateOpen.png"));   
        imgButtonFree 			  = new Texture(Gdx.files.internal("buttonFree.png")); //check
        imgButtonPressed		  = new Texture(Gdx.files.internal("buttonPressed.png"));  //check      
        imgCrystalYellow		  = new Texture(Gdx.files.internal("esmeraldas.png"));
        imgCrystalBlue 			  = new Texture(Gdx.files.internal("case2blue.png"));
        imgCrystalRed  			  = new Texture(Gdx.files.internal("case2blue.png"));
        imgCrystalGreen			  = new Texture(Gdx.files.internal("case2blue.png"));
        imgDarkness               = new Texture(Gdx.files.internal("darkness.png"));	//check
        imgHappySantanche		  = new Texture(Gdx.files.internal("HappySantache.png"));  //check
        imgBlueBG				  = new Texture(Gdx.files.internal("fundoAzul1.png"));	//check
        imgRedBG				  = new Texture(Gdx.files.internal("fundoVermelho1.png"));	//check
        imgButtonFreeFrame		  = new Texture(Gdx.files.internal("molduraApagada.png"));	//check
        imgButtonPressedFrame	  = new Texture(Gdx.files.internal("molduraAcesa.png"));	//check
    }
	
    public static void dispose() {
    	imgCase.dispose();
        imgTars.dispose();
        imgCasePhantom.dispose();
        imgTarsPhantom.dispose();
        imgWall.dispose();
        imgHardWall.dispose();
        imgGround.dispose();
        imgDark.dispose();
        imgButtonPressed.dispose();
        imgButtonFree.dispose();
        imgGateClosed.dispose();
        imgGateOpen.dispose();
        imgGateHardClosed.dispose();
        imgGateHardOpen.dispose();
        imgCrystalYellow.dispose();
        imgCrystalBlue.dispose();
        imgCrystalRed.dispose();
        imgCrystalGreen.dispose();
        imgHappySantanche.dispose();
        imgBlueBG.dispose();
        imgRedBG.dispose();
        imgButtonFreeFrame.dispose();
        imgButtonPressedFrame.dispose();
    }
    
	public void update() {
			   
	   int nbElements = cell.nElements();
	   
	   textures.clear();
	   textures.add(imgGround); 
	   if(!cell.visible()) {  
		   if(nbElements <= 1) textures.add(imgDark);  //talvez de BO 
		   else {
			   for(int i = 1;i < nbElements;i++) {
				   if(cell.visual(i).variation() == 'C') textures.add(imgCase); 
				   if(cell.visual(i).variation() == 'T') textures.add(imgTars);
			   }
		   }  
	   }
	   else {
		   for(int i = 0;i < nbElements;i++) {
			   if(cell.visual(i).type() == 'B') {
				   if(cell.visual(i).variation() == 'c') { //case
					   if(cell.visual(i).state() == 'p') {
						   textures.add(imgButtonPressedFrame);
						   textures.add(imgBlueBG);
						   textures.add(imgButtonPressed); 
					   }
			           else if(cell.visual(i).state() == 'f') {
			        	   textures.add(imgButtonFreeFrame);
						   textures.add(imgBlueBG);
			        	   textures.add(imgButtonFree);
			           }
				   }
				   else if(cell.visual(i).type() == 't') { //tars
					   if(cell.visual(i).state() == 'p') {
						   textures.add(imgButtonPressedFrame);
						   textures.add(imgRedBG);
						   textures.add(imgButtonPressed); 
					   }
			           else if(cell.visual(i).state() == 'f') {
			        	   textures.add(imgButtonFreeFrame);
						   textures.add(imgRedBG);
			        	   textures.add(imgButtonFree);
			           }
				   }
				   else {
					   if(cell.visual(i).state() == 'p') {
						   textures.add(imgButtonPressedFrame);
						   textures.add(imgButtonPressed); 
					   }
			           else if(cell.visual(i).state() == 'f') {
			        	   textures.add(imgButtonFreeFrame);
			        	   textures.add(imgButtonFree);
			           }
				   }
			   }
			   else if(cell.visual(i).type() == 'G') {
				   if(cell.visual(i).variation() == 'H') { //hard
					   if(cell.visual(0).state() == 'c') textures.add(imgGateHardClosed); 
					   else if(cell.visual(0).state() == 'o') textures.add(imgGateOpen);  //falta
				   }
				   else {  //normal
					   if(cell.visual(0).state() == 'c') textures.add(imgGateClosed);  
					   else if(cell.visual(0).state() == 'o') textures.add(imgGateOpen); 
				   }
			   }
			   else if(cell.visual(i).type() == 'W')
			   		if(cell.visual(i).variation() == 'H') textures.add(imgHardWall);
			   		else  textures.add(imgWall);
               else if(cell.visual(i).type() == 'D') textures.add(imgGround);
			   else if(cell.visual(i).type() == 'C') {
				   textures.add(imgCrystalYellow);
//				   if(cell.visual(i).variation() == 'b')textures.add(imgCrystalBlue); 
//				   else if(cell.visual(i).variation() == 'r')textures.add(imgCrystalRed);
//				   else if(cell.visual(i).variation() == 'y')textures.add(imgCrystalYellow);
//				   else if(cell.visual(i).variation() == 'g')textures.add(imgCrystalGreen);
			   }
			   else if(cell.visual(i).type() == 'P') {
				   if(cell.visual(i).variation() == 'C') {
					   if(cell.visual(i).state() == 'P') textures.add(imgCasePhantom);
					   else textures.add(imgCase);
				   }
				   if(cell.visual(i).variation() == 'T') {
					   if(cell.visual(i).state() == 'P') textures.add(imgTarsPhantom);
					   else textures.add(imgTars);
				   }
			   }
			   else {
		    	   textures.add(imgHappySantanche);
		       }
		   }
		   if(nbElements > 0 && cell.visual(0).type() == 'D')
               textures.add(imgDarkness);
	   }
	}
	
//	private boolean contains(int P) {
//	    int nP = 0;
//	    for(int i = 0; i<cell.nElements(); i++) {
//	        if(cell.visual(i).type() == 'P')
//	            nP++;
//	    }
//	    return nP == P;
//	}
	
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
