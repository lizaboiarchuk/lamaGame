package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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
    ImageButton musicOnButton;
    ImageButton musicOffButton;
    ImageButton backButton;
    int musicCount = 0;

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
                dispose();
                startClass.setChoiceScreen();
            }
        });

        Drawable musicOnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/musicOn.png")));
        musicOnButton = new ImageButton(musicOnDrawable);
        musicOnButton.setPosition(StartClass.WIDTH/2-musicOnButton.getWidth()/2, StartClass.HEIGHT/2-musicOnButton.getHeight());

        Drawable backDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/authButton.png")));
        backButton = new ImageButton(backDrawable);
        backButton.setPosition(7, StartClass.HEIGHT-7-backButton.getHeight());
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                dispose();
                startClass.setAuthorizationScreen();
            }
        });
//
//        musicOnButton.addListener(new ClickListener() {
//            public void clicked(InputEvent event, float x, float y){
//                musicCount++;
//                musicOnButton.clear();
//                stage.addActor(musicOffButton);
//                startClass.music.play();
//            }
//        });
//
//        Drawable musicOffDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/musicOff.png")));
//        musicOffButton = new ImageButton(musicOffDrawable);
//        musicOffButton.setPosition(StartClass.WIDTH/2-musicOffButton.getWidth()/2, StartClass.HEIGHT/2-musicOffButton.getHeight());
//        musicOffButton.addListener(new ClickListener() {
//            public void clicked(InputEvent event, float x, float y){
//                musicCount++;
//                musicOffButton.clear();
//                stage.addActor(musicOnButton);
//                startClass.music.dispose();
//            }
//        });

        stage.addActor(backgroundImage);
        stage.addActor(grassImage);
        stage.addActor(sittingLamaImage);
        stage.addActor(playButton);
        stage.addActor(backButton);
      //  stage.addActor(musicOffButton);

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
