package com.mygdx.game.elements.buttons;

import com.mygdx.game.elements.player.IPlayerInteraction;

public class ButtonNoSpring extends Button {

    public ButtonNoSpring(int x, int y) {
        super(x, y);
    }

    @Override
    public void deaction(IPlayerInteraction player) {}
}
