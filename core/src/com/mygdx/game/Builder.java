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
    	Space.size = 31; //receber do arquivo
    	space = new Space();
        view = new View();
        
        for(int x = 0; x<Space.size; x++)
            for(int y = 0; y<Space.size; y++)
                space.getCell(x, y).connect(view.getCell(x, y));
                
        // leitura de arquivo
        
        pCase = new Player(1,1, 'C');
        pCase.connect(space);
        space.insert(pCase);
        
        pTars = new Player(1,2, 'T');
        pTars.connect(space);
        space.insert(pTars);
        
        Wall wall;
        for(int i = 0; i<4; i++) {
            wall = new Wall(2+i,7);
            space.insert(wall);
        }
    
        Control control = new Control();
        
        control.conectCase(pCase);
        control.conectTars(pTars);
        
        Gdx.input.setInputProcessor(control);
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
