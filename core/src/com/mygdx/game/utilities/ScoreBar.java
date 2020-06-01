package com.mygdx.game.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class ScoreBar {

    public TextureRegion digit1, digit2, digit3, digit4,digit5;
    public int d1,d2,d3,d4,d5;
    public ArrayList<TextureRegion> digits;
    public ArrayList<TextureRegion> score;
    public Texture main;
    public float width, height;



    public ScoreBar() {
        main = new Texture("cifri.png");
        width = main.getWidth()/10;
        height = main.getHeight();
        score = new ArrayList<TextureRegion>();


        digits = new ArrayList<TextureRegion>();
        for (int i=0;i<10;i++) {
            TextureRegion a = new TextureRegion(main, 5+(i%5)*(11), 15+20*(i/5), 8, 11);
            digits.add(a);
        }
    }


    public ArrayList<TextureRegion> show(long score) {
        d1 = (int) (score / 10000);
        d2 = (int) (score/1000 % 10);
        d3 = (int) (score/100 % 10);
        d4 = (int) (score%100/10);
        d5 = (int)score%10;
        this.score = new ArrayList<TextureRegion>();
        this.score.add(region(d1));
        this.score.add(region(d2));
        this.score.add(region(d3));
        this.score.add(region(d4));
        this.score.add(region(d5));
        return this.score;
    }




    private TextureRegion region (int a) {
        TextureRegion res=null;
        if (a==0) res = digits.get(9);
        else res = digits.get(a-1);

        return res;
    }



}
