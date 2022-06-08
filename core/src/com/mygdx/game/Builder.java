package com.mygdx.game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.elements.Player;
import com.mygdx.game.elements.Wall;

public class Builder {
    private View view;
    private Player pCase;
    private Player pTars;
    private Space space; //n precisa
    
    public void build() throws IOException {
    	Space.size = 31; //receber do arquivo
    	space = new Space();
        view = new View();
        connectCells();
        // leitura de arquivo
        
        
        
        //pegar o tamanho do labirinto
        
        pCase = new Player(14,15, 'C');
        pCase.connect(space);
        space.insert(pCase);
        
        pTars = new Player(16,15, 'T');
        pTars.connect(space);
        space.insert(pTars);        
        
        try{
        	String[][] assemblyFile = readAssemblyFile();
        	for(int x = 0;x < Space.size;x++) {
    			for(int y = 0;y < Space.size;y++) {
    				int posY = Space.size-1-y;
            		if(assemblyFile[y][x].charAt(0) == 'W') {
            		Wall wall = new Wall(x,posY);
                    space.insert(wall);
            		}
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
        	System.out.println(erro);
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
