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
    
    private char[][] mazeMatrix;
    private boolean[][] visibilityMatrix;
    
    private int nButtons;
    private char[][] buttonsConfigurationMatrix;
    private Position[] buttonsPositionArray;
    private Position[][] gatesPositionMatrix;
    
    public void build() throws IOException {
    	
		readFile();
		
    	space = new Space();
        space.setAlwaysVisibleCells(visibilityMatrix);
    	view = new View();
        connectCells();
        

    	for(int x = 0;x < Space.size;x++) {
			for(int y = 0;y < Space.size;y++) {
			    char symbol = mazeMatrix[x][y];
			    
        		if(symbol == 'W') {
            		Wall wall = new Wall(x,y);
                    space.insert(wall);
        		}
        		else if(symbol == 'C') {
                    pCase = new Player(x,y,'C');
                    pCase.connect(space);
                    space.insert(pCase);
            	}
                else if(symbol == 'T') {
                    pTars = new Player(x,y,'T');
                    pTars.connect(space);
                    space.insert(pTars);
                }
            }
        }

    	for(int i = 0; i<nButtons; i++) {  // Botoes
			Position p = buttonsPositionArray[i];
			boolean hasSpring = buttonsConfigurationMatrix[i][0] == 'b';
			char allowed = buttonsConfigurationMatrix[i][1];
			Button button = new Button(p.x, p.y, hasSpring, allowed);
            space.insert(button);
   			for(int j = 0; j<gatesPositionMatrix[i].length; j++) {
                Position gP = gatesPositionMatrix[i][j];
                Gate gate = new Gate(gP.x, gP.y);
                space.insert(gate);
   				button.connect(gate);
   			}
    	}
        	
        SoundManager.loadSounds();
        SoundManager.playGameMusic();
        
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
        
        lantern.setRadius(1);
        
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
    
    private void readFile() throws IOException {
        FileReader file = new FileReader("Maze.txt");
        BufferedReader readFile = new BufferedReader(file);
        
        Space.size = Integer.parseInt(readFile.readLine());
        
        readMazeMatrix(readFile);
        readFile.readLine();
        readVisibilityMatrix(readFile);
        
        nButtons  = Integer.parseInt(readFile.readLine());
        readButtons(readFile);
        
        file.close();
    }
    
    private void readMazeMatrix(BufferedReader readFile) throws IOException {
        mazeMatrix = new char[Space.size][Space.size];
        for(int y = Space.size-1; y>=0; y--) {
            String line[] = readFile.readLine().split(",");
            for(int x = 0; x<Space.size; x++)
                mazeMatrix[x][y] = line[x].charAt(0);
        }
    }
    
    private void readVisibilityMatrix(BufferedReader readFile) throws IOException {
        visibilityMatrix = new boolean[Space.size][Space.size];
        for(int y = Space.size-1; y>=0; y--) {
            String line[] = readFile.readLine().split(",");
            for(int x = 0; x<Space.size; x++)
                visibilityMatrix[x][y] = line[x].charAt(0) == '+';
        }
    }
    
    private void readButtons(BufferedReader readFile) throws IOException {
        buttonsConfigurationMatrix = new char[nButtons][2];
        buttonsPositionArray = new Position[nButtons];
        gatesPositionMatrix = new Position[nButtons][];
        for(int i = 0; i<nButtons; i++)
            readButton(i, readFile);
    }
    
    private void readButton(int index, BufferedReader readFile) throws IOException {
        String line[] = readFile.readLine().split(",");
        buttonsConfigurationMatrix[index][0] = line[0].charAt(0);
        buttonsConfigurationMatrix[index][1] = line[1].charAt(0);
        buttonsPositionArray[index] = new Position(Integer.parseInt(line[2]), Integer.parseInt(line[3]));
        
        int nGates = Integer.parseInt(line[5]);
        Position gates[] = new Position[nGates];
        for(int i = 0; i<nGates; i++)
            gates[i] = new Position(Integer.parseInt(line[7+3*i]), Integer.parseInt(line[8+3*i]));
        gatesPositionMatrix[index] = gates;
    }
}
