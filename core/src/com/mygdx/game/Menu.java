package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;


import javax.swing.*;

//Main menu with autorization and registration button
public class Menu implements Screen {

    Info inf;
    Game game;
    Stage stage;
    SpriteBatch batch;
    TextButton enter;
    TextButton registrate;
    TextArea login;
    TextArea password;
    Texture background;
    Skin skin;
    Label loginLabel;
    Label passwordLabel;
    Texture name;



    //constructor

    Menu(Game game, Info inf) {
        this.inf = inf;
        this.game=game;
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        background = new Texture("fon.jpg");
        name = new Texture("lama.png");


        skin = new Skin();
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));
        skin.add("default", new BitmapFont());
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white",Color.LIGHT_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        TextField.TextFieldStyle style = new TextField.TextFieldStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.DARK_GRAY;
        style.background = skin.newDrawable("white", Color.WHITE);

        login = new TextArea("",style);
        password = new TextArea("", style);
        login.setWidth(120);
        password.setWidth(120);


        enter = new TextButton(" Enter!  ", skin);
        registrate = new TextButton(" Registrate  ", skin);
        enter.setPosition(250-enter.getWidth()/2, 200);
        registrate.setPosition(250-registrate.getWidth()/2,250);

        login.setPosition(210, 350);
        password.setPosition(210,300);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        labelStyle.fontColor = Color.WHITE;
        labelStyle.background = skin.newDrawable("white", Color.LIGHT_GRAY);

        loginLabel = new Label(" login:  ", labelStyle);
        passwordLabel = new Label(" password:  ", labelStyle);
        loginLabel.setPosition(170-loginLabel.getWidth()/2,350);
        passwordLabel.setPosition(130,300);



        stage.addActor(enter);
        stage.addActor(registrate);
        stage.addActor(login);
        stage.addActor(password);
        stage.addActor(loginLabel);
        stage.addActor(passwordLabel);

        enterPressed();
        registratePressed();


    }






    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,0,0,500,700);
        batch.draw(name, 90,470,320,70);
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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

    }

    void enterPressed() {
        enter.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                String log = login.getText();
                String pass = password.getText();
                if (inf.userbase.checkUser(new User(log,pass))) {
                    nextScreen();
                    System.out.println("OK");
                }
                else System.out.println("Not OK");
            }
        });
    }

    void registratePressed() {
        registrate.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Registration(game,inf));
            }
        });
    }





    void nextScreen() {

    }



}
