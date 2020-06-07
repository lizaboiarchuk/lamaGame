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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.StartClass;
import com.mygdx.game.sprites.Tube;

import java.util.ArrayList;

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
    Label moneyCount;
    Image moneyBag;
    Image bonusesImage[];
    ArrayList<Tube> tubesGrey;
    Label bonusesNumberLabel[];
    int bonusesNumber[];

    
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

        moneyBag = new Image(new Texture("uiskins/moneybagBigger.png"));
        moneyBag.setPosition(startClass.WIDTH/2, startClass.HEIGHT-80);

        moneyCount = new Label(String.format("%d", startClass.user.getMoney()), new Label.LabelStyle(startClass.priceFont, startClass.priceFont.getColor()));
        moneyCount.setPosition(moneyBag.getX() - moneyCount.getPrefWidth(), startClass.HEIGHT - moneyCount.getHeight()-14.5f);

        magnetImage = new Image(new Texture("uiskins/buyMagnetImage.png"));
        magnetImage.setPosition(StartClass.WIDTH/2- magnetImage.getWidth()/2, StartClass.HEIGHT/2- magnetImage.getHeight()/2+grassImage.getHeight()/2-10);

        wingsImage = new Image(new Texture("uiskins/buyWingsImage.png"));
        wingsImage.setPosition(StartClass.WIDTH/2- wingsImage.getWidth()/2, StartClass.HEIGHT/2- wingsImage.getHeight()/2+grassImage.getHeight()/2-10);

        rocketImage = new Image(new Texture("uiskins/buyRocketImage.png"));
        rocketImage.setPosition(StartClass.WIDTH/2- rocketImage.getWidth()/2, StartClass.HEIGHT/2- rocketImage.getHeight()/2+grassImage.getHeight()/2-10);

        doubleBonusImage = new Image(new Texture("uiskins/buyDoubleBonusImage.png"));
        doubleBonusImage.setPosition(StartClass.WIDTH/2- doubleBonusImage.getWidth()/2, StartClass.HEIGHT/2- doubleBonusImage.getHeight()/2+grassImage.getHeight()/2-10);

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
                startClass.setMenuScreen(false, false);
            }
        });

        Drawable slideLeftDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/slideLeft.png")));
        slideLeftButton = new ImageButton(slideLeftDrawable);
        slideLeftButton.setPosition(37.5f-slideLeftButton.getWidth()/2, StartClass.HEIGHT/2-slideLeftButton.getHeight()/2+grassImage.getHeight()/2-10);
        slideLeftButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                countPage--;
            }
        });

        Drawable slideRightDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/slideRight.png")));
        slideRightButton = new ImageButton(slideRightDrawable);
        slideRightButton.setPosition(462.5f-slideRightButton.getWidth()/2, StartClass.HEIGHT/2-slideRightButton.getHeight()/2+grassImage.getHeight()/2-10);
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
        buyMagnetButton.setPosition(StartClass.WIDTH/2-buyMagnetButton.getWidth()/2, StartClass.HEIGHT/2-3*buyMagnetButton.getHeight()+20);
        buyMagnetButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                int moneyLeft = startClass.user.getMoney() - Integer.parseInt(priceString);
                if(moneyLeft < 0){
                    MessageCloud messageCloud = new MessageCloud(startClass, stage, "Not enough money");
                } else {
                    startClass.user.purchaseMagnet();
                    startClass.user.setMoney(moneyLeft);
                }
            }
        });

        Drawable buyWingsDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/buyButton.png")));
        buyWingsButton = new ImageButton(buyWingsDrawable);
        buyWingsButton.setPosition(StartClass.WIDTH/2-buyWingsButton.getWidth()/2, StartClass.HEIGHT/2-3*buyWingsButton.getHeight()+20);
        buyWingsButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                int moneyLeft = startClass.user.getMoney() - Integer.parseInt(priceString);
                if(moneyLeft < 0){
                    MessageCloud messageCloud = new MessageCloud(startClass, stage, "Not enough money");
                } else {
                    startClass.user.purchaseWings();
                    startClass.user.setMoney(moneyLeft);
                }
            }
        });

        Drawable buyRocketDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/buyButton.png")));
        buyRocketButton = new ImageButton(buyRocketDrawable);
        buyRocketButton.setPosition(StartClass.WIDTH/2-buyRocketButton.getWidth()/2, StartClass.HEIGHT/2-3*buyRocketButton.getHeight()+20);
        buyRocketButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                int moneyLeft = startClass.user.getMoney() - Integer.parseInt(priceString);
                if(moneyLeft < 0){
                    MessageCloud messageCloud = new MessageCloud(startClass, stage, "Not enough money");
                } else {
                    startClass.user.purchaseRocket();
                    startClass.user.setMoney(moneyLeft);
                }
            }
        });

        Drawable buyDoubleBonusDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/buyButton.png")));
        buyDoubleBonusButton = new ImageButton(buyDoubleBonusDrawable);
        buyDoubleBonusButton.setPosition(StartClass.WIDTH/2-buyDoubleBonusButton.getWidth()/2, StartClass.HEIGHT/2-3*buyDoubleBonusButton.getHeight()+20);
        buyDoubleBonusButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                startClass.clicksoundbool = true;
                int moneyLeft = startClass.user.getMoney() - Integer.parseInt(priceString);
                if(moneyLeft < 0){
                    MessageCloud messageCloud = new MessageCloud(startClass, stage, "Not enough money");
                } else {
                    startClass.user.purchaseDoubleBonus();
                    startClass.user.setMoney(moneyLeft);
                }
            }
        });

        bonusesImage = new Image[4];
        bonusesImage[0] = new Image(new Texture("magnit.png"));
        bonusesImage[1] = new Image( new Texture("wings.png"));
        bonusesImage[2] = new Image(new Texture("jetpack.png"));
        bonusesImage[3] = new Image(new Texture("doubleBonus.png"));
        tubesGrey = new ArrayList<Tube>();
        bonusesNumber = new int[4];
        bonusesNumberLabel = new Label[4];
        for (int i=0;i<4;i++) {
            Image t = new Image(new Texture("tubeGrey.png"));
            Tube tu = new Tube();
            tu.tube=t;
            tubesGrey.add(tu);
        }

        tubesGrey.get(0).tube.setBounds(startClass.WIDTH / 2 - 100, 0, 50, 50);
        tubesGrey.get(1).tube.setBounds(startClass.WIDTH / 2 - 50, 0, 50, 50);
        tubesGrey.get(2).tube.setBounds(startClass.WIDTH / 2, 0, 50, 50);
        tubesGrey.get(3).tube.setBounds(startClass.WIDTH / 2 + 50, 0, 50, 50);

        bonusesImage[0].setBounds(startClass.WIDTH / 2 - 90, 14, 30, 28);
        bonusesImage[1].setBounds(startClass.WIDTH / 2 - 50, 20, 50, 20);
        bonusesImage[2].setBounds(startClass.WIDTH / 2 + 5, 18, 40, 28);
        bonusesImage[3].setBounds(startClass.WIDTH / 2 + 60, 16, 30, 30);

        bonusesNumber[0] = startClass.user.getMagnetPurchased();
        bonusesNumber[1] = startClass.user.getWingsPurchased();
        bonusesNumber[2] = startClass.user.getRocketPurchased();
        bonusesNumber[3] = startClass.user.getDoubleBonusPurchased();

        bonusesNumberLabel[0] = new Label(String.format("%d", bonusesNumber[0]), new Label.LabelStyle(startClass.bonusFont, Color.BLACK));
        bonusesNumberLabel[1] = new Label(String.format("%d", bonusesNumber[1]), new Label.LabelStyle(startClass.bonusFont, Color.BLACK));
        bonusesNumberLabel[2] = new Label(String.format("%d", bonusesNumber[2]), new Label.LabelStyle(startClass.bonusFont, Color.BLACK));
        bonusesNumberLabel[3] = new Label(String.format("%d", bonusesNumber[3]), new Label.LabelStyle(startClass.bonusFont, Color.BLACK));

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
        stage.addActor(moneyBag);
        stage.addActor(moneyCount);
        for (int i = 0; i < 4; i++) stage.addActor(tubesGrey.get(i).tube);
        stage.addActor(bonusesImage[0]);
        stage.addActor(bonusesImage[1]);
        stage.addActor(bonusesImage[2]);
        stage.addActor(bonusesImage[3]);
        for (int i = 0; i < 4; i++) stage.addActor(bonusesNumberLabel[i]);
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
            priceString = "90";
            bonusesNumber[0] = startClass.user.getMagnetPurchased();
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
            priceString = "180";
            bonusesNumber[1] = startClass.user.getWingsPurchased();
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
            priceString = "165";
            bonusesNumber[2] = startClass.user.getRocketPurchased();
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
            priceString = "120";
            bonusesNumber[3] = startClass.user.getDoubleBonusPurchased();
        }
        objectNameLabel.setText(objectNameString);
        objectNameLabel.setPosition(StartClass.WIDTH/2-objectNameLabel.getPrefWidth()/2, StartClass.HEIGHT-100);
        descriptionLabel.setText(descriptionString);
        descriptionLabel.setPosition(StartClass.WIDTH/2-descriptionLabel.getPrefWidth()/2, StartClass.HEIGHT-160);
        priceLabel.setText(priceString);
        priceLabel.setPosition(StartClass.WIDTH/2-priceLabel.getPrefWidth()/2+coinImage.getWidth()/4, coinImage.getY()+coinImage.getHeight()/2);
        moneyCount.setText(String.format("%d", startClass.user.getMoney()));
        moneyCount.setPosition(moneyBag.getX() - moneyCount.getPrefWidth(), startClass.HEIGHT - moneyCount.getHeight()-14.5f);

        bonusesNumberLabel[0].setText(String.format("%d", bonusesNumber[0]));
        bonusesNumberLabel[1].setText(String.format("%d", bonusesNumber[1]));
        bonusesNumberLabel[2].setText(String.format("%d", bonusesNumber[2]));
        bonusesNumberLabel[3].setText(String.format("%d", bonusesNumber[3]));

        bonusesNumberLabel[0].setPosition(startClass.WIDTH / 2 - 78, 2);
        bonusesNumberLabel[1].setPosition(startClass.WIDTH / 2 - 28, 2);
        bonusesNumberLabel[2].setPosition(startClass.WIDTH / 2 + 21, 2);
        bonusesNumberLabel[3].setPosition(startClass.WIDTH / 2 + 71, 2);
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
