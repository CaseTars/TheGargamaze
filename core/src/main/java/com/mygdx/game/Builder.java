package com.mygdx.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
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
import com.mygdx.game.interfaces.IGameControl;

public class Builder {
    protected String mazePath = "maze.txt";
    
    protected View view;
    private Player pCase;
    private Player pTars;
    private Space space;
    protected Control control;
    protected IGameControl game;
    private Blackhole bh;
    
    private char[][] mazeMatrix;
    private boolean[][] visibilityMatrix;
    
    private int nButtons;
    private char[][] buttonsConfigurationMatrix;
    private Position[] buttonsPositionArray;
    private char[][] gatesConfigurationMatrix;
    private Position[][] gatesPositionMatrix;
    
    public Builder(IGameControl game) {
        this.game = game;
    }
    
    public void build() throws IOException {
		readFile();
		
    	space = new Space();
        space.setAlwaysVisibleCells(visibilityMatrix);
        createView();
        connectCells();
        createControl();

        // Monta o mapa
        buildMaze();
    	buildButtons();
    	
    	// Cria Habilidades
        createHability(0);  // Habilidade Visual
        createHability(1);  // Habilidade troca de lugar.
        createHability(2);  // Habilidade atravessar paredes
        
        // cria lanternas
        createLantern(pCase);
        createLantern(pTars);
        
        // Cria Buraco negro
        createBlackhole();
        
        view.connect(pCase, pTars);
        control.conectCase(pCase);
        control.conectTars(pTars);
        
        Gdx.input.setInputProcessor(control);
    }
    
    protected void createView() {
        view = new View();
        view.connect(game);
    }
    
    protected void createControl() {
        control = new Control();
        control.conectView(view);
    }
    
    private void buildMaze() {
        for(int x = 0;x < Space.size;x++) {
            for(int y = 0;y < Space.size;y++) {
                switch(mazeMatrix[x][y]) {
                case 'W':   // Cria Parede
                    Wall wall = new Wall(x,y);
                    space.insert(wall);
                    break;
                case 'C':   // Cria Jogador 1
                    pCase = new Player(x,y,'C');
                    pCase.connect(space);
                    pCase.connect(game);
                    space.insert(pCase);
                    break;
                case 'T':   // Cria Jogador 2
                    pTars = new Player(x,y,'T');
                    pTars.connect(space);
                    pTars.connect(game);
                    space.insert(pTars);
                    break;
                case 'D':   // Cria Escuridao
                    Darkness darkness = new Darkness(x,y);
                    space.insert(darkness);
                    break;
                case 'H':   // Cria Parede Dura
                    HardWall hardWall = new HardWall(x,y);
                    space.insert(hardWall);
                    break;
                case 'G':   // Cria Portão desconectado
                    Gate gate = Gate.create(x,y, 'N');
                    space.insert(gate);
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':   // Cria Qaulquer Cristal
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
                    System.out.println("Error - bloco nao existe: " + mazeMatrix[x][y]); // FAZER COMO EXCECAO?
                    break;
                }
            }
        }
    }
    
    private void buildButtons() {
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
    }
    
    private void createHability(int i) {
        if(i == 0) {    // Habilidade Visual
            VisionRadiusHability hability1 = new VisionRadiusHability(10, 5, 2);
            hability1.connect(pCase);
            pCase.connect(hability1, 0);
            
            hability1 = new VisionRadiusHability(10, 5, 2);
            hability1.connect(pTars);
            pTars.connect(hability1, 0);
        }
        else if(i == 1) {   // Habilidade troca de lugar.
            SwitchPlacesHability hability2 = new SwitchPlacesHability(1, 5);
            hability2.connect(pCase, pTars);
            hability2.connect(control);
            hability2.connect(view);
            hability2.connect(space);
            pCase.connect(hability2, 1);
            pTars.connect(hability2, 1);
        }
        else if(i == 2) {   // Habilidade atravessar paredes
            PhantomHability hability3 = new PhantomHability(10, 5);
            hability3.connect(pCase);
            pCase.connect(hability3, 2);
            
            hability3 = new PhantomHability(10, 5);
            hability3.connect(pTars);
            pTars.connect(hability3, 2);
        }
    }
    
    private void createLantern(Player p) {
        Lantern lantern = new Lantern();
        lantern.connect(p);
        lantern.connect(space);
        p.connect(lantern);
        space.addLantern(lantern);
    }
    
    protected void createBlackhole() {
        bh = new Blackhole(space);
        bh.connect(pCase, pTars);
        bh.connect(game);
        view.createBH(bh);
        pCase.connect(bh);
        pTars.connect(bh);
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
        FileHandle handle = Gdx.files.internal(mazePath);
        String text = handle.readString();
        String file[] = text.split("\\r?\\n");
        Array<String> lines = new Array<String>();
        for(String line : file) {
            lines.add(line);
        }
        
        Space.size = Integer.parseInt(lines.removeIndex(0));
        
        readMazeMatrix(lines);
        lines.removeIndex(0);
        readVisibilityMatrix(lines);
        
        nButtons  = Integer.parseInt(lines.removeIndex(0));
        readButtons(lines);
    }
    
    private void readMazeMatrix(Array<String> lines) {
        mazeMatrix = new char[Space.size][Space.size];
        for(int y = Space.size-1; y>=0; y--) {
            String line[] = lines.removeIndex(0).split(",");
            for(int x = 0; x<Space.size; x++)
                mazeMatrix[x][y] = line[x].charAt(0);
        }
    }
    
    private void readVisibilityMatrix(Array<String> lines) {
        visibilityMatrix = new boolean[Space.size][Space.size];
        for(int y = Space.size-1; y>=0; y--) {
            String line[] = lines.removeIndex(0).split(",");
            for(int x = 0; x<Space.size; x++)
                visibilityMatrix[x][y] = line[x].charAt(0) == '+';
        }
    }
    
    private void readButtons(Array<String> lines) {
        buttonsConfigurationMatrix = new char[nButtons][2];
        buttonsPositionArray = new Position[nButtons];
        gatesPositionMatrix = new Position[nButtons][];
        gatesConfigurationMatrix = new char[nButtons][];
        for(int i = 0; i<nButtons; i++)
            readButton(i, lines);
    }
    
    private void readButton(int index, Array<String> lines) {
        String line[] = lines.removeIndex(0).split(",");
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
