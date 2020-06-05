package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.StartClass;
import com.mygdx.game.sprites.Cloud;
import com.mygdx.game.sprites.Lame;

import java.util.ArrayList;

public class GameScreen implements Screen {
    StartClass game;

    Lame lama; //main character
    Texture coinPic;
    Texture magnitPic;
    Texture redString;
    Texture background;
    Texture jetpack;
    Texture blueString;
    Texture pampers;
    Texture whiteS;
    SpriteBatch sb;

    boolean begins=true;
    int timer=0;

    public Texture siitingLama = new Texture("sittingLama.png");
    public Texture grass = new Texture("grass.JPEG");


    Image moneyBag;
    Label moneyCount, scoreCount;

    Stage stage;

    public int gameMode;

    public static final int bonusTimer = 1000; //(*delta time (0.22sec))
    public int timeCounter;
    public boolean drawMagnet=true;

    public static final int SPACE_BETWEEN_CLOUDS = 60;
    public static final int CLOUDS_COUNT = 18;

    public Cloud lastCloud;
    public Cloud lowestCloud;

    public ArrayList<Cloud> clouds; //all the clouds
    public long score; // current score
    public long money; //current coins

    float lamePrev;

    //sounds
    public Sound coinSound;
    public Sound endSound;
    public OrthographicCamera camera;

    public ArrayList<Texture> backgrounds;

    public GameScreen(StartClass game, int gameMode) {
        this.game = game;
        this.gameMode=gameMode;
        sb = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        score=0;
        money=0;
        camera = new OrthographicCamera();

        coinSound = Gdx.audio.newSound(Gdx.files.internal("coin_sound.wav"));
        endSound = Gdx.audio.newSound(Gdx.files.internal("end.wav"));

        coinPic = new Texture("coin.png");
        magnitPic = new Texture("magnit.png");
        redString = new Texture("redS.jpg");
        blueString = new Texture("blueS.png");
        jetpack = new Texture("jetpack.png");
        pampers = new Texture("pampers.png");
        whiteS = new Texture("whiteS.png");
        moneyBag = new Image(new Texture("moneybag.png"));

        camera.setToOrtho(false, StartClass.WIDTH/2, StartClass.HEIGHT/2);

        backgrounds = new ArrayList<Texture>();
        backgrounds.add(new Texture("d.jpg"));
        backgrounds.add(new Texture("pinkBackGround.jpg"));
        backgrounds.add(new Texture("yellowBackGround.jpg"));
        backgrounds.add(new Texture("blueBackGround.jpg"));
        backgrounds.add(new Texture("greenBackGround.jpg"));

        background = backgrounds.get(gameMode-1);


        scoreCount = new Label(String.format("%05d", this.score), new Label.LabelStyle(game.welcomeFont, Color.PINK));
        scoreCount.setPosition(game.WIDTH-moneyBag.getWidth(), game.HEIGHT-moneyBag.getHeight());

        moneyCount = new Label(String.format("%03d", this.money), new Label.LabelStyle(game.welcomeFont, Color.PINK));
        moneyCount.setPosition(game.WIDTH-moneyBag.getWidth()-10, game.HEIGHT-moneyBag.getHeight());

        moneyBag.setPosition(game.WIDTH-moneyBag.getWidth(), game.HEIGHT-moneyBag.getHeight());


        stage.addActor(moneyBag);
        stage.addActor(scoreCount);
        stage.addActor(moneyCount);







        //creating clouds
        clouds = new ArrayList<Cloud>();
        for (int i=1; i<=CLOUDS_COUNT; i++) {
            Cloud cll = new Cloud(i*(SPACE_BETWEEN_CLOUDS+Cloud.CLOUD_HEIGHT),lastCloud);
            clouds.add(cll);
            lastCloud = cll;
            if (Cloud.ran()) {
                clouds.add(new Cloud(i*(SPACE_BETWEEN_CLOUDS+Cloud.CLOUD_HEIGHT)+30,null));
                clouds.get(clouds.size()-1).setBad();
            }
        }

        lowestCloud=clouds.get(0);
        lama=new Lame(clouds.get(0).position.x+10, clouds.get(0).position.y+35, game);
        lamePrev = lama.position.y;




        camera.position.y=lama.position.y+80;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);

        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        sb.draw(background, camera.position.x-camera.viewportWidth/2, camera.position.y-camera.viewportHeight/2, camera.viewportWidth,camera.viewportHeight);
        sb.draw(grass, 0,0,grass.getWidth()/2,grass.getHeight()/2);
        //drawing clouds with coins/bonuses on them
        for (Cloud c:clouds) {
            if (c.visited) c.toDraw = false;
            if (lama.isOnCloud && lama.currentCloud==c) c.toDraw=true;
            c.move();
            c.resize();
            if (c.toDraw || gameMode != 3) {
                sb.draw(c.cloud, c.position.x, c.position.y, c.width, c.height);
                if (c.hasCoin) {
                    sb.draw(c.coin, c.coinPosition.x, c.coinPosition.y);
                }
                if (c.magnit)
                    sb.draw(c.mag, c.magnitPosition.x, c.magnitPosition.y);
                if (c.hasJetpack)
                    sb.draw(c.jetpack, c.jetpackPosition.x, c.jetpackPosition.y);
                if (c.hasPampers)
                    sb.draw(c.pampers, c.pampersPosition.x, c.pampersPosition.y, 20,20);
            }
        }



