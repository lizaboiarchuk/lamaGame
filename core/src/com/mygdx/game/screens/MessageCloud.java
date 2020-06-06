package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.StartClass;

public class MessageCloud implements Screen {
    private StartClass startClass;
    private Stage stage;
    Image cloudImage;
    Label messageLabel;
    String message;
    private long time;
    private long delayLong = 2000L;
    private float delay = 2;

    public MessageCloud(StartClass startClass, Stage stage, String message){
        this.startClass = startClass;
        this.stage = stage;
        this.message = message;

        cloudImage = new Image(new Texture("uiskins/cloudMessageImage.png"));
        cloudImage.setPosition(StartClass.WIDTH/2+7, 128);

        messageLabel = new Label(message, new Label.LabelStyle(startClass.messageFont, Color.BLACK));
        messageLabel.setPosition(cloudImage.getX()+cloudImage.getPrefWidth()/2-messageLabel.getPrefWidth()/2, cloudImage.getY()+cloudImage.getHeight()/2-messageLabel.getPrefHeight()/2+5);

        stage.addActor(cloudImage);
        stage.addActor(messageLabel);

        Timer.schedule(new Timer.Task(){
            @Override
            public void run() {
                cloudImage.addAction(Actions.fadeOut(1f));
                messageLabel.addAction(Actions.fadeOut(1f));
            }
        }, delay);
    }


    @Override
    public void show() {

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
        cloudImage.clear();
        messageLabel.clear();
    }
}
