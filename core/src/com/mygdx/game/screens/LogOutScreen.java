package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.StartClass;

public class LogOutScreen implements Screen {
    private StartClass startClass;
    private Stage stage;
    Label warningLabel;
    Image logoutBoard;
    Image logoutTopBoard;
    ImageButton yesButton;
    ImageButton cancelButton;
    Label areYouSureLabel;


    public LogOutScreen(final StartClass startClass, final Stage stage){
        this.startClass = startClass;
        this.stage = stage;

        logoutBoard = new Image(new Texture("uiskins/pauseBoard.png"));
        logoutBoard.setPosition(StartClass.WIDTH/2- logoutBoard.getWidth()/2, StartClass.HEIGHT/2- logoutBoard.getHeight()/2);

        logoutTopBoard = new Image(new Texture("uiskins/pauseTopBoard.png"));
        logoutTopBoard.setPosition(StartClass.WIDTH/2- logoutTopBoard.getWidth()/2, logoutBoard.getY()+ logoutBoard.getHeight()- logoutTopBoard.getHeight());

        warningLabel = new Label("Warning", new Label.LabelStyle(startClass.pauseFont, Color.WHITE));
        warningLabel.setPosition(StartClass.WIDTH/2- warningLabel.getWidth()/2, logoutTopBoard.getY()+10);

        areYouSureLabel= new Label("Are you sure you want\n          to log out?", new Label.LabelStyle(startClass.sureFont, Color.BLACK));
        areYouSureLabel.setPosition(StartClass.WIDTH/2- areYouSureLabel.getWidth()/2, logoutTopBoard.getY()-areYouSureLabel.getPrefHeight()-5);

        Drawable yesDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/yesButton.png")));
        yesButton = new ImageButton(yesDrawable);
        yesButton.setPosition(StartClass.WIDTH/2-yesButton.getWidth()/2, areYouSureLabel.getY()-yesButton.getHeight()-4);
        yesButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                startClass.user=null;
                startClass.userWelcomeString=null;
                stage.dispose();
                dispose();
                startClass.setAuthorizationScreen();
            }
        });

        Drawable cancelDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/cancelButton.png")));
        cancelButton = new ImageButton(cancelDrawable);
        cancelButton.setPosition(yesButton.getX(), yesButton.getY()- cancelButton.getHeight()-4);
        cancelButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
            }
        });

        stage.addActor(logoutBoard);
        stage.addActor(logoutTopBoard);
        stage.addActor(warningLabel);
        stage.addActor(areYouSureLabel);
        stage.addActor(yesButton);
        stage.addActor(cancelButton);

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
        logoutBoard.addAction(Actions.removeActor());
        logoutTopBoard.addAction(Actions.removeActor());
        warningLabel.addAction(Actions.removeActor());
        areYouSureLabel.addAction(Actions.removeActor());
        yesButton.addAction(Actions.removeActor());
        cancelButton.addAction(Actions.removeActor());
    }

}
