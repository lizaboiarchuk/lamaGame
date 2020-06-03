package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.StartClass;
import com.mygdx.game.StartClass;
import com.mygdx.game.User;

public class MenuState extends State {

    private Texture back;
    private Texture playBtn;
    public User user;



    public MenuState(GameStateManager gsm) {
        super(gsm);
        back = new Texture("fon.jpg");
        playBtn = new Texture("butt.png");
        gsm.currentUser = gsm.userbase.users.get(0);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {

           gsm.set(new PlayState(gsm,2));


        }

    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(back,0,0, StartClass.WIDTH,StartClass.HEIGHT);
        sb.draw(playBtn, StartClass.WIDTH/2-40, 300,80,80);
        sb.end();
    }

    @Override
    public void dispose() {
        back.dispose();
        playBtn.dispose();
    }
}
