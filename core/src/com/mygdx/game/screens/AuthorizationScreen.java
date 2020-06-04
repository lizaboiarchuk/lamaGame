package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.StartClass;

public class AuthorizationScreen implements Screen {

    private StartClass startClass;
    private Stage stage;

    Image backgroundImage;
    Image grassImage;
    Image sittingLamaImage;
    ImageButton loginButtonImage;
    ImageButton registerButtonImage;
    Label welcomeLabel;
    Label loginLabel;
    Label passwordLabel;
    Label loginButtonLabel;
    Label registerButtonLabel;

    public AuthorizationScreen(final StartClass startClass){
        this.startClass = startClass;
        stage = new Stage(new ScreenViewport());

        backgroundImage = new Image(new Texture("blueBackGround.jpg"));
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(StartClass.WIDTH, StartClass.HEIGHT);

        grassImage = new Image( new Texture("grass.JPEG"));
        grassImage.setPosition(0, -50);

        sittingLamaImage = new Image(new Texture("sittingLama.png"));
        sittingLamaImage.setPosition(StartClass.WIDTH/2-sittingLamaImage.getWidth()/2, 70);

        welcomeLabel = new Label("Welcome to Jumping Lama", new Label.LabelStyle(startClass.welcomeFont, Color.PINK));
        welcomeLabel.setPosition(35, StartClass.HEIGHT-100);

        loginLabel = new Label("Enter your login", new Label.LabelStyle(startClass.whiteFont, Color.WHITE));
        loginLabel.setPosition(StartClass.WIDTH/2-loginLabel.getWidth()/2, StartClass.HEIGHT/2+120);

        passwordLabel = new Label("Enter password", new Label.LabelStyle(startClass.whiteFont, Color.WHITE));
        passwordLabel.setPosition(StartClass.WIDTH/2-passwordLabel.getWidth()/2, StartClass.HEIGHT/2+40);

        loginButtonLabel = new Label("Login", new Label.LabelStyle(startClass.buttonsFont, Color.WHITE));
        loginButtonLabel.setPosition(StartClass.WIDTH/2-loginButtonLabel.getWidth()/2, StartClass.HEIGHT/2-85);

        registerButtonLabel = new Label("Sign Up", new Label.LabelStyle(startClass.buttonsFont, Color.WHITE));
        registerButtonLabel.setPosition(StartClass.WIDTH/2-registerButtonLabel.getWidth()/2, StartClass.HEIGHT/2-145);

        Drawable loginDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/loginButton.png")));
        loginButtonImage = new ImageButton(loginDrawable);
        loginButtonImage.setPosition(StartClass.WIDTH/2-loginButtonImage.getWidth()/2, StartClass.HEIGHT/2-100);
        loginButtonImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                dispose();
                startClass.setMenuScreen();
            }
        });

        loginButtonLabel.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                dispose();
                startClass.setMenuScreen();
            }
        });

        Drawable registerDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/loginButton.png")));
        registerButtonImage = new ImageButton(registerDrawable);
        registerButtonImage.setPosition(StartClass.WIDTH/2-registerButtonImage.getWidth()/2, StartClass.HEIGHT/2-160);
        registerButtonImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                dispose();
                startClass.setRegistrationScreen();
            }
        });

        registerButtonLabel.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                dispose();
                startClass.setRegistrationScreen();
            }
        });

        stage.addActor(backgroundImage);
        stage.addActor(grassImage);
        stage.addActor(sittingLamaImage);
        stage.addActor(loginButtonImage);
        stage.addActor(registerButtonImage);
        stage.addActor(welcomeLabel);
        stage.addActor(loginLabel);
        stage.addActor(passwordLabel);
        stage.addActor(loginButtonLabel);
        stage.addActor(registerButtonLabel);
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
