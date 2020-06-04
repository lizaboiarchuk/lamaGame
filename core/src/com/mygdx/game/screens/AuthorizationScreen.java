package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.StartClass;
import com.mygdx.game.states.AuthorizationState;

public class AuthorizationScreen implements Screen {

    private StartClass startClass;
    private Stage stage;

    public Texture background;
    public Texture grass;
    public Texture sittingLama;
    private Texture loginButton;
    Image backgroundImage;
    Image grassImage;
    Image sittingLamaImage;
    ImageButton loginButtonImage;

    public AuthorizationScreen(StartClass startClass){
        this.startClass = startClass;
        stage = new Stage(new ScreenViewport());

        background = new Texture("blueBackGround.jpg");
        backgroundImage = new Image(background);
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(StartClass.WIDTH, StartClass.HEIGHT);
        grass = new Texture("grass.JPEG");
        grassImage = new Image(grass);
        grassImage.setPosition(0, -50);
        sittingLama = new Texture("sittingLama.png");
        sittingLamaImage = new Image(sittingLama);
        sittingLamaImage.setPosition(StartClass.WIDTH/2-sittingLama.getWidth()/2, 70);
        loginButton = new Texture("uiskins/loginButton.png");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(loginButton));
        loginButtonImage = new ImageButton(drawable);
        loginButtonImage.setPosition(StartClass.WIDTH/2-loginButton.getWidth()/2, StartClass.HEIGHT/2-100);
        loginButtonImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                System.out.println("clicked");
            }
        });

        stage.addActor(backgroundImage);
        stage.addActor(grassImage);
        stage.addActor(sittingLamaImage);
        stage.addActor(loginButtonImage);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
