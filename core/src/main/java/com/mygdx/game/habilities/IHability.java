package com.mygdx.game.habilities;

import com.mygdx.game.interfaces.ITime;
import com.mygdx.game.interfaces.IUpdate;

public interface IHability extends ITime, IVisualHability, IUpdate {
    public void use();
}
