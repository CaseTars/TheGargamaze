package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.elements.Player;
import com.mygdx.game.elements.Wall;

public class Builder {
    private View view;
    private Player pCase;
    private Player pTars;
    private Space space; //n precisa
    
    public void build() {
        view = new View();
        space = new Space();
        connectCells();
        // leitura de arquivo
        
        pCase = new Player(1,1, 'C');
        pCase.connect(space);
        space.insert(pCase);
        
        pTars = new Player(1,2, 'T');
        pTars.connect(space);
        space.insert(pTars);
        
        Wall wall;
        int[][] positions = {{3, 3},
                             {3, 4},
                             {3, 5},
                             {3, 6},
                             {3, 7},
                             {3, 8},
                             {3, 9},
                             {3, 10},
                             {3, 11},
                             {3, 12},
                             {3, 13},
                             {4, 3},
                             {4, 9},
                             {5, 3},
                             {5, 4},
                             {5, 5},
                             {5, 7},
                             {5, 8},
                             {5, 9},
                             {5, 11},
                             {5, 12},
                             {5, 13},
                             {6, 3},
                             {6, 7},
                             {6, 11},
                             {6, 13},
                             {7, 3},
                             {7, 5},
                             {7, 7},
                             {7, 9},
                             {7, 10},
                             {7, 11},
                             {7, 13},
                             {8, 3},
                             {8, 5},
                             {8, 7},
                             {8, 9},
                             {8, 13},
                             {9, 3},
                             {9, 5},
                             {9, 7},
                             {9, 9},
                             {9, 11},
                             {9, 13},
                             {10, 3},
                             {10, 5},
                             {10, 7},
                             {10, 11},
                             {10, 13},
                             {11, 3},
                             {11, 5},
                             {11, 6},
                             {11, 7},
                             {11, 8},
                             {11, 9},
                             {11, 10},
                             {11, 11},
                             {11, 13},
                             {12, 13},
                             {13, 3},
                             {13, 4},
                             {13, 5},
                             {13, 6},
                             {13, 7},
                             {13, 8},
                             {13, 9},
                             {13, 10},
                             {13, 11},
                             {13, 12},
                             {13, 13},};
        
        for(int i = 0; i<positions.length; i++) {
            wall = new Wall(positions[i][0], positions[i][1]);
            space.insert(wall);
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
        
        lantern.setRadius(2);
    
        Control control = new Control();
        
        control.conectCase(pCase);
        control.conectTars(pTars);
        
        Gdx.input.setInputProcessor(control);
    }
    
    private void connectCells() {
        for(int x = 0; x<Space.size; x++)
            for(int y = 0; y<Space.size; y++) {
                space.getCell(x, y).connect(view .getCell(x, y));
                view .getCell(x, y).connect(space.getCell(x, y));
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
}
