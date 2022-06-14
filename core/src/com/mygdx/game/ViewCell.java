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
    private static Texture imgGateClosed; 
    private static Texture imgGateOpen;  
    private static Texture imgGateOpenCase;  
    private static Texture imgGateOpenTars; 
    private static Texture imgGateOpenCaseTars;
    private static Texture imgButtonPressedCaseTars;
    private static Texture imgButtonFreeCaseTars;
    private static Texture imgButtonFree; 
    private static Texture imgButtonFreeCase;
    private static Texture imgButtonFreeTars; 
    private static Texture imgButtonPressed;
    private static Texture imgButtonPressedCase;
    private static Texture imgButtonPressedTars;
    private static Texture imgCrystal;
    private static Texture imgCrystalCase;
    private static Texture imgCrystalTars;
    private static Texture imgCrystalCaseTars;
    private static Texture imgHappySantanche;
    
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
    
    public static void loadImages() {  //falta isso só
        imgCase     			  = new Texture(Gdx.files.internal("case2blue2.png"));	//check
        imgTars     			  = new Texture(Gdx.files.internal("case2red2.png"));   //check
        imgCaseTars 			  = new Texture(Gdx.files.internal("case2BlueRed.png"));
        imgWall        			  = new Texture(Gdx.files.internal("muro.png"));     //check
        imgGround   			  = new Texture(Gdx.files.internal("ground.png"));   //check
        imgDark	        		  = new Texture(Gdx.files.internal("dark.png"));	//check
        imgGateClosed 		      = new Texture(Gdx.files.internal("gate.png"));	//check
        imgGateOpen 	          = new Texture(Gdx.files.internal("gateOpen.png"));   //check
        imgButtonFree 			  = new Texture(Gdx.files.internal("buttonFree.png")); //check
        imgButtonPressed		  = new Texture(Gdx.files.internal("buttonPressed.png"));  //check    
        imgGateOpenCase     	  = new Texture(Gdx.files.internal("gateOpenBlue1.png"));  //check
        imgGateOpenTars           = new Texture(Gdx.files.internal("gateOpenRed1.png"));	//check
        imgGateOpenCaseTars 	  = new Texture(Gdx.files.internal("gateOpenBlueRed.png"));	//check
        imgButtonPressedCaseTars  = new Texture(Gdx.files.internal("buttonPressedBlueRed.png"));	//check
        imgButtonFreeCaseTars	  = new Texture(Gdx.files.internal("buttonFreeBlueRed.png"));	//check
        imgButtonFreeCase		  = new Texture(Gdx.files.internal("buttonFreeBlue.png"));	//check
        imgButtonFreeTars         = new Texture(Gdx.files.internal("buttonFreeRed1.png"));	//check
        imgButtonPressedCase 	  = new Texture(Gdx.files.internal("buttonPressedBlue.png"));	//check
        imgButtonPressedTars      = new Texture(Gdx.files.internal("buttonPressedRed1.png")); 	//check
        imgCrystal    			  = new Texture(Gdx.files.internal("case2blue.png"));
        imgCrystalCase			  = new Texture(Gdx.files.internal("case2blue.png"));
        imgCrystalTars			  = new Texture(Gdx.files.internal("case2blue.png"));
        imgCrystalCaseTars		  = new Texture(Gdx.files.internal("case2blue.png"));
        imgHappySantanche		  = new Texture(Gdx.files.internal("HappySantache.png"));  //check
    }
	
    public static void dispose() {
    	imgCase.dispose();
        imgTars.dispose();
        imgCaseTars.dispose();
        imgWall.dispose();
        imgGround.dispose();
        imgDark.dispose();
        imgButtonPressed.dispose();
        imgButtonFree.dispose();
        imgGateOpenCase.dispose();
        imgGateOpenTars.dispose();
        imgGateOpenCaseTars.dispose();
        imgButtonPressedCaseTars.dispose();
        imgButtonFreeCaseTars.dispose();
        imgButtonFreeCase.dispose();
        imgButtonFreeTars.dispose();
        imgButtonPressedCase.dispose();
        imgButtonPressedTars.dispose();
        imgCrystal.dispose();
        imgCrystalCase.dispose();
        imgCrystalTars.dispose();
        imgCrystalCaseTars.dispose();
        imgHappySantanche.dispose();
    }
    
	public void update() {
	   if(!cell.visible()) {  
		   if(cell.nElements() > 1) {
			   if(cell.visual(1).variation() == 'C') current = imgCase;
			   if(cell.visual(1).variation() == 'T') current = imgTars;
		   }
		   else
			   current = imgDark;
	   }
	   else {
	       int n = cell.nElements();
	       if(n == 0)
	           current = imgGround;
	       else if(n == 1) {
	          if (cell.visual(0).type() == 'P') {
	              if (cell.visual(0).variation() == 'C')  //colocar aqui a troca de passos se for ter
	                  current = imgCase;
	              if (cell.visual(0).variation() == 'T')
	                  current = imgTars;
	          }
	          else if (cell.visual(0).type() == 'W')
	              current = imgWall;
	          else if (cell.visual(0).type() == 'g') {
	        	  if(cell.visual(0).state() == 'c') current = imgGateClosed;
	        	  else if(cell.visual(0).state() == 'o') current = imgGateOpen;
	          }
	          else if (cell.visual(0).type() == 'b') {
	        	  if(cell.visual(0).state() == 'p') current = imgButtonPressed;
	        	  else if(cell.visual(0).state() == 'f') current = imgButtonFree;
	          }
	          else if(cell.visual(0).type() == 'c') {
	        	  current = imgCrystal;
	          }
	       }
	       else if(n == 2) {
	           if(contains(2)) //nbPlayers
	               current = imgCaseTars;
	           else if(cell.visual(0).type() == 'b') { //assumir q a primeira eh smp p objeto
	        	   if(cell.visual(1).variation() == 'C') {
	        		   if(cell.visual(0).state() == 'p') current = imgButtonPressedCase;
	        		   else if(cell.visual(0).state() == 'f') current = imgButtonFreeCase;
	        	   }
	        	   if(cell.visual(1).variation() == 'T') {
	        		   if(cell.visual(0).state() == 'p') current = imgButtonPressedTars;
	        		   else if(cell.visual(0).state() == 'f') current = imgButtonFreeTars;
	        	   }
	           }
	           else if(cell.visual(0).type() == 'g') { 
	        	   if(cell.visual(1).variation() == 'C') {
	        		   current = imgGateOpenCase;
	        	   }
	        	   if(cell.visual(1).variation() == 'T') {
	        		   current = imgGateOpenTars;
	        	   }
	           }
	           else if(cell.visual(0).type() == 'c') {
	        	   if(cell.visual(1).variation() == 'C') {
	        		   current = imgCrystalCase;
	        	   }
	        	   if(cell.visual(1).variation() == 'T') {
	        		   current = imgCrystalTars;
	        	   }
	           }
	       }
	       else if(n == 3) {
	    	   if(cell.visual(0).type() == 'b') {
	    		   if(cell.visual(0).state() == 'p') current = imgButtonPressedCaseTars;
        		   else if(cell.visual(0).state() == 'f') current = imgButtonFreeCaseTars;
	    	   }
	    	   else if(cell.visual(0).type() == 'g') 
	    		   current = imgGateOpenCaseTars;
	    	   else if(cell.visual(0).type() == 'c')
	    		   current = imgCrystalCaseTars;
	       }
	       else {
	    	   current = imgHappySantanche;
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
	
	public Texture getDefaultTexture() {
	    return imgGround;
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
