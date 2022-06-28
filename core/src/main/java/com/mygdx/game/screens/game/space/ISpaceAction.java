package com.mygdx.game.screens.game.space;

import com.mygdx.game.elements.player.IPlayerInteraction;

public interface ISpaceAction {
	public void action(int x, int y, IPlayerInteraction player);
	public void deaction(int x, int y, IPlayerInteraction player);
}
