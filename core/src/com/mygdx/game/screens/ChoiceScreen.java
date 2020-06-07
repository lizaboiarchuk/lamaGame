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

public class ChoiceScreen implements Screen {

    private StartClass startClass;
    private Stage stage;

    Image backgroundImage;
    Image grassImage;
    Image sittingLamaImage;
    Label choiceLabel;
    ImageButton oneLevel;
    ImageButton twoLevel;
    ImageButton threeLevel;
    ImageButton fourLevel;
    ImageButton fiveLevel;
    ImageButton backButton;
    ImageButton musicOnSmallButton;
    ImageButton musicOffSmallButton;
    Label currentScore;
    Label currentScoreDigits;

    public ChoiceScreen(final StartClass startClass){
        this.startClass = startClass;
        stage = new Stage(new ScreenViewport());

        backgroundImage = new Image(new Texture("blueBackGround.jpg"));
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(StartClass.WIDTH, StartClass.HEIGHT);

        grassImage = new Image( new Texture("grass.JPEG"));
        grassImage.setPosition(0, -50);

        sittingLamaImage = new Image(new Texture("sittingLama.png"));
        sittingLamaImage.setPosition(StartClass.WIDTH/2-sittingLamaImage.getWidth()/2, 70);

        currentScore = new Label("Your current high score", new Label.LabelStyle(startClass.welcomeFont, Color.PINK));
        currentScore.setPosition(StartClass.WIDTH/2-currentScore.getWidth()/2, StartClass.HEIGHT/2+200);

        currentScoreDigits = new Label(String.format("%d", startClass.user.getHighScore()), new Label.LabelStyle(startClass.welcomeFont, Color.PINK));
        currentScoreDigits.setPosition(StartClass.WIDTH/2-currentScoreDigits.getWidth()/2, StartClass.HEIGHT/2+150);

        choiceLabel = new Label("Choose difficulty", new Label.LabelStyle(startClass.pauseFont, Color.WHITE));
        choiceLabel.setPosition(StartClass.WIDTH/2-choiceLabel.getWidth()/2, StartClass.HEIGHT/2+40);

        Drawable oneLevelDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/oneLevel.png")));
        oneLevel = new ImageButton(oneLevelDrawable);
        oneLevel.setPosition(50, StartClass.HEIGHT/2-oneLevel.getHeight());
        oneLevel.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.setGameMode(1);
                startClass.setGameScreen();
            }
        });

        Drawable twoLevelDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/twoLevel.png")));
        twoLevel = new ImageButton(twoLevelDrawable);
        twoLevel.setPosition(133, StartClass.HEIGHT/2-twoLevel.getHeight());
        twoLevel.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.setGameMode(2);
                startClass.setGameScreen();
            }
        });

        Drawable threeDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/threeLevel.png")));
        threeLevel = new ImageButton(threeDrawable);
        threeLevel.setPosition(216, StartClass.HEIGHT/2-threeLevel.getHeight());
        threeLevel.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.setGameMode(3);
                startClass.setGameScreen();
            }
        });

        Drawable fourLevelDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/fourLevel.png")));
        fourLevel = new ImageButton(fourLevelDrawable);
        fourLevel.setPosition(299, StartClass.HEIGHT/2-fourLevel.getHeight());
        fourLevel.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.setGameMode(4);
                startClass.setGameScreen();
            }
        });

        Drawable fiveLevelDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/fiveLevel.png")));
        fiveLevel = new ImageButton(fiveLevelDrawable);
        fiveLevel.setPosition(382, StartClass.HEIGHT/2-fiveLevel.getHeight());
        fiveLevel.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.setGameMode(5);
                startClass.setGameScreen();
            }
        });

        Drawable backDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/homeButton.png")));
        backButton = new ImageButton(backDrawable);
        backButton.setPosition(7, StartClass.HEIGHT-7-backButton.getHeight());
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.setMenuScreen(false, false);
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

        stage.addActor(backgroundImage);
        stage.addActor(grassImage);
        stage.addActor(sittingLamaImage);
        stage.addActor(choiceLabel);
        stage.addActor(oneLevel);
        stage.addActor(twoLevel);
        stage.addActor(threeLevel);
        stage.addActor(fourLevel);
        stage.addActor(fiveLevel);
        stage.addActor(backButton);
        stage.addActor(musicOnSmallButton);
        stage.addActor(musicOffSmallButton);
        musicOnSmallButton.setVisible(false);
        stage.addActor(currentScore);
        stage.addActor(currentScoreDigits);
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
