package com.mygdx.game;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.elements.Element;
import com.mygdx.game.elements.Player;
import com.mygdx.game.elements.Wall;
import com.mygdx.game.exceptions.ObstructedCell;
import com.mygdx.game.interfaces.ISpace;
import com.mygdx.game.interfaces.ILantern;

public class Space implements ISpace{
	public static int size;
	
	private Array<ILantern> lanterns = new Array<ILantern>();
	private Cell[][] cells; 
	
	public Space() {
		cells = new Cell[size][size]; 
		for(int x = 0; x < size; x++)
 			for(int y = 0; y < size; y++)
 			   cells[x][y] = new Cell();
	}
	
	public void setAlwaysVisibleCells(boolean[][] matrix) {
        for(int x = 0;x < size;x++)
            for(int y = 0;y < size;y++)
                if(matrix[x][y])
                    cells[x][y].setAlwaysVisible();
	}
	
	public void insert(Element toInsert) {
		cells[toInsert.getX()][toInsert.getY()].insert(toInsert);
	}
	
	public void addLantern(ILantern lantern) {
	    lanterns.add(lantern);
        updateVisibility();
	}
	
	public void move(Player toMove, int xi, int yi,  int xf, int yf) throws ObstructedCell {
		if(cells[xf][yf].isObstructed())
		    throw new ObstructedCell("This cell is obstructed!");
		
		cells[xi][yi].remove(toMove);
		cells[xf][yf].insert(toMove);
        cells[xf][yf].interact(toMove);
        updateVisibility();
	}
	
	public Cell getCell(int x, int y) {
        return cells[x][y];
    }
	
	public void updateVisibility() {
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

	@Override
	public void action(int x, int y,char variation) {
		cells[x][y].action(variation);
	}

	@Override
	public void deaction(int x, int y) {
		cells[x][y].deaction();
	}
}
