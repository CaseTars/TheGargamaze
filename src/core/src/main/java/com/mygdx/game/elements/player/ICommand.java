package com.mygdx.game.elements.player;

public interface ICommand {
	public void moveLeft();
	public void moveRight();
	public void moveUp();
	public void moveDown();
    public void moveTo(int x, int y);
	public void commandAction();
	public void commandDeaction();
	public void useHability(int i);
	public void dropCrystal();
}
