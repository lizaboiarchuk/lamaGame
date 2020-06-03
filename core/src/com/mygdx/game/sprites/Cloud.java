package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.util.concurrent.ThreadLocalRandom;
import com.mygdx.game.StartClass;

import java.util.Random;

public class Cloud {
    public static final int CLOUD_HEIGHT = 19;
    public static final int CLOUD_WIDTH=50;
    private static final int RANDOMfrom = 10;
    private static final int RANDOMto = 180;

    private int left_or_right ;
    private int one_or_two_clouds_a_line;

    public boolean moveable;
    public boolean bad=false;
    public boolean visited;
    public boolean hasCoin;
    public boolean magnit;
    public boolean hasJetpack;
    public boolean canBeMagnit=true;
    public boolean toDraw=true;
    public boolean resizable=false;
    public boolean hasPampers;
    public float width, height;
    public boolean smaller=false;
    public float sizeVel = 0.1f;



    public float velocity=1;
    public float velocityY=1;



    public Texture cloud;
    public Texture coin;
    public Texture mag;
    public Texture jetpack;
    public Texture pampers;

    public Vector2 position;
    public Random rand;

    public float yMoveTo;
    public boolean yMoveable;


    public Vector2 coinPosition;
    public Vector2 magnitPosition;
    public Vector2 jetpackPosition;
    public Vector2 pampersPosition;


    //constructor
    public Cloud(float y, Cloud c) {
        yMoveTo=y+70;
        Random r = new Random();
        if (r.nextBoolean()) hasCoin=true;
        else hasCoin=false;
        visited= false;
        cloud= new Texture("cloud1.png");
        width= cloud.getWidth();
        height = cloud.getHeight();
        coin = new Texture("coin.png");
        mag = new Texture("magnit.png");
        jetpack = new Texture("jetpack.png");
        pampers = new Texture("pampers.png");
        rand = new Random();
        if (c==null)
            position = new Vector2(200, y);
        else {
            reposition(y,c,false);
        }
        if (hasCoin) coinPosition = new Vector2(position.x+18, y+20);

    }


    //creates a new position for a cloud based on a location of a previous cloud
    public void reposition(float y,Cloud c, boolean bonus) {
        yMoveTo=y+70;
        toDraw=true;
        magnit=false;
        Random r = new Random();
        if (r.nextBoolean())
            hasCoin=true;
        else
            hasCoin=false;
        if ((canBeMagnit) && (!bonus)) {
            if (r.nextInt(10) == 5) {
                hasCoin = false;
                hasJetpack=false;
                hasPampers=false;
                magnit = true;
            } else {
                if (r.nextInt(10) == 4) {
                    hasCoin = false;
                    hasJetpack = true;
                    hasPampers=false;
                    magnit = true;
                }
                if (r.nextInt(10) == 3) {
                    hasCoin = false;
                    hasPampers = true;
                    hasJetpack=false;
                    magnit = true;
                }
            }

        }
            visited=false;
            bad=false;
            cloud = new Texture("cloud1.png");
            coin = new Texture("coin.png");
            left_or_right = rand.nextInt(2);
            if ((left_or_right==0) && (c.position.x<40))
                left_or_right=1;
            if ((left_or_right==1) && (c.position.x>165))
                left_or_right=0;
            int xCor;
            if (left_or_right==0) {
                if (c.position.x<95)
                    xCor=ThreadLocalRandom.current().nextInt(5,(int)c.position.x-30+1);
                else
                    xCor=ThreadLocalRandom.current().nextInt((int)c.position.x-90,(int)c.position.x-30+1);
                position = new Vector2(xCor, y);
            }
            else {
                if (c.position.x>115)
                    xCor=ThreadLocalRandom.current().nextInt( (int)c.position.x+30, 197);
                else
                    xCor=ThreadLocalRandom.current().nextInt( (int)c.position.x+30, (int) c.position.x+81);
                position = new Vector2(xCor, y);
            }

        if (hasCoin)
            coinPosition = new Vector2(position.x+18, y+20);
        if (magnit)
            magnitPosition = new Vector2(position.x+17, y+20);
        if (hasJetpack)
            jetpackPosition = new Vector2(position.x+17, y+20);
        if (hasPampers)
            pampersPosition = new Vector2(position.x+17, y+20);
    }





    public void move() {
        if (moveable==true) {
            position.x += velocity;
            if (hasCoin)
                coinPosition.x+=velocity;
            if (magnit)
                magnitPosition.x+=velocity;
            if (hasJetpack)
                jetpackPosition.x+=velocity;
            if (position.x >= 200)
                velocity = -1;
            if (position.x <= 0)
                velocity = 1;
        }
    }



    //make a cloud bad
    public void setBad() {
        bad=true;
       // cloud = new Texture("badCloud.png");
    }


    //true with probability 1:4
    public static boolean ran() {
        boolean res;
        Random r = new Random();
        if (r.nextInt(4)==0) res=true;
        else res=false;
        return res;
    }

    public void resize() {
        if (resizable) {
            if (smaller) {
                if (height >= 10) {
                    height -= sizeVel;
                    width -= 2.5*sizeVel;
                }
                else
                    smaller = false;
            } else {
                if (height <= 25) {
                    height += sizeVel;
                    width += 2.5*sizeVel;
                }
                else
                    smaller = true;
            }
        }
    }



}
