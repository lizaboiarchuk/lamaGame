package com.mygdx.game.bonuses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Coin extends Bonus{

    //create a coin on a specific cloud if true
    public Coin(Vector2 cloud, boolean exists) {
        super(cloud,exists);
        this.exists=exists;
        if (exists) {
            texture = new Texture("coin.png");
            position = new Vector2(cloud.x + 18, cloud.y + 20);
        }
    }
}
