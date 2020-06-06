package com.mygdx.game.bonuses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Cloud;

public class Magnet extends Bonus{

    //create a coin on a specific cloud if true
    public Magnet(Vector2 cloud, boolean exists) {
        super(cloud,exists);
        this.exists=exists;
        if (exists) {
            texture = new Texture("magnit.png");
            position = new Vector2(position.x + 16, position.y + 23);
        }
    }
}