        if (lama.magnitism) {
            sb.draw(magnitPic, camera.position.x - camera.viewportWidth / 2 + 20, camera.position.y - camera.viewportHeight / 2 + 20);
            sb.draw(redString, camera.position.x - camera.viewportWidth / 2 + 15, camera.position.y - camera.viewportHeight / 2 + 10, 27*(timeCounter*1.0f/bonusTimer),5 );
        }

        if (lama.fly) {
            sb.draw(jetpack, camera.position.x - camera.viewportWidth / 2 + 20, camera.position.y - camera.viewportHeight / 2 + 20);
            sb.draw(blueString, camera.position.x - camera.viewportWidth / 2 + 15, camera.position.y - camera.viewportHeight / 2 + 10, 27*(timeCounter*1.0f/bonusTimer),5 );
        }

        if (lama.hasPampers) {
            sb.draw(pampers, camera.position.x - camera.viewportWidth / 2 + 20, camera.position.y - camera.viewportHeight / 2 + 20, 20 ,20);
            sb.draw(whiteS, camera.position.x - camera.viewportWidth / 2 + 15, camera.position.y - camera.viewportHeight / 2 + 10, 27*(timeCounter*1.0f/bonusTimer),5 );
        }


        if (!begins) sb.draw(lama.lame, lama.position.x, lama.position.y, lama.width/2, lama.height/2);
        else {
            sb.draw(siitingLama, lama.position.x, lama.position.y-3,lama.width/2, lama.height/2);
            if (timer==80) {
                begins=false;
            }
        }
        timer++;
        sb.end();


