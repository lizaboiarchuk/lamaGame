package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.mygdx.game.StartClass;

public class RegistrationState extends State {
    public Texture background;
    public Texture grass;
    public Texture sittingLama;
    private Texture registrationButton;
    private Texture backButton;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter2;
    private FreeTypeFontGenerator.FreeTypeFontParameter signUpFontParameter;
    private BitmapFont font2;
    private BitmapFont signUpFont;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private TextField nameTextField;
    private TextField checkPasswordTextField;
    private Skin defaultSkin;

    public RegistrationState(GameStateManager gsm) {
        super(gsm);
        System.out.println("Registration");
        texturesSetUp();
    }

    public void texturesSetUp(){
        background = new Texture("blueBackGround.jpg");
        grass = new Texture("grass.JPEG");
        sittingLama = new Texture("sittingLama.png");
        registrationButton = new Texture("uiskins/loginButton.png");
        backButton = new Texture("uiskins/backButton.png");
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Modulus-Bold.otf"));
        defaultSkin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        signUpFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        signUpFontParameter.size = 30;
        signUpFontParameter.color= Color.WHITE;
        signUpFont = fontGenerator.generateFont(signUpFontParameter);
        fontParameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter2.size = 25;
        fontParameter2.color= Color.WHITE;
        font2 = fontGenerator.generateFont(fontParameter2);
        nameTextField = new TextField("", defaultSkin);
        checkPasswordTextField = new TextField("", defaultSkin);
        usernameTextField = new TextField("", defaultSkin);
        passwordTextField = new TextField("", defaultSkin);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            gsm.set(new PlayState(gsm, 1));
        }
        if(Gdx.input.getX() > 185 &&
                Gdx.input.getX() < 320 &&
                Gdx.input.getY() > 465 &&
                Gdx.input.getY() < 498) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                gsm.set(new MenuState(gsm));
            }
        }
        if(Gdx.input.getX() > 5 &&
                Gdx.input.getX() < 45 &&
                Gdx.input.getY() > 5 &&
                Gdx.input.getY() < 48) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                gsm.set(new AuthorizationState(gsm));
            }
        }
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
        sb.draw(registrationButton, StartClass.WIDTH/2-registrationButton.getWidth()/2, StartClass.HEIGHT/2-160);
        sb.draw(backButton, 5, StartClass.HEIGHT-backButton.getHeight()-5);
        signUpFont.draw(sb, "Sign up", StartClass.WIDTH/2-42, StartClass.HEIGHT/2-120);
        font2.draw(sb, "Enter your name", 125, StartClass.HEIGHT/2+200);
        font2.draw(sb, "Enter your login", 125, StartClass.HEIGHT/2+120);
        font2.draw(sb, "Enter your password", 125, StartClass.HEIGHT/2+40);
        font2.draw(sb, "Check your password", 125, StartClass.HEIGHT/2-40);
        usernameTextField.setPosition(StartClass.WIDTH/2, StartClass.HEIGHT/2+60,100);
        usernameTextField.setSize(250, 25);
        usernameTextField.draw(sb, 60);
        passwordTextField.setPosition(StartClass.WIDTH/2, StartClass.HEIGHT/2-15,100);
        passwordTextField.setSize(250, 25);
        passwordTextField.draw(sb, 60);
        nameTextField.setPosition(StartClass.WIDTH/2, StartClass.HEIGHT/2+135,100);
        nameTextField.setSize(250, 25);
        nameTextField.draw(sb, 60);
        checkPasswordTextField.setPosition(StartClass.WIDTH/2, StartClass.HEIGHT/2-90,100);
        checkPasswordTextField.setSize(250, 25);
        checkPasswordTextField.draw(sb, 60);

        sb.end();
    }

    @Override
    public void dispose() {
        backButton.dispose();
        background.dispose();
        grass.dispose();
        sittingLama.dispose();
        nameTextField.cut();
        checkPasswordTextField.cut();
        registrationButton.dispose();
        usernameTextField.cut();
        passwordTextField.cut();
        fontGenerator.dispose();
    }
}
