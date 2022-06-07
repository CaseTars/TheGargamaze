package com.mygdx.game;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.elements.Element;
import com.mygdx.game.interfaces.IUpdate;
import com.mygdx.game.interfaces.IVisual;
import com.mygdx.game.interfaces.IVisualCell;

public class Cell implements IVisualCell{
    private Array<Element> elements = new Array<Element>();
    private IUpdate viewCell;
    private boolean visible, alwaysVisible;
    Array<IVisual> textures = new Array<IVisual>();
    
    public Cell(boolean alwaysVisible) {
        if(alwaysVisible) {
            this.visible = true;
            this.alwaysVisible = true;
        }
        else {
            this.visible = false;
            this.alwaysVisible = false;
        }
    }
    
    public void connect(IUpdate viewCell) {
        this.viewCell = viewCell;
    }
    
    public void insert(Element toInsert) {
    	elements.add(toInsert);
    	viewCell.update();
    }
    
    public void remove(Element toRemove) {
    	elements.removeValue(toRemove, true);
        viewCell.update();
    }
    
    public boolean isObstructed() {
    	for(Element element: elements) {
    		if(element.isObstructed()) return true;
    	}
    	return false;
    }
    
    public void setVisibility(boolean visible) {
        if(alwaysVisible) return;
        if(this.visible == visible) return;
        this.visible = visible;
        viewCell.update();
    }
    
    // From IVisualCell
    @Override
    public int nElements() {
        return elements.size;
    }

    @Override
    public boolean visible() {
        return visible;
    }

    @Override
    public IVisual visual(int index) {
        return elements.get(index); // Retorna a interface visual do elemento na posicao index
    }
    
}
