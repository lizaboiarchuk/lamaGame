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

public class PauseScreen implements Screen {
    private StartClass startClass;
    private Stage stage;
    Label pauseLabel;
    Image pauseBoard;
    Image pauseTopBoard;
    ImageButton resumeButton;
    ImageButton mainMenuButton;

    public PauseScreen(final StartClass startClass, final Stage stage){
        this.startClass = startClass;
        this.stage = stage;

        pauseBoard = new Image(new Texture("uiskins/pauseBoard.png"));
        pauseBoard.setPosition(StartClass.WIDTH/2-pauseBoard.getWidth()/2, StartClass.HEIGHT/2-pauseBoard.getHeight()/2);

        pauseTopBoard = new Image(new Texture("uiskins/pauseTopBoard.png"));
        pauseTopBoard.setPosition(StartClass.WIDTH/2-pauseTopBoard.getWidth()/2, pauseBoard.getY()+pauseBoard.getHeight());

        pauseLabel = new Label("Pause", new Label.LabelStyle(startClass.pauseFont, Color.WHITE));
        pauseLabel.setPosition(StartClass.WIDTH/2-pauseLabel.getWidth()/2, pauseTopBoard.getY()+10);

        Drawable resumeDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/resumeButton.png")));
        resumeButton = new ImageButton(resumeDrawable);
        resumeButton.setPosition(pauseBoard.getX()+50, pauseTopBoard.getY()-resumeButton.getHeight()-18);
        resumeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();

            }
        });

        Drawable mainMenuDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/mainMenuButton.png")));
        mainMenuButton = new ImageButton(mainMenuDrawable);
        mainMenuButton.setPosition(pauseBoard.getX()+50, pauseBoard.getY()+28);
        mainMenuButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;


                startClass.setMenuScreen(false, false);
            }
        });

        stage.addActor(pauseBoard);
        stage.addActor(pauseTopBoard);
        stage.addActor(pauseLabel);
        stage.addActor(resumeButton);
        stage.addActor(mainMenuButton);

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
