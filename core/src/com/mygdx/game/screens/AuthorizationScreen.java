package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.StartClass;
import com.mygdx.game.states.AuthorizationState;

public class AuthorizationScreen implements Screen {

    private StartClass startClass;
    public Texture background;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public Texture grass;
    public Texture sittingLama;
    private Texture loginButton;
    Image loginButtonImage;

    public AuthorizationScreen(StartClass startClass){
        this.startClass = startClass;
        background = new Texture("blueBackGround.jpg");
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(StartClass.WIDTH, StartClass.HEIGHT, gameCam);
    }

    public AuthorizationScreen(){
        super();

        loginButton = new Texture("uiskins/loginButton.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      //  startClass.batch.setProjectionMatrix(gameCam.combined);
        startClass.batch.begin();
        startClass.batch.draw(background, 0, 0);
        startClass.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    }
}
