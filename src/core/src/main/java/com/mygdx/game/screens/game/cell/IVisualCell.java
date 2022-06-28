package com.mygdx.game.screens.game.cell;

import com.mygdx.game.elements.element.IVisual;

public interface IVisualCell {
    public int nElements();
    public boolean visible();
    public float clarity();
    public IVisual visual(int index);
}
