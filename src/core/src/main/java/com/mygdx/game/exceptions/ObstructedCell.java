package com.mygdx.game.exceptions;

public class ObstructedCell extends Exception { 
    private static final long serialVersionUID = 1L;

    public ObstructedCell(String errorMessage) {
        super(errorMessage);
    }
}
