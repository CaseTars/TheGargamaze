package com.mygdx.game;

public class Builder {
    private View view;
    private Player pCase;
    private Player pTars;
    private Space space; //n precisa
    
    public void build() {
        view = new View();
        space = new Space();
        
        for(int x = 0; x<Space.size; x++)
            for(int y = 0; y<Space.size; y++)
                space.getCell(x, y).connect(view.getCell(x, y));
                
        // leitura de arquivo
        
        pCase = new Player(0,0);
        space.insert(pCase);
        
        pTars = new Player(1,0);
        space.insert(pTars);
    
        Control control = new Control();
        control.conectCase(pCase);
        control.conectTars(pTars);
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
