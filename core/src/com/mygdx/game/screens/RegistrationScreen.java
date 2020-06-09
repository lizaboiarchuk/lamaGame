package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.StartClass;
import com.mygdx.game.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RegistrationScreen implements Screen {

    /**
     * parameters
     */
    private StartClass startClass;
    private Stage stage;

    Image backgroundImage;
    Image grassImage;
    Image sittingLamaImage;
    ImageButton registerButtonImage;
    ImageButton backButton;
    Label registerButtonLabel;
    Label loginLabel;
    Label passwordLabel;
    Label nameLabel;
    Label checkPasswordLabel;
    Drawable textFieldBack;
    TextField.TextFieldStyle style;
    TextField nameTextField;
    TextField passwordTextField;
    TextField loginTextField;
    TextField checkPasswordTextField;
    String nameInput;
    String loginInput;
    String passwordInput;
    String checkPasswordInput;
    ImageButton musicOnSmallButton;
    ImageButton musicOffSmallButton;

    /**
     * registration screen constructor
     * @param startClass
     */
    public RegistrationScreen(final StartClass startClass){
        this.startClass = startClass;
        stage = new Stage(new ScreenViewport());

        backgroundImage = new Image(new Texture("blueBackGround.jpg"));
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(StartClass.WIDTH, StartClass.HEIGHT);

        grassImage = new Image( new Texture("grass.JPEG"));
        grassImage.setPosition(0, -50);

        sittingLamaImage = new Image(new Texture("sittingLama.png"));
        sittingLamaImage.setPosition(StartClass.WIDTH/2-sittingLamaImage.getWidth()/2, 70);

        registerButtonLabel = new Label("Sign Up", new Label.LabelStyle(startClass.buttonsFont, Color.WHITE));
        registerButtonLabel.setPosition(StartClass.WIDTH/2-registerButtonLabel.getWidth()/2, StartClass.HEIGHT/2-145);

        nameLabel = new Label("Enter your name", new Label.LabelStyle(startClass.whiteFont, Color.WHITE));
        nameLabel.setPosition(StartClass.WIDTH/2-nameLabel.getWidth()/2, StartClass.HEIGHT/2+200);

        loginLabel = new Label("Create your login", new Label.LabelStyle(startClass.whiteFont, Color.WHITE));
        loginLabel.setPosition(StartClass.WIDTH/2-loginLabel.getWidth()/2, StartClass.HEIGHT/2+120);

        passwordLabel = new Label("Create your password", new Label.LabelStyle(startClass.whiteFont, Color.WHITE));
        passwordLabel.setPosition(StartClass.WIDTH/2-passwordLabel.getWidth()/2, StartClass.HEIGHT/2+40);

        checkPasswordLabel = new Label("Verify your password", new Label.LabelStyle(startClass.whiteFont, Color.WHITE));
        checkPasswordLabel.setPosition(StartClass.WIDTH/2-checkPasswordLabel.getWidth()/2, StartClass.HEIGHT/2-40);

        textFieldBack = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/textFieldBorder.png")));
        style = new TextField.TextFieldStyle();
        style.font = startClass.whiteFont;
        style.fontColor = Color.BLACK;
        style.background = textFieldBack;

        loginTextField = new TextField("", style);
        loginTextField.setPosition(StartClass.WIDTH/2-loginTextField.getWidth(), StartClass.HEIGHT/2+80);
        loginTextField.setSize(300, 30);

        passwordTextField = new TextField("", style);
        passwordTextField.setPosition(StartClass.WIDTH/2-passwordTextField.getWidth(), StartClass.HEIGHT/2);
        passwordTextField.setSize(300, 30);
        passwordTextField.setPasswordCharacter('*');
        passwordTextField.setPasswordMode(true);

        nameTextField = new TextField("", style);
        nameTextField.setPosition(StartClass.WIDTH/2-nameTextField.getWidth(), StartClass.HEIGHT/2+160);
        nameTextField.setSize(300, 30);

        checkPasswordTextField = new TextField("", style);
        checkPasswordTextField.setPosition(StartClass.WIDTH/2-checkPasswordTextField.getWidth(), StartClass.HEIGHT/2-80);
        checkPasswordTextField.setSize(300, 30);
        checkPasswordTextField.setPasswordCharacter('*');
        checkPasswordTextField.setPasswordMode(true);

        Drawable registerDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/loginButton.png")));
        registerButtonImage = new ImageButton(registerDrawable);
        registerButtonImage.setPosition(StartClass.WIDTH/2-registerButtonImage.getWidth()/2, StartClass.HEIGHT/2-160);
        registerButtonImage.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                nameInput = nameTextField.getText();
                loginInput = loginTextField.getText();
                passwordInput = passwordTextField.getText();
                checkPasswordInput = checkPasswordTextField.getText();
                System.out.println("name = " + nameInput + " ; login = " + loginInput + " ; password = " + passwordInput + " ; checked password = " + checkPasswordInput);
                if(loginInput.matches("[A-Za-z0-9_@.]+") &&
                        passwordInput.matches("[A-Za-z0-9_@.]+") &&
                        nameInput.matches("[A-Za-z]+") &&
                        checkPasswordInput.matches("[A-Za-z0-9_@.]+") &&
                        (passwordInput.equals(checkPasswordInput))) {

                    User newUser = new User();
                    newUser.setName(nameInput);
                    newUser.setLogin(loginInput);
                    newUser.setPassword(passwordInput);
                    newUser.setMoney(0);
                    newUser.setHighScore(0);
                    newUser.setMagnetPurchased(0);
                    newUser.setWingsPurchased(0);
                    newUser.setRocketPurchased(0);
                    newUser.setDoubleBonusPurchased(0);
                    try {
                        FileWriter fileWriter  = new FileWriter("C:\\jl/users.txt");
                        FileReader fr = new FileReader("C:\\jl/users.txt");
                        Scanner sc = new Scanner(fr);
                        while (sc.hasNextLine()) {
                            String p = sc.nextLine();
                            System.out.println(p);
                            fileWriter.append(p);
                        }
                        fr.close();
                        String k = newUser.name + " " + newUser.login + " " + newUser.password + " " + newUser.highScore + " " + newUser.money + " "+ newUser.magnetPurchased+ " " + newUser.wingsPurchased+ " " + newUser.rocketPurchased +" " + newUser.doubleBonusPurchased + " k";
                        fileWriter.append(k);
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    startClass.userBase.users.add(newUser);
                    startClass.userBase.write();
                    startClass.setUser(newUser);
                    dispose();
                    startClass.setMenuScreen(true, true);
                } else {
                    if(loginInput.equals("") || passwordInput.equals("") || nameInput.equals("") || checkPasswordInput.equals("")){
                        MessageCloud messageCloud4 = new MessageCloud(startClass, stage, " Field cannot\n  be empty.");
                    } else if (!passwordInput.equals(checkPasswordInput)) {
                        MessageCloud messageCloud2 = new MessageCloud(startClass, stage, "       Oops! Passwords\n doesn`t match.");
                    } else {
                        MessageCloud messageCloud = new MessageCloud(startClass, stage, "     Oops!\nInvalid input");
                        if (!nameInput.matches("[A-Za-z]+")) {
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    MessageCloud messageCloud1 = new MessageCloud(startClass, stage, "Only letters are\n accepted in\n name field.");
                                }
                            }, 2f);
                        } else {
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    MessageCloud messageCloud3 = new MessageCloud(startClass, stage, "Only letters\n and digits\nare accepted.");
                                }
                            }, 2f);
                        }
                    }
                }
            }
        });


        registerButtonLabel.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                nameInput = nameTextField.getText();
                loginInput = loginTextField.getText();
                passwordInput = passwordTextField.getText();
                checkPasswordInput = checkPasswordTextField.getText();
                System.out.println("name = " + nameInput + " ; login = " + loginInput + " ; password = " + passwordInput + " ; checked password = " + checkPasswordInput);
                if(loginInput.matches("[A-Za-z0-9_@.]+") &&
                        passwordInput.matches("[A-Za-z0-9_@.]+") &&
                        nameInput.matches("[A-Za-z]+") &&
                        checkPasswordInput.matches("[A-Za-z0-9_@.]+") &&
                        (passwordInput.equals(checkPasswordInput))) {

                    User newUser = new User();
                    newUser.setName(nameInput);
                    newUser.setLogin(loginInput);
                    newUser.setPassword(passwordInput);
                    newUser.setMoney(0);
                    newUser.setHighScore(0);
                    newUser.setMagnetPurchased(0);
                    newUser.setWingsPurchased(0);
                    newUser.setRocketPurchased(0);
                    newUser.setDoubleBonusPurchased(0);
                    try {
                        FileWriter fileWriter  = new FileWriter("C:\\jl/users.txt");
                        FileReader fr = new FileReader("C:\\jl/users.txt");
                        Scanner sc = new Scanner(fr);
                        while (sc.hasNextLine()) {
                            String p = sc.nextLine();
                            System.out.println(p);
                            fileWriter.append(p);
                        }
                        fr.close();
                        String k = newUser.name + " " + newUser.login + " " + newUser.password + " " + newUser.highScore + " " + newUser.money + " "+ newUser.magnetPurchased+ " " + newUser.wingsPurchased+ " " + newUser.rocketPurchased +" " + newUser.doubleBonusPurchased + " k";
                        fileWriter.append(k);
                        fileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    startClass.userBase.users.add(newUser);
                    startClass.userBase.write();
                    startClass.setUser(newUser);
                    dispose();
                    startClass.setMenuScreen(true, true);
                } else {
                    if(loginInput.equals("") || passwordInput.equals("") || nameInput.equals("") || checkPasswordInput.equals("")){
                        MessageCloud messageCloud4 = new MessageCloud(startClass, stage, " Field cannot\n  be empty.");
                    } else if (!passwordInput.equals(checkPasswordInput)) {
                        MessageCloud messageCloud2 = new MessageCloud(startClass, stage, " Oops! Passwords\n   doesn`t match.");
                    } else {
                        MessageCloud messageCloud = new MessageCloud(startClass, stage, "     Oops!\nInvalid input");
                        if (!nameInput.matches("[A-Za-z]+")) {
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    MessageCloud messageCloud1 = new MessageCloud(startClass, stage, "Only letters are\n accepted in\n name field.");
                                }
                            }, 2f);
                        } else {
                            Timer.schedule(new Timer.Task() {
                                @Override
                                public void run() {
                                    MessageCloud messageCloud3 = new MessageCloud(startClass, stage, "Only letters\n and digits\nare accepted.");
                                }
                            }, 2f);
                        }
                    }
                }
            }
        });

        Drawable backDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/backButton.png")));
        backButton = new ImageButton(backDrawable);
        backButton.setPosition(7, StartClass.HEIGHT-7-backButton.getHeight());
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.setAuthorizationScreen();
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
        stage.addActor(registerButtonImage);
        stage.addActor(registerButtonLabel);
        stage.addActor(nameLabel);
        stage.addActor(loginLabel);
        stage.addActor(passwordLabel);
        stage.addActor(checkPasswordLabel);
        stage.addActor(backButton);
        stage.addActor(nameTextField);
        stage.addActor(passwordTextField);
        stage.addActor(loginTextField);
        stage.addActor(checkPasswordTextField);
        stage.addActor(musicOnSmallButton);
        stage.addActor(musicOffSmallButton);
        musicOnSmallButton.setVisible(false);
    }

    /**
     * show stage
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

    /**
     * dispose
     */
    @Override
    public void dispose() {
        stage.dispose();
    }
}
