package com.mygdx.game.interfaces;

public interface IVisualCell {
    public int nElements();
    public boolean visible();
    public float clarity();
    public IVisual visual(int index);
}
