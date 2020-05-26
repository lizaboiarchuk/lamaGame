package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    public OrthographicCamera camera;
    public Vector3 mouse;
    public GameStateManager gsm;

    State(GameStateManager gsm) {
        this.gsm=gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }


    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();




 }
