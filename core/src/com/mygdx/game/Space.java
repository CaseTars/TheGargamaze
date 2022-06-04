package com.mygdx.game;

import com.mygdx.game.elements.Element;
import com.mygdx.game.interfaces.Imove;

public class Space implements Imove{
	
	private static final int size = 31;  
	private Cell[][] cells; 
	private View view;
	
	public Space() {
		cells = new Cell[size][size]; //pd ser q de problema
	}
	
	public void insert(Element e) {
		cells[e.getX()][e.getY()].insert(e);
	}
	
	public void connectView(View view) {
		this.view = view;
	}
	
	public boolean move(Element e, int xF, int yF) {
		if(cells[xF][yF].isObstructed) return false;
		
		cells[e.getX()][e.getY()].remove(e);
		cells[xF][yF].insert(e);
		//laterna
		
		return true;
	}
	
	
}
