package com.mygdx.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.elements.Button;
import com.mygdx.game.elements.Gate;
import com.mygdx.game.elements.Player;
import com.mygdx.game.elements.Wall;

public class Builder {
    private View view;
    private Player pCase;
    private Player pTars;
    private Space space; //n precisa
    
    public void build() throws IOException {
    	
        try{
        	String[][] assemblyFile = readAssemblyFile();
    		Space.size = Integer.parseInt(assemblyFile[0][0]); 
    		int nbButtons = Integer.parseInt(assemblyFile[1][0]); 
    		
        	space = new Space(assemblyFile);
        	view = new View();
            connectCells();
            

            pCase = new Player(14,15, 'C');
            pCase.connect(space);
            space.insert(pCase);
            
            pTars = new Player(16,15, 'T');
            pTars.connect(space);
            space.insert(pTars);    

        	for(int x = 0;x < Space.size;x++) {
    			for(int y = 2;y < Space.size+2;y++) {
    				int posY = Space.size+1-y;
            		if(assemblyFile[y][x].charAt(0) == 'W' || assemblyFile[y][x].charAt(0) == 'w') {
	            		Wall wall = new Wall(x,posY);
	                    space.insert(wall);
            		}
            		else if(assemblyFile[y][x].charAt(0) == 'g') {
                		Gate gate = new Gate(x,posY);
                        space.insert(gate);
                	}
                }
            }
        	
        	for(int i = Space.size+2;i < Space.size+2+nbButtons;i++) {  // Botoes
        		//b,c,xb,yb,-,qtdP,-,xp,yp,-,xp2,yp2,-,xp3,yp3
       			if(assemblyFile[i][0].charAt(0) == 'b') {
       				int xB = Integer.parseInt(assemblyFile[i][2]);
       				int yB = Integer.parseInt(assemblyFile[i][3]);
       				int nG = Integer.parseInt(assemblyFile[i][5]);
       				char allowed = assemblyFile[i][1].charAt(0);
       				Button button = new Button(xB, yB, allowed);
       				
       				for(int j = 0;j < nG;j++) {
	       				int xG = Integer.parseInt(assemblyFile[i][7+(3*j)]);
	       				int yG = Integer.parseInt(assemblyFile[i][8+(3*j)]);
	       				button.connectGate(space.getCell(xG, yG).getGate());
       				}
       				space.insert(button);
       			}
        	}
        	
        	
        	//Impressor de mapa
//        	for(int i=0;i < 31;i++) {
//        		for(int j= 0;j < 31;j++) {
//        			if(i==0 || i ==  30 || j == 0 || j == 30) {
//        				System.out.print("W");
//            			if(j<30) System.out.print(",");
//
//        			}
//        			else {
//        				System.out.print("-");
//            			if(j<30) System.out.print(",");
//
//        			}
//        		}
//        		System.out.println(" ");
//        	}

        	
        }
        catch(IOException erro) {  //rever esses erros
        	System.out.println(erro.fillInStackTrace());
        }  
           
        Lantern lantern = new Lantern();
        lantern.connect(pCase);
        lantern.connect(space);
        pCase.connect(lantern);
        space.addLantern(lantern);
        
        lantern = new Lantern();
        lantern.connect(pTars);
        lantern.connect(space);
        pTars.connect(lantern);
        space.addLantern(lantern);
        
        lantern.setRadius(30);
        
        view.connect(pCase, pTars);
        
        Control control = new Control();
        
        control.conectCase(pCase);
        control.conectTars(pTars);
        
        Gdx.input.setInputProcessor(control);
    }
    
    private void connectCells() {
        for(int x = 0; x<Space.size; x++)
            for(int y = 0; y<Space.size; y++) {
                view .getCell(x, y).connect(space.getCell(x, y));
                space.getCell(x, y).connect(view .getCell(x, y));
            }
    }
    
    View getView() {
        return view;
    }
    
    Player getCase() {
        return pCase;
    }
    
    Player getTars() {
        return pTars;
    }
    
    private String[][] readAssemblyFile() throws IOException { 
    	FileReader file = new FileReader("Maze.txt");
        BufferedReader readFile = new BufferedReader(file);
        
        Vector<String[]> maze = new Vector<String[]>();
        
        String line =readFile.readLine(); 
           while (line != null) {
	    	   String ln[]  = line.split(",");
	           maze.add(ln);
             line = readFile.readLine(); 
           }        	
        file.close();
        return (String[][])maze.toArray(new String[maze.size()][]); 
    }
}
