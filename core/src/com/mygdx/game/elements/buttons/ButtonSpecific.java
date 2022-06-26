package com.mygdx.game.elements.buttons;

import com.mygdx.game.SoundManager;
import com.mygdx.game.interfaces.IPlayerInteraction;

public class ButtonSpecific extends Button{
    char variation;
    
    public ButtonSpecific(int x, int y, char variation) {
        super(x, y);
        this.variation = variation;
    }
    
    @Override
    public void action(IPlayerInteraction player) {
        if(this.variation != player.variation()) {
            SoundManager.playError();
            return;
        }
        super.action(player);
    }
    
    @Override
    public char variation() {
        return variation;
    }
}
