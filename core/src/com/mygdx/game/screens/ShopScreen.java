package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.StartClass;

public class ShopScreen implements Screen {
    private StartClass startClass;
    private Stage stage;

    Image backgroundImage;
    Image grassImage;
    Image sittingLamaImage;
    ImageButton backButton;
    ImageButton musicOnSmallButton;
    ImageButton musicOffSmallButton;
    ImageButton slideLeftButton;
    ImageButton slideRightButton;
    ImageButton buyMagnetButton;
    ImageButton buyWingsButton;
    ImageButton buyRocketButton;
    ImageButton buyDoubleBonusButton;
    Image magnetImage;
    Image wingsImage;
    Image rocketImage;
    Image doubleBonusImage;
    Image coinImage;
    private int countPage;
    Label objectNameLabel;
    private String objectNameString;
    Label descriptionLabel;
    private String descriptionString;
    Label priceLabel;
    private String priceString;

    
    public ShopScreen(final StartClass startClass){
        this.startClass = startClass;
        stage = new Stage(new ScreenViewport());
        countPage = 0;
        objectNameString = "";
        descriptionString = "";
        priceString = "";

        backgroundImage = new Image(new Texture("blueBackGround.jpg"));
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(StartClass.WIDTH, StartClass.HEIGHT);

        grassImage = new Image( new Texture("grass.JPEG"));
        grassImage.setPosition(0, -50);

        sittingLamaImage = new Image(new Texture("sittingLama.png"));
        sittingLamaImage.setPosition(StartClass.WIDTH/2-sittingLamaImage.getWidth()/2, 70);

        magnetImage = new Image(new Texture("uiskins/buyMagnetImage.png"));
        magnetImage.setPosition(StartClass.WIDTH/2- magnetImage.getWidth()/2, StartClass.HEIGHT/2- magnetImage.getHeight()/2+grassImage.getHeight()/2-30);

        wingsImage = new Image(new Texture("uiskins/buyWingsImage.png"));
        wingsImage.setPosition(StartClass.WIDTH/2- wingsImage.getWidth()/2, StartClass.HEIGHT/2- wingsImage.getHeight()/2+grassImage.getHeight()/2-30);

        rocketImage = new Image(new Texture("uiskins/buyRocketImage.png"));
        rocketImage.setPosition(StartClass.WIDTH/2- rocketImage.getWidth()/2, StartClass.HEIGHT/2- rocketImage.getHeight()/2+grassImage.getHeight()/2-30);

        doubleBonusImage = new Image(new Texture("uiskins/buyDoubleBonusImage.png"));
        doubleBonusImage.setPosition(StartClass.WIDTH/2- doubleBonusImage.getWidth()/2, StartClass.HEIGHT/2- doubleBonusImage.getHeight()/2+grassImage.getHeight()/2-30);

        coinImage = new Image(new Texture("uiskins/coinImage.png"));
        coinImage.setPosition(StartClass.WIDTH/2- 2*coinImage.getWidth()+coinImage.getWidth()/2, magnetImage.getY()-80);

        objectNameLabel = new Label(objectNameString, new Label.LabelStyle(startClass.welcomeFont, Color.PINK));
        descriptionLabel = new Label(descriptionString, new Label.LabelStyle(startClass.whiteFont, Color.WHITE));
        priceLabel = new Label(priceString, new Label.LabelStyle(startClass.priceFont, Color.YELLOW));

        Drawable backDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/homeButton.png")));
        backButton = new ImageButton(backDrawable);
        backButton.setPosition(7, StartClass.HEIGHT-7-backButton.getHeight());
        backButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                dispose();
                startClass.setMenuScreen();
            }
        });

        Drawable slideLeftDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/slideLeft.png")));
        slideLeftButton = new ImageButton(slideLeftDrawable);
        slideLeftButton.setPosition(37.5f-slideLeftButton.getWidth()/2, StartClass.HEIGHT/2-slideLeftButton.getHeight()/2+grassImage.getHeight()/2-30);
        slideLeftButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                countPage--;
            }
        });

        Drawable slideRightDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/slideRight.png")));
        slideRightButton = new ImageButton(slideRightDrawable);
        slideRightButton.setPosition(462.5f-slideRightButton.getWidth()/2, StartClass.HEIGHT/2-slideRightButton.getHeight()/2+grassImage.getHeight()/2-30);
        slideRightButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                countPage++;
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

        Drawable buyMagnetDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/buyButton.png")));
        buyMagnetButton = new ImageButton(buyMagnetDrawable);
        buyMagnetButton.setPosition(StartClass.WIDTH/2-buyMagnetButton.getWidth()/2, StartClass.HEIGHT/2-3*buyMagnetButton.getHeight());
        buyMagnetButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                System.out.println("buy magnet");
            }
        });

        Drawable buyWingsDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/buyButton.png")));
        buyWingsButton = new ImageButton(buyWingsDrawable);
        buyWingsButton.setPosition(StartClass.WIDTH/2-buyWingsButton.getWidth()/2, StartClass.HEIGHT/2-3*buyWingsButton.getHeight());
        buyWingsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                System.out.println("buy wings");
            }
        });

        Drawable buyRocketDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/buyButton.png")));
        buyRocketButton = new ImageButton(buyRocketDrawable);
        buyRocketButton.setPosition(StartClass.WIDTH/2-buyRocketButton.getWidth()/2, StartClass.HEIGHT/2-3*buyRocketButton.getHeight());
        buyRocketButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                System.out.println("buy rocket");
            }
        });

        Drawable buyDoubleBonusDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/buyButton.png")));
        buyDoubleBonusButton = new ImageButton(buyDoubleBonusDrawable);
        buyDoubleBonusButton.setPosition(StartClass.WIDTH/2-buyDoubleBonusButton.getWidth()/2, StartClass.HEIGHT/2-3*buyDoubleBonusButton.getHeight());
        buyDoubleBonusButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                System.out.println("buy double bonus");
            }
        });

        stage.addActor(backgroundImage);
        stage.addActor(grassImage);
        stage.addActor(sittingLamaImage);
        stage.addActor(backButton);
        stage.addActor(slideLeftButton);
        stage.addActor(slideRightButton);
        slideLeftButton.setVisible(false);
        stage.addActor(magnetImage);
        stage.addActor(wingsImage);
        stage.addActor(rocketImage);
        stage.addActor(doubleBonusImage);
        magnetImage.setVisible(true);
        wingsImage.setVisible(false);
        rocketImage.setVisible(false);
        doubleBonusImage.setVisible(false);
        stage.addActor(musicOnSmallButton);
        stage.addActor(musicOffSmallButton);
        musicOnSmallButton.setVisible(false);
        stage.addActor(buyMagnetButton);
        stage.addActor(buyWingsButton);
        stage.addActor(buyRocketButton);
        stage.addActor(buyDoubleBonusButton);
        buyMagnetButton.setVisible(true);
        buyWingsButton.setVisible(false);
        buyRocketButton.setVisible(false);
        buyDoubleBonusButton.setVisible(false);
        stage.addActor(objectNameLabel);
        stage.addActor(descriptionLabel);
        stage.addActor(coinImage);
        stage.addActor(priceLabel);
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
        if(startClass.musicOn){
            musicOnSmallButton.setVisible(false);
            musicOffSmallButton.setVisible(true);
        } else{
            musicOnSmallButton.setVisible(true);
            musicOffSmallButton.setVisible(false);
        }
        if(countPage==0){
            slideRightButton.setVisible(true);
            slideLeftButton.setVisible(false);
            magnetImage.setVisible(true);
            wingsImage.setVisible(false);
            rocketImage.setVisible(false);
            doubleBonusImage.setVisible(false);
            buyMagnetButton.setVisible(true);
            buyWingsButton.setVisible(false);
            buyRocketButton.setVisible(false);
            buyDoubleBonusButton.setVisible(false);
            objectNameString = "Magnet";
            descriptionString = "       collect all the coins you \ncome across without any efforts";
            priceString = "100";
        } else if(countPage==1){
            slideLeftButton.setVisible(true);
            slideRightButton.setVisible(true);
            magnetImage.setVisible(false);
            wingsImage.setVisible(true);
            rocketImage.setVisible(false);
            doubleBonusImage.setVisible(false);
            buyMagnetButton.setVisible(false);
            buyWingsButton.setVisible(true);
            buyRocketButton.setVisible(false);
            buyDoubleBonusButton.setVisible(false);
            objectNameString = "Wings";
            descriptionString = "           save yourself from falling\nand don`t bother about the black clouds";
            priceString = "200";
        } else if(countPage==2){
            slideLeftButton.setVisible(true);
            slideRightButton.setVisible(true);
            magnetImage.setVisible(false);
            wingsImage.setVisible(false);
            rocketImage.setVisible(true);
            doubleBonusImage.setVisible(false);
            buyMagnetButton.setVisible(false);
            buyWingsButton.setVisible(false);
            buyRocketButton.setVisible(true);
            buyDoubleBonusButton.setVisible(false);
            objectNameString = "Rocket";
            descriptionString = "have a rest and fly only upwards";
            priceString = "300";
        } else if(countPage==3){
            slideLeftButton.setVisible(true);
            slideRightButton.setVisible(false);
            magnetImage.setVisible(false);
            wingsImage.setVisible(false);
            rocketImage.setVisible(false);
            doubleBonusImage.setVisible(true);
            buyMagnetButton.setVisible(false);
            buyWingsButton.setVisible(false);
            buyRocketButton.setVisible(false);
            buyDoubleBonusButton.setVisible(true);
            objectNameString = "Double Bonus";
            descriptionString = "double all your achievments";
            priceString = "400";
        }
        objectNameLabel.setText(objectNameString);
        objectNameLabel.setPosition(StartClass.WIDTH/2-objectNameLabel.getPrefWidth()/2, StartClass.HEIGHT-100);
        descriptionLabel.setText(descriptionString);
        descriptionLabel.setPosition(StartClass.WIDTH/2-descriptionLabel.getPrefWidth()/2, StartClass.HEIGHT-160);
        priceLabel.setText(priceString);
        priceLabel.setPosition(StartClass.WIDTH/2-priceLabel.getPrefWidth()/2+coinImage.getWidth()/4, coinImage.getY()+coinImage.getHeight()/2);
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
        stage.dispose();
    }
}
