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

public class GameOverScreen implements Screen {
    /**
     * parameters
     */
    private StartClass startClass;
    private Stage stage;

    Image backgroundImage;
    Image boardImage;
    Image topBoardImage;
    Image bottomBoardImage;
    Label gameOver;
    Label scoreLabel;
    ImageButton mainMenuButton;
    ImageButton restartButton;
    String scoreString;
    Label scoreDigitsLabel;
    private long score;

    /**
     * game over screen constructor
     * @param startClass
     * @param score
     * @param newHighScore
     */
    public GameOverScreen(final StartClass startClass, long score, boolean newHighScore){
        this.startClass = startClass;
        stage = new Stage(new ScreenViewport());
        this.score = score;

        backgroundImage = new Image(new Texture("blueBackGround.jpg"));
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(StartClass.WIDTH, StartClass.HEIGHT);

        boardImage = new Image(new Texture("uiskins/board.png"));
        boardImage.setPosition(StartClass.WIDTH/2-boardImage.getWidth()/2, StartClass.HEIGHT/2-boardImage.getHeight()/2);

        topBoardImage = new Image(new Texture("uiskins/topBoard.png"));
        topBoardImage.setPosition(StartClass.WIDTH/2-topBoardImage.getWidth()/2, boardImage.getY()+boardImage.getHeight()-topBoardImage.getHeight());

        bottomBoardImage = new Image(new Texture("uiskins/bottomBoard.png"));
        bottomBoardImage.setPosition(StartClass.WIDTH/2-bottomBoardImage.getWidth()/2, boardImage.getY());

        gameOver = new Label("Game Over!", new Label.LabelStyle(startClass.welcomeFont, Color.PINK));
        gameOver.setPosition(StartClass.WIDTH/2-gameOver.getWidth()/2, topBoardImage.getY()-2*gameOver.getHeight());

        scoreDigitsLabel = new Label(String.format("%d", this.score) , new Label.LabelStyle(startClass.welcomeFont, Color.PINK));
        scoreDigitsLabel.setPosition(StartClass.WIDTH/2-scoreDigitsLabel.getWidth()/2, StartClass.HEIGHT/2+scoreDigitsLabel.getHeight()/4);

        scoreString = startClass.highScoreString;
        scoreLabel = new Label(scoreString , new Label.LabelStyle(startClass.welcomeFont, Color.PINK));
        scoreLabel.setPosition(StartClass.WIDTH/2-scoreLabel.getWidth()/2, StartClass.HEIGHT/2+scoreLabel.getHeight()+10);

        Drawable restartDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/restartButton.png")));
        restartButton = new ImageButton(restartDrawable);
        restartButton.setPosition(StartClass.WIDTH/2-restartButton.getWidth()/2, StartClass.HEIGHT/2-70);
        restartButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.user.newHighScoreBool = false;
                startClass.setGameScreen();
            }
        });

        Drawable mainMenuDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/mainMenuButton.png")));
        mainMenuButton = new ImageButton(mainMenuDrawable);
        mainMenuButton.setPosition(StartClass.WIDTH/2-mainMenuButton.getWidth()/2, StartClass.HEIGHT/2-mainMenuButton.getHeight()-90);
        mainMenuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.user.newHighScoreBool = false;
                startClass.setMenuScreen(false, false);
            }
        });

        stage.addActor(backgroundImage);
        stage.addActor(boardImage);
        stage.addActor(topBoardImage);
        stage.addActor(bottomBoardImage);
        stage.addActor(gameOver);
        stage.addActor(scoreLabel);
        stage.addActor(restartButton);
        stage.addActor(mainMenuButton);
        stage.addActor(scoreDigitsLabel);
    }

    /**
     * show screen
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * render screen
     * @param delta
     */
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

    /**
     * dispose
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
