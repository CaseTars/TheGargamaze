package com.mygdx.game;

import com.mygdx.game.interfaces.IGameControl;

public class BuilderTutorial extends Builder {

    public BuilderTutorial(IGameControl game) {
        super(game);
        mazePath = "maps/tutorial.txt";
    }
    
    @Override
    protected void createView() {
        view = new ViewTutorial();
        view.connect(game);
    }
    
    @Override
    protected void createControl() {
        control = new ControlTutorial();
        control.conectView(view);
        ((ViewTutorial) view).connect((ControlTutorial) control);
    }
    
    @Override
    protected void createBlackhole() {
        //Sem buraco negro
    }
}
