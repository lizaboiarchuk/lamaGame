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

public class MenuScreen implements Screen {

    private StartClass startClass;
    private Stage stage;

    Image backgroundImage;
    Image grassImage;
    Image sittingLamaImage;
    ImageButton playButton;
    ImageButton infoButton;
    ImageButton backButton;
    ImageButton shopButton;
    ImageButton musicOnSmallButton;
    ImageButton musicOffSmallButton;
    MessageCloud messageCloud;

    public MenuScreen(final StartClass startClass){
        this.startClass = startClass;
        stage = new Stage(new ScreenViewport());

        backgroundImage = new Image(new Texture("blueBackGround.jpg"));
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(StartClass.WIDTH, StartClass.HEIGHT);

        grassImage = new Image( new Texture("grass.JPEG"));
        grassImage.setPosition(0, -50);

        sittingLamaImage = new Image(new Texture("sittingLama.png"));
        sittingLamaImage.setPosition(StartClass.WIDTH/2-sittingLamaImage.getWidth()/2, 70);

        Drawable registerDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/playButton.png")));
        playButton = new ImageButton(registerDrawable);
        playButton.setPosition(StartClass.WIDTH/2-playButton.getWidth()/2, StartClass.HEIGHT/2+playButton.getHeight());
        playButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.setChoiceScreen();
            }
        });

        Drawable shopDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/shopButton.png")));
        shopButton = new ImageButton(shopDrawable);
        shopButton.setPosition(StartClass.WIDTH/2-shopButton.getWidth()/2, StartClass.HEIGHT/2);
        shopButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.setShopScreen();
            }
        });

        Drawable backDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/authButton.png")));
        backButton = new ImageButton(backDrawable);
        backButton.setPosition(7, StartClass.HEIGHT-7-backButton.getHeight());
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                startClass.user=null;
                startClass.userWelcomeString=null;
                dispose();
                startClass.setAuthorizationScreen();
            }
        });

        Drawable infoDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/infoButton.png")));
        infoButton = new ImageButton(infoDrawable);
        infoButton.setPosition(StartClass.WIDTH/2-infoButton.getWidth()/2, StartClass.HEIGHT/2-infoButton.getHeight());
        infoButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.setInfoScreen();
            }
        });

        Drawable musicOnSmallDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/musicOnSmallButton.png")));
        musicOnSmallButton = new ImageButton(musicOnSmallDrawable);
        musicOnSmallButton.setPosition(StartClass.WIDTH-musicOnSmallButton.getWidth()-7, StartClass.HEIGHT-musicOnSmallButton.getHeight()-7);
        musicOnSmallButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                startClass.musicOn = true;
            }
        });

        Drawable musicOffSmallDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/musicOffSmallButton.png")));
        musicOffSmallButton = new ImageButton(musicOffSmallDrawable);
        musicOffSmallButton.setPosition(StartClass.WIDTH-musicOffSmallButton.getWidth()-7, StartClass.HEIGHT-musicOffSmallButton.getHeight()-7);
        musicOffSmallButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                startClass.musicOn = false;
            }
        });

        startClass.disposeGameScreen = false;

        stage.addActor(backgroundImage);
        stage.addActor(grassImage);
        stage.addActor(sittingLamaImage);
        stage.addActor(playButton);
        stage.addActor(backButton);
        stage.addActor(shopButton);
        stage.addActor(infoButton);
        stage.addActor(musicOnSmallButton);
        stage.addActor(musicOffSmallButton);
        musicOnSmallButton.setVisible(false);
        if(startClass.firstEntrance) messageCloud = new MessageCloud(startClass, stage, startClass.userWelcomeString);
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
        if(startClass.musicOn){
            musicOnSmallButton.setVisible(false);
            musicOffSmallButton.setVisible(true);
        } else{
            musicOnSmallButton.setVisible(true);
            musicOffSmallButton.setVisible(false);
        }
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
