package com.mygdx.game.habilities;

import com.mygdx.game.Position;
import com.mygdx.game.interfaces.IPlayerInteraction;
import com.mygdx.game.interfaces.IPosition;
import com.mygdx.game.interfaces.IVisualEffect;

public class VisualEffect implements IVisualEffect {
    IPlayerInteraction player;
    int radiusGain;
    float time;
    IPosition playerPos;
    Position pos;
    
    public VisualEffect(IPlayerInteraction player, int radiusGain, float duration) {
        this.player = player;
        this.radiusGain = radiusGain;
        this.time = duration;
        this.pos = null;
        this.playerPos = null;
    }
    
    public VisualEffect(IPlayerInteraction player, int radiusGain, IPosition playerPos) {
        this.player = player;
        this.radiusGain = radiusGain;
        this.time = 0;
        this.pos = new Position(playerPos.getX(),playerPos.getY());
        this.playerPos = playerPos;
    }

    @Override
    public void update(float t) {
        if(pos != null) return;
        time -= t;
    }

    @Override
    public void applyEffect() {
        player.changeRadius(radiusGain);
    }

    @Override
    public void removeEffect() {
        player.changeRadius(-radiusGain);
    }

    @Override
    public boolean isOver() {
        if(pos == null)
            return time <= 0;
        return playerPos.getX() != pos.x || playerPos.getY() != pos.y;
    }
}
