package com.mygdx.game;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.elements.Element;
import com.mygdx.game.elements.Player;
import com.mygdx.game.interfaces.IUpdate;
import com.mygdx.game.interfaces.IVisual;
import com.mygdx.game.interfaces.IVisualCell;
import com.mygdx.game.interfaces.ICell;
import com.mygdx.game.interfaces.IGate;
import com.mygdx.game.interfaces.IPlayerInteraction;


public class Cell implements ICell{
    private Array<Element> elements = new Array<Element>();
    private IUpdate viewCell;
    private boolean visible = false, 
                    alwaysVisible = false;
    private float clarity;
    Array<IVisual> textures = new Array<IVisual>();

    public void setAlwaysVisible() {
        this.visible = true;
        this.alwaysVisible = true;
    }
    
    public void connect(IUpdate viewCell) {
        this.viewCell = viewCell;
        viewCell.update();
    }
    
    public void insert(Element toInsert) {
    	elements.add(toInsert);
    	toInsert.setCell(this);
    	viewCell.update();
    }
    
    public void remove(Element toRemove) {
    	elements.removeValue(toRemove, true);
    	toRemove.setCell(null);
        viewCell.update();
    }
    
    public int obstructionLevel() {
        int maior = 0;
        for(Element element : elements)
            if(element.obstructionLevel() > maior)
                maior = element.obstructionLevel();
        return maior;
    }
    
    public void setVisibility(boolean visible, float clarity) {
        if(alwaysVisible) return;
        if(this.visible == false && visible == false) {
            this.clarity = 0;
            return;
        }
        
        if(visible == false)
            this.clarity = 0;
        else
            this.clarity += clarity;
        if(clarity > 1)
            clarity = 1;
        
        this.visible = visible;
        viewCell.update();
    }
    
    public void setVisibility(boolean visible) {
        setVisibility(visible, 1);
    }
    
    public IGate getGate() {
    	return (IGate) elements.get(0);
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
    public float clarity() {
        if(alwaysVisible) return 1;
        return clarity;
    }

    @Override
    public IVisual visual(int index) {
        return elements.get(index); // Retorna a interface visual do elemento na posicao index
    }

	@Override
	public void action(IPlayerInteraction player) {
		for(Element element: elements) {
			element.action(player);
		}
	}

	@Override
	public void deaction() {
		for(Element element: elements) {
			element.deaction();
		}
		
	}

    public void interact(IPlayerInteraction player) {
        for(Element element: elements) {
            element.interact(player);
        }
    }
    
    public void update() {
        viewCell.update();
    }
    
}
