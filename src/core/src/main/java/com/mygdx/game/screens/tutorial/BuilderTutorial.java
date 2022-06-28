package com.mygdx.game.screens.tutorial;

import com.mygdx.game.app.IGameControl;
import com.mygdx.game.screens.game.Builder;

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
