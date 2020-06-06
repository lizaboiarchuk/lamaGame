package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.util.concurrent.ThreadLocalRandom;
import com.mygdx.game.StartClass;
import com.mygdx.game.bonuses.*;

import java.util.Random;

public class Cloud {

    public boolean moveable;
    public boolean bad;
    public boolean visited;
    public boolean canBeMagnit=true;
    public boolean toDraw=true;
    public boolean resizable=false;
    public float width, height;
    public boolean smaller=false;
    public float sizeVel = 0.1f;
    public boolean isInter=false;
    public float velocity=1;




    public Texture cloud;

    public Bonus bonus;
    public boolean hasBonus;

    public Coin coin;
    public boolean hasCoin;
    public Vector2 position;
    Random random = new Random();



    //constructor
    public Cloud(float y, Cloud c) {
        visited=false;
        cloud= new Texture("cloud1.png");
        width= cloud.getWidth();
        height = cloud.getHeight();

        if (c==null)                                             // if it is a first cloud
            position = new Vector2(200, y);
        else
            reposition(y,c);
    }








    //creates a new position for a cloud based on a location of a previous cloud
    public void reposition(float y,Cloud c) {
        if (isInter)                                                    //some of the "inter" clouds are black
            setBad();
        visited=false;
        toDraw = true;
        hasCoin=false;
        int left_or_right = random.nextInt(2);       //randomly generate a position for a cloud
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
        coin = new Coin(position, random.nextBoolean());       // create a coin with probability 1:2
        if (coin.exists)
            hasCoin = true;
        if (!hasCoin) {
            if (random.nextInt(5)==0) {                           //if cloud doesnt have a coin, there can be
                int bonusType = random.nextInt(4);                //randomly generated one of 4 bonuses
                if (bonusType==0) {
                    this.bonus =new Magnet(position,true);
                    this.bonus.bonusType=0;
                }
                if (bonusType==1) {
                    this.bonus =new Wings(position,true);
                    this.bonus.bonusType=1;
                }
                if (bonusType==2) {
                    this.bonus =new Jetpack(position,true);
                    this.bonus.bonusType=2;
                }
                if (bonusType==3) {
                    this.bonus =new Multiplier(position,true);
                    this.bonus.bonusType=3;
                }
            }
        }

    }






    //change cloud's position if it is moveable (for gameMode2)
    public void move() {
        if (moveable==true) {
            position.x += velocity;
            if (hasBonus)
                bonus.position.x+=velocity;
            if (position.x >= 200)
                velocity = -1;
            if (position.x <= 0)
                velocity = 1;
        }
    }



    //make a cloud bad (lama dies in case of jumping on it)
    public void setBad() {
        bad=true;
        cloud = new Texture("badCloud.png");
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
                if (height >= 5) {
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
