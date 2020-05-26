package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Cloud {
    public static final int CLOUD_HEIGHT = 19;

    private static final int RANDOMfrom = 10;
    private static final int RANDOMto = 180;


    public Texture cloud;
    public Vector2 position;
    public Random rand;


    public Cloud(float y) {
        cloud= new Texture("cloud1.png");
        rand = new Random();
        position = new Vector2(rand.nextInt(RANDOMto)+RANDOMfrom, y);
    }

    public void reposition(float y) {
        position = new Vector2(rand.nextInt(RANDOMto)+RANDOMfrom, y);
    }

}
