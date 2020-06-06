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

    public boolean bonusesOn[];     // 0-magnet 1-wings 2-jetpack 3-multiplier

    public Sprite[][] sprites;      // all the sprites
    public Sprite currentSprite;

    public int GRAVITY = -15;
    public static final int MOVEMENT = 100;
    public boolean isOnCloud=true;
    public boolean lookingLeft;
    public float width, height;
    public Sound jumpSound;
    public Cloud currentCloud=null;
    private StartClass game;




    //constructor
    public Lame (float x, float y, StartClass startClass) {
        currentSprite = new Sprite(new Texture("lameLeftStay.PNG"));
        width =  currentSprite.getWidth();
        height = currentSprite.getHeight();
        initSprites();

        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

        bonusesOn = new boolean[4];
        for (int i=0;i<4;i++) bonusesOn[i]=false;

        this.game = startClass;
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.mp3"));
    }

    //updating position
    public void update(float dt) {
        if (position.y>0)
            velocity.add(0, GRAVITY,0);
        velocity.scl(dt);
        position.add(0,velocity.y+MOVEMENT*dt*0.6f,0);
        if (position.y<=0)
            position.y=0;
        velocity.scl(1/dt);
        if (position.x<=0)
            position.x=0;
        if (position.x>= StartClass.WIDTH/2-currentSprite.getWidth()/2)
            position.x=StartClass.WIDTH/2-currentSprite.getWidth()/2;
    }



    //"UP" key is pressed
    public void jump() {
        GRAVITY=-15;
        if (isOnCloud==true) {
            position.y+=5;
            jumpSound.setVolume(0,0.2f);
           if (game.musicOn)
               jumpSound.play();
            velocity.y = 400;

            if (lookingLeft && !bonusesOn[0] && !bonusesOn[1] && !bonusesOn[2] && !bonusesOn[3] ) currentSprite = sprites[0][2];
            else currentSprite = sprites[0][3];

            if (bonusesOn[1]) {
                if (lookingLeft) currentSprite = sprites[2][2];
                else currentSprite = sprites[2][3];
            }

            if (bonusesOn[0]) {
                if (lookingLeft) currentSprite = sprites[1][2];
                else currentSprite = sprites[1][3];
            }

            if (bonusesOn[3]) {
                if (lookingLeft) currentSprite = sprites[4][2];
                else currentSprite = sprites[4][3];
            }
        }
    }



    // "LEFT" key is pressed
    public void left() {
        lookingLeft=true;
        if (position.x>0)
            position.x+=-2;
        if (isOnCloud && !bonusesOn[0] && !bonusesOn[1] && !bonusesOn[2] && !bonusesOn[3]) currentSprite = sprites[0][0];
        else currentSprite = sprites[0][2];

        if (bonusesOn[1]) {
            if (isOnCloud) currentSprite = sprites[2][0];
            else currentSprite = sprites[2][2];
        }

        if (bonusesOn[0]) {
            if (isOnCloud) currentSprite = sprites[1][0];
            else currentSprite = sprites[1][2];
        }

        if (bonusesOn[3]) {
            if (isOnCloud) currentSprite = sprites[4][0];
            else currentSprite = sprites[4][2];
        }

        if (bonusesOn[2]) {
            if (isOnCloud) currentSprite = sprites[3][0];
        }
    }



    // "RIGHT" key is pressed
    public void right() {
        lookingLeft = false;
        if (position.x < StartClass.WIDTH / 2 - currentSprite.getWidth() / 2)
            position.x += 2;
        if (isOnCloud && !bonusesOn[0] && !bonusesOn[1] && !bonusesOn[2] && !bonusesOn[3])
            currentSprite = sprites[0][1];
        else currentSprite = sprites[0][3];

        if (bonusesOn[1]) {
            if (isOnCloud) currentSprite = sprites[2][1];
            else currentSprite = sprites[2][3];
        }

        if (bonusesOn[0]) {
            if (isOnCloud) currentSprite = sprites[1][1];
            else currentSprite = sprites[1][3];
        }

        if (bonusesOn[3]) {
            if (isOnCloud) currentSprite = sprites[4][1];
            else currentSprite = sprites[4][3];
        }

        if (bonusesOn[2]) {
            if (isOnCloud) currentSprite = sprites[3][1];
        }
    }


    public long onCloud(long score, boolean v) {
        if (!isOnCloud) {
            isOnCloud=true;
            if (!v) score+=10;
        }
        if (lookingLeft && !bonusesOn[0] && !bonusesOn[1] && !bonusesOn[2] && !bonusesOn[3] ) currentSprite = sprites[0][0];
        else currentSprite = sprites[0][1];

        if (bonusesOn[1]) {
            if (lookingLeft) currentSprite = sprites[2][0];
            else currentSprite = sprites[2][1];
        }
        if (bonusesOn[0]) {
            if (lookingLeft) currentSprite = sprites[1][0];
            else currentSprite = sprites[1][1];
        }
        if (bonusesOn[3]) {
            if (lookingLeft) currentSprite = sprites[4][0];
            else currentSprite = sprites[4][1];
        }
        if (bonusesOn[2]) {
            if (lookingLeft) currentSprite = sprites[3][0];
            else currentSprite = sprites[3][1];
        }
        return score;
    }






    private void initSprites() {
        sprites = new Sprite[5][4];

        sprites[0][0] = new Sprite(new Texture("lameLeftStay.PNG"));          // just lama
        sprites[0][1] = new Sprite(new Texture("lameRightStay.PNG"));
        sprites[0][2] = new Sprite(new Texture("lameLeftMove.PNG"));
        sprites[0][3] = new Sprite(new Texture("LameRightMove.PNG"));

        sprites[1][0] = new Sprite(new Texture("lamaMagnitLeftStay.PNG"));          // with magnet
        sprites[1][1] = new Sprite(new Texture("lamaMagnitRightStay.PNG"));
        sprites[1][2] = new Sprite(new Texture("lamaMagnitLeftMove.PNG"));
        sprites[1][3] = new Sprite(new Texture("lamaMagnitRightMove.PNG"));

        sprites[2][0] = new Sprite(new Texture("lamaWingsLeftStay.PNG"));          // with wings
        sprites[2][1] = new Sprite(new Texture("lamaWingsRightStay.PNG"));
        sprites[2][2] = new Sprite(new Texture("lamaWingsLeftMove.PNG"));
        sprites[2][3] = new Sprite(new Texture("lamaWingsRightMove.PNG"));

        sprites[3][0] = new Sprite(new Texture("lameJetLeft.PNG"));          // with jetPack
        sprites[3][1] = new Sprite(new Texture("lameJetRight.PNG"));
        sprites[3][2] = new Sprite(new Texture("lameLeftMove.PNG"));
        sprites[3][3] = new Sprite(new Texture("LameRightMove.PNG"));

        sprites[4][0] = new Sprite(new Texture("lamaDoubleLeftStay.PNG"));          // with multiplier
        sprites[4][1] = new Sprite(new Texture("lamaDoubleRightStay.PNG"));
        sprites[4][2] = new Sprite(new Texture("lamaDoubleLeftMove.PNG"));
        sprites[4][3] = new Sprite(new Texture("LamaDoubleRightMove.PNG"));
    }


































}
