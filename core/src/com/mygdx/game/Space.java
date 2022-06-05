package com.mygdx.game;

import com.mygdx.game.elements.Element;
import com.mygdx.game.interfaces.IMove;

public class Space implements IMove{
	public static final int size = 31; 
	
	private Cell[][] cells; 
	
	public Space() {
		cells = new Cell[size][size]; 
		for(int x = 0;x < size;x++)
			for(int y = 0;y < size;y++)
				cells[x][y] = new Cell();
	}
	
	public void insert(Element toInsert) {
		cells[toInsert.getX()][toInsert.getY()].insert(toInsert);
	}
	
	public boolean move(Element toMove, int xF, int yF) {
		if(cells[xF][yF].isObstructed()) return false;
		cells[toMove.getX()][toMove.getY()].remove(toMove);
		cells[xF][yF].insert(toMove);
		//laterna
    	
		return true;
	}
	
	public Cell getCell(int x, int y) {
        return cells[x][y];
    }
}
