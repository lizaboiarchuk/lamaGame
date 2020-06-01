package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Lama;
import com.mygdx.game.states.GameStateManager;

public class Lame {

    public Vector3 position;
    public Vector3 velocity;

    public int GRAVITY = -15;
    public static final int MOVEMENT = 100;

    public Sprite lame;
    public boolean isOnCloud=true;
    public boolean lookingLeft;




    public Lame (int x, int y) {
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        lame = new Sprite(new Texture("lameLeftStay.PNG"));
    }



    public void update(float dt) {
        if (position.y>0)
            velocity.add(0, GRAVITY,0);
        velocity.scl(dt);
       // position.add(velocity);
        position.add(0,velocity.y+MOVEMENT*dt*0.6f,0);
        if (position.y<=0)
            position.y=0;
        velocity.scl(1/dt);
        if (position.x<=0)
            position.x=0;
        if (position.x>= Lama.WIDTH/2-lame.getWidth()/2)
            position.x=Lama.WIDTH/2-lame.getWidth()/2;
    }



    public void jump() {
        System.out.println("jump");
        if (isOnCloud==true) {
            velocity.y = 400;
            if (lookingLeft)
                lame = new Sprite(new Texture("lameLeftMove.PNG"));
            else
                lame = new Sprite(new Texture("LameRightMove.PNG"));
        }

    }


    public void left() {
        lookingLeft=true;
        if (position.x>0)
            position.x+=-2;
        if (isOnCloud) lame=new Sprite(new Texture("lameLeftStay.PNG"));
        else lame=new Sprite(new Texture("lameLeftMove.PNG"));
    }



    public void right() {
        lookingLeft=false;
        if (position.x<Lama.WIDTH/2-lame.getWidth()/2)
            position.x+= 2;
        if (isOnCloud)
            lame=new Sprite(new Texture("lameRightStay.PNG"));
        else
            lame=new Sprite(new Texture("LameRightMove.PNG"));

    }



    public long onCloud(long score, boolean v) {
        if (!isOnCloud) {
            isOnCloud=true;
            if (!v) score+=10;
        }
        if (lookingLeft)
            lame=new Sprite(new Texture("lameLeftStay.PNG"));
        else
            lame=new Sprite(new Texture("lameRightStay.PNG"));
        return score;
    }


}
