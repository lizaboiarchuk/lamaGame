package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.StartClass;

public class ChoiceState extends State {
    public Texture background;
    public Texture grass;
    public Texture sittingLama;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font;
    private Texture oneLevel;
    private Texture twoLevel;
    private Texture threeLevel;
    private Texture fourLevel;
    private Texture fiveLevel;
    private Texture backButton;

    public ChoiceState(GameStateManager gsm) {
        super(gsm);
        texturesSetUp();
    }

    public void texturesSetUp(){
        background = new Texture("blueBackGround.jpg");
        grass = new Texture("grass.JPEG");
        sittingLama = new Texture("sittingLama.png");
        oneLevel = new Texture("uiskins/oneLevel.png");
        twoLevel = new Texture("uiskins/twoLevel.png");
        threeLevel = new Texture("uiskins/threeLevel.png");
        fourLevel = new Texture("uiskins/fourLevel.png");
        fiveLevel = new Texture("uiskins/fiveLevel.png");
        backButton = new Texture("uiskins/backButton.png");
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Modulus-Bold.otf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 35;
        fontParameter.color= Color.PINK;
        font = fontGenerator.generateFont(fontParameter);
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
        sb.draw(background,0,0, StartClass.WIDTH,StartClass.HEIGHT);
        sb.draw(grass, 0, -50);
        sb.draw(sittingLama, StartClass.WIDTH/2-sittingLama.getWidth()/2, 70);
        sb.draw(oneLevel, 100, 350);
        sb.draw(twoLevel, 162, 350);
        sb.draw(threeLevel, 224, 350);
        sb.draw(fourLevel, 286, 350);
        sb.draw(fiveLevel, 348, 350);
        sb.draw(backButton, 5, StartClass.HEIGHT-backButton.getHeight()-5);
        font.draw(sb, "Choose difficulty", 120, StartClass.HEIGHT/2+150);
       /*
        if (Gdx.input.isTouched()) {
            System.out.println("x = " + Gdx.input.getX() + " ; y = " + Gdx.input.getY());
        }

        */
        if(Gdx.input.getX() > 111 &&
                Gdx.input.getX() < 129 &&
                Gdx.input.getY() > 310 &&
                Gdx.input.getY() < 340) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                gsm.set(new PlayState(gsm, 1));
            }
        }
        if(Gdx.input.getX() > 173 &&
                Gdx.input.getX() < 202 &&
                Gdx.input.getY() > 310 &&
                Gdx.input.getY() < 340) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                gsm.set(new PlayState(gsm, 2));
            }
        }
        if(Gdx.input.getX() > 235 &&
                Gdx.input.getX() < 264 &&
                Gdx.input.getY() > 310 &&
                Gdx.input.getY() < 340) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                gsm.set(new PlayState(gsm, 3));
            }
        }
        if(Gdx.input.getX() > 297 &&
                Gdx.input.getX() < 326 &&
                Gdx.input.getY() > 310 &&
                Gdx.input.getY() < 340) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                gsm.set(new PlayState(gsm, 4));
            }
        }
        if(Gdx.input.getX() > 359 &&
                Gdx.input.getX() < 377 &&
                Gdx.input.getY() > 310 &&
                Gdx.input.getY() < 340) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                gsm.set(new PlayState(gsm, 5));
            }
        }


        if(Gdx.input.getX() > 5 &&
                Gdx.input.getX() < 45 &&
                Gdx.input.getY() > 5 &&
                Gdx.input.getY() < 48) {
            if (Gdx.input.isTouched()) {
                this.dispose();
                gsm.set(new MenuState(gsm));
            }
        }

        sb.end();
    }

    @Override
    public void dispose() {
        backButton.dispose();
        background.dispose();
        grass.dispose();
        sittingLama.dispose();
        fontGenerator.dispose();
        oneLevel.dispose();
        twoLevel.dispose();
        threeLevel.dispose();
        fourLevel.dispose();
        fiveLevel.dispose();
    }

}
