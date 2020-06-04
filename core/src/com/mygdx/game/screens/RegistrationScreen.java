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

public class RegistrationScreen implements Screen {

    private StartClass startClass;
    private Stage stage;

    Image backgroundImage;
    Image grassImage;
    Image sittingLamaImage;
    ImageButton registerButtonImage;
    ImageButton backButton;
    Label registerButtonLabel;
    Label loginLabel;
    Label passwordLabel;
    Label nameLabel;
    Label checkPasswordLabel;

    public RegistrationScreen(final StartClass startClass){
        this.startClass = startClass;
        stage = new Stage(new ScreenViewport());

        backgroundImage = new Image(new Texture("blueBackGround.jpg"));
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(StartClass.WIDTH, StartClass.HEIGHT);

        grassImage = new Image( new Texture("grass.JPEG"));
        grassImage.setPosition(0, -50);

        sittingLamaImage = new Image(new Texture("sittingLama.png"));
        sittingLamaImage.setPosition(StartClass.WIDTH/2-sittingLamaImage.getWidth()/2, 70);

        registerButtonLabel = new Label("Sign Up", new Label.LabelStyle(startClass.buttonsFont, Color.WHITE));
        registerButtonLabel.setPosition(StartClass.WIDTH/2-registerButtonLabel.getWidth()/2, StartClass.HEIGHT/2-145);

        nameLabel = new Label("Enter your name", new Label.LabelStyle(startClass.whiteFont, Color.WHITE));
        nameLabel.setPosition(StartClass.WIDTH/2-nameLabel.getWidth()/2, StartClass.HEIGHT/2+200);

        loginLabel = new Label("Create your login", new Label.LabelStyle(startClass.whiteFont, Color.WHITE));
        loginLabel.setPosition(StartClass.WIDTH/2-loginLabel.getWidth()/2, StartClass.HEIGHT/2+120);

        passwordLabel = new Label("Create your password", new Label.LabelStyle(startClass.whiteFont, Color.WHITE));
        passwordLabel.setPosition(StartClass.WIDTH/2-passwordLabel.getWidth()/2, StartClass.HEIGHT/2+40);

        checkPasswordLabel = new Label("Verify your password", new Label.LabelStyle(startClass.whiteFont, Color.WHITE));
        checkPasswordLabel.setPosition(StartClass.WIDTH/2-checkPasswordLabel.getWidth()/2, StartClass.HEIGHT/2-40);

        Drawable registerDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/loginButton.png")));
        registerButtonImage = new ImageButton(registerDrawable);
        registerButtonImage.setPosition(StartClass.WIDTH/2-registerButtonImage.getWidth()/2, StartClass.HEIGHT/2-160);
        registerButtonImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                dispose();
                startClass.setMenuScreen();
            }
        });

        registerButtonLabel.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                dispose();
                startClass.setMenuScreen();
            }
        });

        Drawable backDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/backButton.png")));
        backButton = new ImageButton(backDrawable);
        backButton.setPosition(7, StartClass.HEIGHT-7-backButton.getHeight());
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                dispose();
                startClass.setAuthorizationScreen();
            }
        });

        stage.addActor(backgroundImage);
        stage.addActor(grassImage);
        stage.addActor(sittingLamaImage);
        stage.addActor(registerButtonImage);
        stage.addActor(registerButtonLabel);
        stage.addActor(nameLabel);
        stage.addActor(loginLabel);
        stage.addActor(passwordLabel);
        stage.addActor(checkPasswordLabel);
        stage.addActor(backButton);
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
