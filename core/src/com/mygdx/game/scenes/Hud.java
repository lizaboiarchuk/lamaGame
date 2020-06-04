package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.StartClass;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private Integer scoreCounter;
    private Integer coinsCounter;
    private float timeCount;
    private Integer score;
    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font;

    Label welcomeLabel;
    Label scoreLabel;
    Label coinsLabel;
    Label enterNameLabel;
    Label enterLoginLabel;
    Label enterPasswordLabel;
    Label checkPasswordLabel;

    public Hud(SpriteBatch sb){
        score =0;
        viewport = new FitViewport(StartClass.WIDTH, StartClass.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

//        scoreCounter = 0;
//        coinsCounter = 0;

        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Modulus-Bold.otf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 35;
        fontParameter.color= Color.WHITE;
        font = fontGenerator.generateFont(fontParameter);
        welcomeLabel = new Label("Welcome", new Label.LabelStyle(font, Color.WHITE));
//        scoreLabel =  new Label(String.format("%03d", scoreCounter), new Label.LabelStyle(font, Color.WHITE));
//        coinsLabel =  new Label(String.format("%03d", coinsCounter), new Label.LabelStyle(font, Color.WHITE));
        enterNameLabel =  new Label("Enter your name", new Label.LabelStyle(font, Color.WHITE));
        enterLoginLabel = new Label("Enter login", new Label.LabelStyle(font, Color.WHITE));
        enterPasswordLabel = new Label("Enter password", new Label.LabelStyle(font, Color.WHITE));
 //       checkPasswordLabel = new Label("Check password", new Label.LabelStyle(font, Color.WHITE));

        table.add(welcomeLabel).expandY().padTop(50);
        table.add(enterLoginLabel).expandY().padTop(50);
        table.add(enterPasswordLabel).expandY().padTop(50);

    }
}
