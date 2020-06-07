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
import com.mygdx.game.StartClass;

public class PauseScreen implements Screen {
    private StartClass startClass;
    private Stage stage;
    Label pauseLabel;
    Image pauseBoard;
    Image pauseTopBoard;
    ImageButton resumeButton;
    ImageButton mainMenuButton;
    ImageButton musicOnButton;
    ImageButton musicOffButton;

    public PauseScreen(final StartClass startClass, final Stage stage){
        this.startClass = startClass;
        this.stage = stage;
        startClass.pausedScreenOn = true;

        pauseBoard = new Image(new Texture("uiskins/pauseBoard.png"));
        pauseBoard.setPosition(StartClass.WIDTH/2-pauseBoard.getWidth()/2, StartClass.HEIGHT/2-pauseBoard.getHeight()/2);

        pauseTopBoard = new Image(new Texture("uiskins/pauseTopBoard.png"));
        pauseTopBoard.setPosition(StartClass.WIDTH/2-pauseTopBoard.getWidth()/2, pauseBoard.getY()+pauseBoard.getHeight()-pauseTopBoard.getHeight());

        pauseLabel = new Label("Pause", new Label.LabelStyle(startClass.pauseFont, Color.WHITE));
        pauseLabel.setPosition(StartClass.WIDTH/2-pauseLabel.getWidth()/2, pauseTopBoard.getY()+10);

        Drawable resumeDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/resumeButton.png")));
        resumeButton = new ImageButton(resumeDrawable);
        resumeButton.setPosition(pauseBoard.getX()+50, pauseTopBoard.getY()-resumeButton.getHeight());
        resumeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.pausedScreenOn = false;
            }
        });

        Drawable musicOnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/musicOn.png")));
        musicOnButton = new ImageButton(musicOnDrawable);
        musicOnButton.setPosition(resumeButton.getX(), resumeButton.getY()-musicOnButton.getHeight());

        Drawable musicOffDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/musicOff.png")));
        musicOffButton = new ImageButton(musicOffDrawable);
        musicOffButton.setPosition(resumeButton.getX(), resumeButton.getY()-musicOffButton.getHeight());

        Drawable mainMenuDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/mainMenuButton.png")));
        mainMenuButton = new ImageButton(mainMenuDrawable);
        mainMenuButton.setPosition(musicOnButton.getX(), musicOnButton.getY()-mainMenuButton.getHeight());
        mainMenuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                startClass.pausedScreenOn = false;
                startClass.disposeGameScreen = true;
                dispose();
                startClass.setMenuScreen(false, false);
            }
        });

        stage.addActor(pauseBoard);
        stage.addActor(pauseTopBoard);
        stage.addActor(pauseLabel);
        stage.addActor(resumeButton);
        stage.addActor(mainMenuButton);
        stage.addActor(musicOnButton);
        stage.addActor(musicOffButton);

        musicOnButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                startClass.musicOn = true;
                musicOnButton.setVisible(false);
                musicOffButton.setVisible(true);
            }
        });

        musicOffButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                startClass.musicOn = false;
                musicOnButton.setVisible(true);
                musicOffButton.setVisible(false);
            }
        });

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
        pauseBoard.clear();
        pauseTopBoard.clear();
        pauseLabel.clear();
        resumeButton.clear();
        mainMenuButton.clear();
    }
}
