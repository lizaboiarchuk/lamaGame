package com.mygdx.game.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class ScoreBar {

    public Texture digit1,digit2,digit3,digit4,digit5;
    public int d1,d2,d3,d4,d5;
    public ArrayList<TextureRegion> digits;
    public Texture main;



    public ScoreBar() {
        main = new Texture("dig.png");
        digits = new ArrayList<TextureRegion>();
        for (int i=0;i<9;i++) {
            TextureRegion a = new TextureRegion(main, i*main.getWidth()/10,0, main.getWidth()/10, 50);
            digits.add(a);
        }



    }




}
