package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.StartClass;

public class AuthorizationState extends State {

    public Texture background;
    public Texture grass;
    public Texture sittingLama;
    private Texture loginButton;
    private Texture loginButtonPressed;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter2;
    private FreeTypeFontGenerator.FreeTypeFontParameter loginFontParameter;
    private BitmapFont font;
    private BitmapFont font2;
    private BitmapFont loginFont;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private Skin defaultSkin;
    private Stage stage;

    public AuthorizationState(GameStateManager gsm) {
        super(gsm);
        texturesSetUp();
    }

    public void texturesSetUp(){
        background = new Texture("blueBackGround.jpg");
        grass = new Texture("grass.JPEG");
        sittingLama = new Texture("sittingLama.png");
        loginButton = new Texture("uiskins/loginButton.png");
        loginButtonPressed = new Texture("uiskins/loginButtonPressed.png");
        defaultSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Modulus-Bold.otf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 35;
        fontParameter.color= Color.PINK;
        font = fontGenerator.generateFont(fontParameter);
        fontParameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter2.size = 25;
        fontParameter2.color= Color.WHITE;
        font2 = fontGenerator.generateFont(fontParameter2);
        loginFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        loginFontParameter.size = 30;
        loginFontParameter.color= Color.WHITE;
        loginFont = fontGenerator.generateFont(loginFontParameter);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        usernameTextField = new TextField("", defaultSkin);
        passwordTextField = new TextField("", defaultSkin);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background,0,0, StartClass.WIDTH, StartClass.HEIGHT);
        sb.draw(grass, 0, -50);
        sb.draw(sittingLama, StartClass.WIDTH/2-sittingLama.getWidth()/2, 70);
        sb.draw(loginButton, StartClass.WIDTH/2-loginButton.getWidth()/2, StartClass.HEIGHT/2-100);
        font.draw(sb, "Welcome to Jumping Lama!", 35, StartClass.HEIGHT-50);
        font2.draw(sb, "Enter your username", 125, StartClass.HEIGHT/2+120);
        font2.draw(sb, "Enter your password", 125, StartClass.HEIGHT/2+40);
        loginFont.draw(sb, "Login", StartClass.WIDTH/2-33, StartClass.HEIGHT/2-60);
        usernameTextField.setPosition(StartClass.WIDTH/2, StartClass.HEIGHT/2+60,100);
        usernameTextField.setSize(250, 25);
        usernameTextField.draw(sb, 60);
        passwordTextField.setPosition(StartClass.WIDTH/2, StartClass.HEIGHT/2-15,100);
        passwordTextField.setSize(250, 25);
        passwordTextField.draw(sb, 60);
        stage.act();
        if(Gdx.input.getX() > 185 &&
        Gdx.input.getX() < 320 &&
        Gdx.input.getY() > 405 &&
        Gdx.input.getY() < 438) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                gsm.set(new MenuState(gsm));
            }
        }
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        grass.dispose();
        sittingLama.dispose();
        usernameTextField.cut();
        passwordTextField.cut();
        fontGenerator.dispose();
    }

}
