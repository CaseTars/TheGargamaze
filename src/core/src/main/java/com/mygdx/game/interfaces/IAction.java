package com.mygdx.game.interfaces;

import com.mygdx.game.elements.player.IPlayerInteraction;

public interface IAction {
	
	public void action(IPlayerInteraction player);
	public void deaction(IPlayerInteraction player);
}
