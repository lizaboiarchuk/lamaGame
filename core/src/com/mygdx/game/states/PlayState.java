package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Lama;
import com.mygdx.game.sprites.Cloud;
import com.mygdx.game.sprites.Lame;
import com.mygdx.game.utilities.ScoreBar;

import java.awt.*;
import java.util.ArrayList;




public class PlayState extends State{

    Lame lama; //main character
    Texture background=null;

    public static final int SPACE_BETWEEN_CLOUDS = 60;
    public static final int CLOUDS_COUNT = 18;

    public Cloud lastCloud;
    public Cloud lowestCloud;
    public ArrayList<Cloud> clouds;
    public long score;
    public Label scoreLabel;
    ScoreBar scoreBar;
    float lamePrev;



    PlayState(GameStateManager gsm) {
        super(gsm);
        score=0;
        lama = new Lame(50,200);
        lamePrev = lama.position.y;
        scoreBar = new ScoreBar();

        camera.setToOrtho(false, Lama.WIDTH/2, Lama.HEIGHT/2);

        background = new Texture("d.jpg");
        lastCloud =null;

        clouds = new ArrayList<Cloud>();
        for (int i=1; i<=CLOUDS_COUNT; i++) {
             Cloud cll = new Cloud(i*(SPACE_BETWEEN_CLOUDS+Cloud.CLOUD_HEIGHT),lastCloud);
             clouds.add(cll);
            lastCloud = cll;
            if (Cloud.ran()) {
                clouds.add(new Cloud(i*(SPACE_BETWEEN_CLOUDS+Cloud.CLOUD_HEIGHT)+30,null));
                clouds.get(clouds.size()-1).setBad();

            }

            System.out.println(lastCloud.position.x + " " + lastCloud.position.y + " cloud");
        }
        lowestCloud=clouds.get(0);
        clouds.get(5).moveable=true;
        clouds.get(3).setBad();
        clouds.get(7).setBad();
      //  clouds.get(10).moveable=true;
      //  clouds.get(15).moveable=true;
    //    clouds.get(20).moveable=true;
     //   clouds.get(24).moveable=true;
        camera.position.y=lama.position.y+80;
    }



    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            lama.jump();
          //  camera.position.y+=lama.position.y+80;
            lama.isOnCloud=false;

        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            lama.left();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            lama.right();
        }
    }



    @Override
    public void update(float dt) {
        handleInput();
        lama.update(dt);
       // camera.position.y=lama.position.y+80; //+80
     //   camera.position.y+=lama.position.y-lamePrev;
        camera.position.y+=0.5;
    //    lamePrev = lama.position.y;
        for (int d=0; d<clouds.size(); d++)//repos the clouds
        {
            Cloud cl = clouds.get(d);
            if ((camera.position.y - (camera.viewportHeight/2)) > (cl.position.y+Cloud.CLOUD_HEIGHT+200)) {
                cl.reposition(cl.position.y + ((Cloud.CLOUD_HEIGHT+SPACE_BETWEEN_CLOUDS)*CLOUDS_COUNT),lastCloud);
                lastCloud=cl;
                lowestCloud=clouds.get((d+1)%10);
            }
        }

        for (Cloud c:clouds) {

            if ((lama.position.y<=c.position.y+24) && (lama.position.y>=c.position.y+15) && (lama.position.x+15>=c.position.x) && (lama.position.x<=c.position.x+Cloud.CLOUD_WIDTH)) {
                lama.velocity.set(new Vector3(0,0,0));
                lama.position.y=c.position.y+17;
                score = lama.onCloud(score);
            }
        }


        if (lama.position.y+100<=lowestCloud.position.y) {
            System.out.println("end");
        }




        camera.update();
    }



    @Override
    public void render(SpriteBatch sb) {
      //  sb.setProjectionMatrix(camera.combined);
        sb.begin();
        //sb.draw(scoreBar.digits.get(2), camera.position.x,camera.position.y,13,25);
        sb.setProjectionMatrix(camera.combined);
        sb.draw(background, camera.position.x-camera.viewportWidth/2, camera.position.y-camera.viewportHeight/2, background.getWidth(),background.getHeight());
        for (Cloud c:clouds) {
            c.move();
            sb.draw(c.cloud, c.position.x, c.position.y);
        }
        //sb.draw(scoreBar.digits.get(2), 10,200,13,25);
        sb.draw(scoreBar.digits.get(2), camera.position.x,camera.position.y+camera.viewportHeight/2-28,13,25);

        sb.draw(lama.lame, lama.position.x, lama.position.y, lama.lame.getWidth()/2, lama.lame.getHeight()/2);
        System.out.println(score);

        sb.end();
    }



    @Override
    public void dispose() {
        background.dispose();
        for (Cloud c: clouds) {
           // c.dispose(); ??????????
        }
    }
}
