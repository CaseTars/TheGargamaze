package com.mygdx.game.interfaces;

public interface ISpaceAction {
	public void action(int x, int y, IPlayerInteraction player);
	public void deaction(int x, int y, IPlayerInteraction player);
}
