package com.mygdx.game.bonuses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Cloud;

public class Multiplier extends Bonus{

    /**
     * create double bonus on cloud if true
     * @param cloud
     * @param exists
     */
    public Multiplier(Vector2 cloud, boolean exists) {
        super(cloud,exists);
        this.exists=exists;
        if (exists) {
            texture = new Texture("doubleBonus.png");
            position = new Vector2(position.x + 16, position.y + 23);
        }
    }
}
