package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class AuthorizationScreen extends AbstractScreen {

    public Texture background;
    public Texture grass;
    public Texture sittingLama;
    private Texture loginButton;
    Image loginButtonImage;

    public AuthorizationScreen(){
        super();

        loginButton = new Texture("uiskins/loginButton.png");
    }

    @Override
    public void buildStage() {
        System.out.println("authorization");

        loginButtonImage = new Image(loginButton);
        loginButtonImage.setPosition(300, 300);


        loginButtonImage.addListener(UIFactory.createListener(ScreenEnum.MENU_SCREEN));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        addActor(loginButtonImage);
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
        super.dispose();
        loginButton.dispose();
    }
}
