package com.mygdx.game;

import com.mygdx.game.elements.Element;
import com.mygdx.game.interfaces.IMove;

public class Space implements IMove{
	
	private static final int size = 31;  
	private Cell[][] cells; 
	private View view;
	
	public Space() {
		cells = new Cell[size][size]; //pd ser q de problema
	}
	
	public void insert(Element toInsert) {
		cells[toInsert.getX()][toInsert.getY()].insert(toInsert);
	}
	
	public void connectView(View view) {
		this.view = view;
	}
	
	public boolean move(Element toMove, int xF, int yF) {
		if(cells[xF][yF].isObstructed) return false;
		
		cells[toMove.getX()][toMove.getY()].remove(toMove);
		cells[xF][yF].insert(toMove);
		//laterna
		
		return true;
	}
	
	
}
