package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Lama;
import com.mygdx.game.sprites.Cloud;
import com.mygdx.game.sprites.Lame;

import java.util.ArrayList;

public class PlayState extends State{

    Lame lama;
    Texture background;


    public static final int SPACE_BETWEEN_CLOUDS = 60;
    public static final int CLOUDS_COUNT = 7;

    public ArrayList<Cloud> clouds;




    PlayState(GameStateManager gsm) {
        super(gsm);
        lama = new Lame(50,200);
        camera.setToOrtho(false, Lama.WIDTH/2, Lama.HEIGHT/2);
        background = new Texture("space.png");

        clouds = new ArrayList<Cloud>();

        for (int i=1; i<CLOUDS_COUNT; i++) {
            clouds.add(new Cloud(i*(SPACE_BETWEEN_CLOUDS+Cloud.CLOUD_HEIGHT)));


        }



    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            lama.jump();
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
        camera.position.y=lama.position.y+80;

        for (Cloud cl:clouds) {
            if ((camera.position.y - (camera.viewportHeight/2)) > (cl.position.y+Cloud.CLOUD_HEIGHT)) {
                cl.reposition(cl.position.y + ((Cloud.CLOUD_HEIGHT+SPACE_BETWEEN_CLOUDS)*CLOUDS_COUNT));
            }
        }
        camera.update();


    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, camera.position.x-camera.viewportWidth/2, 0, background.getWidth()*0.4f,background.getHeight()*0.4f);
        for (Cloud c:clouds) {
            sb.draw(c.cloud, c.position.x, c.position.y);
        }

        sb.draw(lama.lame, lama.position.x, lama.position.y, lama.lame.getWidth()/2, lama.lame.getHeight()/2);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
