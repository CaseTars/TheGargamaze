package com.mygdx.game.elements.player;

import com.mygdx.game.interfaces.ICommand;
import com.mygdx.game.interfaces.ITime;
import com.mygdx.game.interfaces.IVisualPlayer;

public interface IPlayer extends    ICommand, ITime, IVisualPlayer, 
                                    IPlayerInteraction, IPlayerSwitchHability, IPlayerBH {

}
