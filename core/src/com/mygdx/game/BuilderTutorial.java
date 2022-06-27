package com.mygdx.game;

import com.mygdx.game.interfaces.IGameEnd;

public class BuilderTutorial extends Builder {

    public BuilderTutorial(IGameEnd game) {
        super(game);
        mazePath = "./maps/tutorial.txt";
    }

    
}
