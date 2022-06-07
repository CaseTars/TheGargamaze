package com.mygdx.game;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.elements.Element;
import com.mygdx.game.exceptions.ObstructedCell;
import com.mygdx.game.interfaces.IMove;
import com.mygdx.game.interfaces.ISpace;
import com.mygdx.game.interfaces.ILantern;

public class Space implements ISpace{
	public static final int size = 31; 
	
	private Array<ILantern> lanterns = new Array<ILantern>();
	private Cell[][] cells; 
	
	public Space() {
		cells = new Cell[size][size]; 
		for(int x = 0;x < size;x++)
			for(int y = 0;y < size;y++)
				cells[x][y] = new Cell(false);
	}
	
	public void insert(Element toInsert) {
		cells[toInsert.getX()][toInsert.getY()].insert(toInsert);
	}
	
	public void addLantern(ILantern lantern) {
	    lanterns.add(lantern);
        updateVisibility();
	}
	
	public void move(Element toMove, int xi, int yi) throws ObstructedCell {
		if(cells[toMove.getX()][toMove.getY()].isObstructed())
		    throw new ObstructedCell("This cell is obstructed!");
		
		cells[xi][yi].remove(toMove);
		cells[toMove.getX()][toMove.getY()].insert(toMove);
        updateVisibility();
	}
	
	public Cell getCell(int x, int y) {
        return cells[x][y];
    }
	
	private void updateVisibility() {
	    setDark();
	    for(ILantern lantern : lanterns)
	        lantern.iluminate();
	}
	
	private void setDark() {
        for(int x = 0;x < size;x++)
            for(int y = 0;y < size;y++)
                cells[x][y].setVisibility(false);
	}

    @Override
    public void iluminate(int x, int y) {
        if(x < 0 || x >= size || y < 0 || y >= size) return;
        cells[x][y].setVisibility(true);
    }
}
