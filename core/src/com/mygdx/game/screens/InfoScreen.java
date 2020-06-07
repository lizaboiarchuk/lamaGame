package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.StartClass;

public class InfoScreen implements Screen {
    private StartClass startClass;
    private Stage stage;

    Image backgroundImage;
    Image grassImage;
    Image sittingLamaImage;
    Image boardImage;
    Image firstImage;
    Image secondImage;
    Image thirdImage;
    Image fourthImage;
    Image fifthImage;
    Image topBoardImage;
    Image bottomBoardImage;
    ImageButton backButton;
    ImageButton musicOnSmallButton;
    ImageButton musicOffSmallButton;
    ImageButton slideLeftButton;
    ImageButton slideRightButton;
    private int countPage;



    public InfoScreen(final StartClass startClass){
        this.startClass = startClass;
        stage = new Stage(new ScreenViewport());
        countPage = 0;

        backgroundImage = new Image(new Texture("blueBackGround.jpg"));
        backgroundImage.setPosition(0, 0);
        backgroundImage.setSize(StartClass.WIDTH, StartClass.HEIGHT);

        grassImage = new Image( new Texture("grass.JPEG"));
        grassImage.setPosition(0, -50);

        sittingLamaImage = new Image(new Texture("sittingLama.png"));
        sittingLamaImage.setPosition(StartClass.WIDTH/2-sittingLamaImage.getWidth()/2, 70);

        boardImage = new Image(new Texture("uiskins/board.png"));
        boardImage.setPosition(StartClass.WIDTH/2-boardImage.getWidth()/2, grassImage.getHeight()+sittingLamaImage.getHeight()/3);

        topBoardImage = new Image(new Texture("uiskins/topBoard.png"));
        topBoardImage.setPosition(StartClass.WIDTH/2-topBoardImage.getWidth()/2, boardImage.getY()+boardImage.getHeight()-topBoardImage.getHeight());

        bottomBoardImage = new Image(new Texture("uiskins/bottomBoard.png"));
        bottomBoardImage.setPosition(StartClass.WIDTH/2-bottomBoardImage.getWidth()/2, boardImage.getY());

        firstImage = new Image(new Texture("uiskins/oneLevel.png"));
        firstImage.setPosition(boardImage.getX()+boardImage.getWidth()/2-firstImage.getWidth()/2, boardImage.getY()+boardImage.getHeight()/2-firstImage.getHeight()/2);

        secondImage = new Image(new Texture("uiskins/twoLevel.png"));
        secondImage.setPosition(boardImage.getX()+boardImage.getWidth()/2-firstImage.getWidth()/2, boardImage.getY()+boardImage.getHeight()/2-firstImage.getHeight()/2);

        thirdImage = new Image(new Texture("uiskins/threeLevel.png"));
        thirdImage.setPosition(boardImage.getX()+boardImage.getWidth()/2-firstImage.getWidth()/2, boardImage.getY()+boardImage.getHeight()/2-firstImage.getHeight()/2);

        fourthImage = new Image(new Texture("uiskins/fourLevel.png"));
        fourthImage.setPosition(boardImage.getX()+boardImage.getWidth()/2-firstImage.getWidth()/2, boardImage.getY()+boardImage.getHeight()/2-firstImage.getHeight()/2);

        fifthImage = new Image(new Texture("uiskins/fiveLevel.png"));
        fifthImage.setPosition(boardImage.getX()+boardImage.getWidth()/2-firstImage.getWidth()/2, boardImage.getY()+boardImage.getHeight()/2-firstImage.getHeight()/2);

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

        Drawable slideLeftDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/slideLeft.png")));
        slideLeftButton = new ImageButton(slideLeftDrawable);
        slideLeftButton.setPosition(boardImage.getX()/2-slideLeftButton.getWidth()/2, boardImage.getY()+boardImage.getHeight()/2-slideLeftButton.getHeight()/2);
        slideLeftButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                countPage--;
            }
        });

        Drawable slideRightDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("uiskins/slideRight.png")));
        slideRightButton = new ImageButton(slideRightDrawable);
        slideRightButton.setPosition(boardImage.getX()+boardImage.getX()/2+boardImage.getWidth()-slideRightButton.getWidth()/2, boardImage.getY()+boardImage.getHeight()/2-slideRightButton.getHeight()/2);
        slideRightButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                countPage++;
            }
        });

        stage.addActor(backgroundImage);
        stage.addActor(grassImage);
        stage.addActor(sittingLamaImage);
        stage.addActor(backButton);
        stage.addActor(boardImage);
        stage.addActor(topBoardImage);
        stage.addActor(bottomBoardImage);
        stage.addActor(slideLeftButton);
        stage.addActor(slideRightButton);
        stage.addActor(musicOnSmallButton);
        stage.addActor(musicOffSmallButton);
        stage.addActor(firstImage);
        stage.addActor(secondImage);
        stage.addActor(thirdImage);
        stage.addActor(fourthImage);
        stage.addActor(fifthImage);
        musicOnSmallButton.setVisible(false);
        slideLeftButton.setVisible(false);
        firstImage.setVisible(true);
        secondImage.setVisible(false);
        thirdImage.setVisible(false);
        fourthImage.setVisible(false);
        fifthImage.setVisible(false);
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
            firstImage.setVisible(true);
            secondImage.setVisible(false);
            thirdImage.setVisible(false);
            fourthImage.setVisible(false);
            fifthImage.setVisible(false);
        } else if(countPage==1){
            slideLeftButton.setVisible(true);
            slideRightButton.setVisible(true);
            firstImage.setVisible(false);
            secondImage.setVisible(true);
            thirdImage.setVisible(false);
            fourthImage.setVisible(false);
            fifthImage.setVisible(false);
        } else if(countPage==2){
            slideLeftButton.setVisible(true);
            slideRightButton.setVisible(true);
            firstImage.setVisible(false);
            secondImage.setVisible(false);
            thirdImage.setVisible(true);
            fourthImage.setVisible(false);
            fifthImage.setVisible(false);
        } else if(countPage==3){
            slideLeftButton.setVisible(true);
            slideRightButton.setVisible(true);
            firstImage.setVisible(false);
            secondImage.setVisible(false);
            thirdImage.setVisible(false);
            fourthImage.setVisible(true);
            fifthImage.setVisible(false);
        } else if(countPage==4){
            slideRightButton.setVisible(false);
            slideLeftButton.setVisible(true);
            firstImage.setVisible(false);
            secondImage.setVisible(false);
            thirdImage.setVisible(false);
            fourthImage.setVisible(false);
            fifthImage.setVisible(true);
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

    @Override
    public void dispose() {
        stage.dispose();
    }
}
