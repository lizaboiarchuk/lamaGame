package com.mygdx.game.bonuses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Cloud;

public abstract class Bonus {

    public Vector2 position;
    public Texture texture;
    public Vector2 cloud;
    public boolean exists;
    public int bonusType;

    Bonus(Vector2 cloud, boolean exists) {
        this.exists = exists;
        this.cloud = cloud;
        position = new Vector2();

    }
}
