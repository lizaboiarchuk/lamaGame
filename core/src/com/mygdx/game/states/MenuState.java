package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.StartClass;

public class MenuState extends State {
    public Texture background;
    public Texture grass;
    public Texture sittingLama;
    public Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        texturesSetUp();
    }

    public void texturesSetUp(){
        background = new Texture("blueBackGround.jpg");
        grass = new Texture("grass.JPEG");
        sittingLama = new Texture("sittingLama.png");
        playButton = new Texture("uiskins/playButton.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, StartClass.WIDTH, StartClass.HEIGHT);
        sb.draw(grass, 0, -50);
        sb.draw(sittingLama, StartClass.WIDTH/2-sittingLama.getWidth()/2, 70);
        sb.draw(playButton, StartClass.WIDTH/2-playButton.getWidth()/2, StartClass.HEIGHT/2+100);
        if(Gdx.input.getX() > 157 &&
                Gdx.input.getX() < 338 &&
                Gdx.input.getY() > 193 &&
                Gdx.input.getY() < 235) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                gsm.set(new ChoiceState(gsm));
            }
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        grass.dispose();
        sittingLama.dispose();
        playButton.dispose();
    }
}