        stage.act();
        stage.draw();
    }



    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (gameMode!=4 && !lama.fly) {
                lama.jump();
                lama.isOnCloud = false;
            }
            score+=3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            lama.left();

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            lama.right();

        }
    }

    public void update(float dt) {
        if (!begins) handleInput();
        lama.update(dt);
        float plus=0.86f;
        if (gameMode==4) plus=0.95f;


        scoreCount = new Label(String.format("%04d", this.score), new Label.LabelStyle(game.countFont, Color.BLACK));

        scoreCount.setPosition(game.WIDTH/2-scoreCount.getWidth()/2, game.HEIGHT-8-scoreCount.getHeight());
        System.out.println(scoreCount.getHeight());

        moneyCount = new Label(String.format("%03d", this.money), new Label.LabelStyle(game.moneyFont, game.moneyFont.getColor()));
        //moneyCount.setColor(0.97f, 0.85f,0,1);
        System.out.println(moneyCount.getHeight());
        moneyCount.setPosition(game.WIDTH-moneyCount.getWidth()-45, game.HEIGHT-14.5f-moneyCount.getHeight());
        moneyBag.setBounds(game.WIDTH-47, game.HEIGHT-17-moneyCount.getHeight(), 40, 40);


        stage.clear();
        stage.addActor(moneyBag);
        stage.addActor(scoreCount);
        stage.addActor(moneyCount);




        if (timer>=120) {
            if (!lama.fly) camera.position.y += plus;//change this value to make camera move faster/slower
            else {
                camera.position.y = lama.position.y + 80;
            }
        }

        for (int d=0; d<clouds.size(); d++)//repos the clouds when they are out of camera viewport
        {
            Cloud cl = clouds.get(d);
            if (gameMode==2) cl.moveable=true;
            if (gameMode==5) {
                cl.resizable=true;
                if (d%2==0) cl.sizeVel=0.15f;
                if (d%3==0) cl.sizeVel=0.07f;
                if (d%5==0) cl.sizeVel=0.2f;
            }


            if (lama.magnitism || lama.fly || lama.hasPampers) { cl.magnit=false; cl.hasJetpack=false; cl.hasPampers=false;}
            if ((camera.position.y - (camera.viewportHeight/2)) > (cl.position.y+Cloud.CLOUD_HEIGHT+200)) {
                cl.reposition(cl.position.y + ((Cloud.CLOUD_HEIGHT+SPACE_BETWEEN_CLOUDS)*CLOUDS_COUNT),lastCloud, lama.magnitism);
                lastCloud=cl;
                lowestCloud=clouds.get((d+1)%10);
                score+=5;
                if ((!cl.canBeMagnit) && (cl.magnit)) cl.magnit=false;
            }
        }
        //checking all the clouds to understand on which lama is staying now
        for (Cloud c:clouds) {
            if (c.toDraw || gameMode!=3) {
                if ((lama.position.y<=c.position.y+c.height+3) && (lama.position.y>=c.position.y+c.height-4) && (lama.position.x+lama.width/4>=c.position.x) && (lama.position.x<=c.position.x+c.width-lama.width/6)) {
                    if (c.bad && !lama.fly) gameOver();
                    lama.velocity.set(new Vector3(0, 0, 0));
                    if (!lama.fly) lama.position.y = c.position.y + c.height - 2;
                    if (gameMode == 4 && !lama.fly && !begins) lama.jump();

                    score = lama.onCloud(score, c.visited); //if this cloud wasn't visited earlier - score++
                    c.visited = true;
                    lama.currentCloud = c;


                    if ((lama.isOnCloud) && (c.moveable))
                        lama.position.x += c.velocity;        // if cloud is moveable - lama moves with it

                    //picking a coin
                    if (c.hasCoin) {
                        if ((lama.position.x + lama.lame.getWidth() >= c.coinPosition.x) && (lama.position.x <= c.coinPosition.x + c.coin.getWidth()))
                            c.hasCoin = false;
                       if(game.musicOn) coinSound.play();
                        money++;
                        score += 7;
                    }

                    //picking a magnit
                    if (c.magnit) {
                        if ((lama.position.x + lama.lame.getWidth() >= c.magnitPosition.x) && (lama.position.x <= c.magnitPosition.x + c.mag.getWidth())) {
                            c.magnit = false;
                            lama.magnitism = true;
                           if(game.musicOn) coinSound.play(); //new sound here for a bonus
                        }

                    }
                    if (c.hasJetpack) {
                        if ((lama.position.x + lama.lame.getWidth() >= c.jetpackPosition.x) && (lama.position.x <= c.jetpackPosition.x + c.mag.getWidth())) {
                            c.hasJetpack = false;
                            lama.fly = true;
                           if(game.musicOn) coinSound.play(); //new sound here for a bonus
                        }
                    }
                    if (c.hasPampers) {
                        if ((lama.position.x + lama.lame.getWidth() >= c.pampersPosition.x) && (lama.position.x <= c.pampersPosition.x + 20)) {
                            c.hasPampers = false;
                            lama.hasPampers = true;
                           if(game.musicOn) coinSound.play(); //new sound here for a bonus
                        }
                    }
                }
            }
        }
        magniting();
        jetpack();
        pampers();

        //end of game
        if (lama.position.y+45<camera.position.y-camera.viewportHeight/2) {
            gameOver();
        }
        camera.update();
    }



    //magniting all the coins
    public void magniting() {
        if (lama.magnitism) {
            timeCounter++;
            if (timeCounter==bonusTimer) {
                lama.magnitism=false;
                timeCounter=0;
                drawMagnet=true;
                for (Cloud cloud: clouds)
                    cloud.canBeMagnit=true;
            }
            else {
                for (Cloud cloud: clouds) {
                    cloud.canBeMagnit=false;
                    if ((cloud.hasCoin)&&(camera.position.y+camera.viewportHeight/2>=cloud.position.y+30)) {
                        Vector2 vector = new Vector2((lama.position.x-cloud.coinPosition.x),(lama.position.y-cloud.coinPosition.y));
                        if (vector.len()<=200) {
                            vector.scl(5/vector.len());
                            cloud.coinPosition.x+=vector.x;
                            cloud.coinPosition.y+=vector.y;
                            if ((lama.position.x+lama.lame.getWidth()>=cloud.coinPosition.x) && (lama.position.x<=cloud.coinPosition.x+cloud.coin.getWidth()) && (cloud.coinPosition.y<=lama.position.y+5) && (cloud.coinPosition.y>=lama.position.y)) {
                                cloud.hasCoin = false;
                               if(game.musicOn) coinSound.play();
                                money++;
                                score += 7;
                            }
                        }
                    }
                }
            }

        }
    }




    public void jetpack() {
        if (lama.fly) {
            timeCounter++;
            if (timeCounter == bonusTimer) {
                lama.fly = false;
                timeCounter = 0;
                lama.velocity.y=0;
            }
            lama.velocity.y=400;
        }
    }

    public void pampers() {
        if (lama.hasPampers) {
            timeCounter++;
            if (timeCounter == bonusTimer) {
                lama.hasPampers = false;
                timeCounter = 0;
                lama.width = 57;
                lama.height=80;
            } else {
                lama.height = 40;
                lama.width = 30;
            }
        }
    }

    public void gameOver() {
        game.setScreen(new AuthorizationScreen(game));
        StartClass.music.stop();
        if(game.musicOn) endSound.play();
        this.dispose();
        game.setScore(this.score);
        game.setGameOverScreen();

    }

    @Override
    public void show() {

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


}
