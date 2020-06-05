package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.StartClass;

public class Lame {

    public Vector3 position;
    public Vector3 velocity;

    public int GRAVITY = -15;
    public static final int MOVEMENT = 100;

    public Sprite lame;
    public boolean isOnCloud=true;
    public boolean lookingLeft;
    public boolean magnitism;
    public boolean fly;
    public boolean hasPampers=false;

    public boolean hasWings=false;
    public float width, height;
    public Sound jumpSound;
    public Cloud currentCloud=null;
    private StartClass startClass;







    public Lame (float x, float y, StartClass startClass) {
        this.startClass = startClass;
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.mp3"));
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        lame = new Sprite(new Texture("lameLeftStay.PNG"));
        width =  lame.getWidth();
        height = lame.getHeight();

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
        if (position.x>= StartClass.WIDTH/2-lame.getWidth()/2)
            position.x=StartClass.WIDTH/2-lame.getWidth()/2;
    }

    public void jump() {
        GRAVITY=-15;
        if (isOnCloud==true) {
            position.y+=5;
            jumpSound.setVolume(0,0.2f);
           if(startClass.musicOn) jumpSound.play();
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
        if (position.x<StartClass.WIDTH/2-lame.getWidth()/2)
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
