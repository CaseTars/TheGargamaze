package com.mygdx.game.elements.buttons;

import com.mygdx.game.interfaces.IPlayerInteraction;

public class ButtonSpecificNoSpring extends ButtonSpecific{

    public ButtonSpecificNoSpring(int x, int y, char variation) {
        super(x, y, variation);
    }

    @Override
    public void deaction(IPlayerInteraction player) {}
}
