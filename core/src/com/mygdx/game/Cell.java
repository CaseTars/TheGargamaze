package com.mygdx.game;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.elements.Element;
import com.mygdx.game.interfaces.IViewCell;
import com.mygdx.game.interfaces.IVisual;

public class Cell {
    private Array<Element> elements = new Array<Element>();
    private IViewCell viewCell;
    Array<IVisual> textures = new Array<IVisual>();
    
    public void connect(IViewCell viewCell) {
        this.viewCell = viewCell;
    }
    
    public void insert(Element toInsert) {
    	elements.add(toInsert);
    	updateView();
    }
    
    public void remove(Element toRemove) {
    	elements.removeValue(toRemove, true);
    	updateView();
    }
    
    public boolean isObstructed() {
    	for(Element element: elements) {
    		if(element.isObstructed()) return true;
    	}
    	return false;
    }
    
    private void updateView() {
        textures.clear();
    	for(Element element: elements) {
    		textures.add(element);
    	}
    	viewCell.update(textures);  //ver as texturas para cada caso, qndo tiver mais de um elemento
    }
    
}
