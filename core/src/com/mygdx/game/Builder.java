package com.mygdx.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.elements.Crystal;
import com.mygdx.game.elements.Darkness;
import com.mygdx.game.elements.Gate;
import com.mygdx.game.elements.HardWall;
import com.mygdx.game.elements.Player;
import com.mygdx.game.elements.Wall;
import com.mygdx.game.elements.buttons.Button;
import com.mygdx.game.habilities.PhantomHability;
import com.mygdx.game.habilities.SwitchPlacesHability;
import com.mygdx.game.habilities.VisionRadiusHability;
import com.mygdx.game.interfaces.IGameEnd;

public class Builder {
    private View view;
    private Player pCase;
    private Player pTars;
    private Space space;
    private Control control;
    private IGameEnd game;
    private Blackhole bh;
    
    private char[][] mazeMatrix;
    private boolean[][] visibilityMatrix;
    
    private int nButtons;
    private char[][] buttonsConfigurationMatrix;
    private Position[] buttonsPositionArray;
    private char[][] gatesConfigurationMatrix;
    private Position[][] gatesPositionMatrix;
    
    public Builder(IGameEnd game) {
        this.game = game;
    }
    
    public void build() throws IOException {
		readFile();
		
    	space = new Space();
        space.setAlwaysVisibleCells(visibilityMatrix);
    	view = new View();
    	view.connect(game);
        connectCells();
        control = new Control();
        control.conectView(view);

    	for(int x = 0;x < Space.size;x++) {
			for(int y = 0;y < Space.size;y++) {
			    switch(mazeMatrix[x][y]) {
			    case 'W':
                    Wall wall = new Wall(x,y);
                    space.insert(wall);
                    break;
			    case 'C':
                    pCase = new Player(x,y,'C');
                    pCase.connect(space);
                    pCase.connect(game);
                    space.insert(pCase);
                    break;
			    case 'T':
                    pTars = new Player(x,y,'T');
                    pTars.connect(space);
                    pTars.connect(game);
                    space.insert(pTars);
                    break;
			    case 'D':
                    Darkness darkness = new Darkness(x,y);
                    space.insert(darkness);
                    break;
                case 'H':
                    HardWall hardWall = new HardWall(x,y);
                    space.insert(hardWall);
                    break;
                case 'G':
                    Gate gate = Gate.create(x,y, 'N');
                    space.insert(gate);
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                    Crystal crystal = new Crystal(x,y,mazeMatrix[x][y]);
                    crystal.connect(space);
                    space.insert(crystal);
                    // cria lanterna do cristal
                    Lantern lantern = new Lantern();
                    lantern.connect(crystal);
                    lantern.connect(space);
                    crystal.connect(lantern);
                    space.addLantern(lantern);
                    break;
			    case '-':
			        break;
                default:
                    System.out.println("Error - bloco nao existe"); // FAZER COMO EXCECAO?
                    break;
			    }
            }
        }

    	for(int i = 0; i<nButtons; i++) {  // Cria Botoes e Portoes
			Position p = buttonsPositionArray[i];
			boolean hasSpring = buttonsConfigurationMatrix[i][0] == 'b';
			char allowed = buttonsConfigurationMatrix[i][1];
			Button button = Button.create(p.x, p.y, hasSpring, allowed);
            space.insert(button);
   			for(int j = 0; j<gatesPositionMatrix[i].length; j++) {
                Position gP = gatesPositionMatrix[i][j];
                Gate gate = Gate.create(gP.x, gP.y, gatesConfigurationMatrix[i][j]);
                space.insert(gate);
   				button.connect(gate);
   			}
    	}
    	
    	// Cria Habilidades
    	
    	// Habilidade Visual
        VisionRadiusHability hability1 = new VisionRadiusHability(10, 5, 2);
        hability1.connect(pCase);
        pCase.connect(hability1, 0);
        
        hability1 = new VisionRadiusHability(10, 5, 2);
        hability1.connect(pTars);
        pTars.connect(hability1, 0);
        
        
        // Habilidade troca de lugar.
        SwitchPlacesHability hability2 = new SwitchPlacesHability(1, 5);
        hability2.connect(pCase, pTars);
        hability2.connect(control);
        hability2.connect(view);
        hability2.connect(space);
        pCase.connect(hability2, 1);
        pTars.connect(hability2, 1);
        
        // Habilidade atravessar paredes
        PhantomHability hability3 = new PhantomHability(10, 5);
        hability3.connect(pCase);
        pCase.connect(hability3, 2);
        
        hability3 = new PhantomHability(10, 5);
        hability3.connect(pTars);
        pTars.connect(hability3, 2);
        
        
        // cria lanternas
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
        
        view.connect(pCase, pTars);
        
        // Cria Buraco negro
        
        bh = new Blackhole(space);
        bh.connect(pCase, pTars);
        bh.connect(game);
        view.createBH(bh);
        pCase.connect(bh);
        pTars.connect(bh);
        
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
    
    Control getControl() {
        return control;
    }

    Blackhole getBH() {
        return bh;
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
        gatesConfigurationMatrix = new char[nButtons][];
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
        char gatesConfig[] = new char[nGates];
        for(int i = 0; i<nGates; i++) {
            gates[i] = new Position(Integer.parseInt(line[7+4*i]), Integer.parseInt(line[8+4*i]));
            gatesConfig[i] = line[9+4*i].charAt(0);
        }
        gatesPositionMatrix[index] = gates;
        gatesConfigurationMatrix[index] = gatesConfig;
    }
}
