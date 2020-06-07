package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.StartClass;
import com.mygdx.game.sprites.Cloud;
import com.mygdx.game.sprites.Lame;
import com.mygdx.game.sprites.Tube;

import java.util.ArrayList;

public class GameScreen implements Screen {

    StartClass game;
    Lame lama; //main character
    SpriteBatch spriteBatch;
    OrthographicCamera camera;
    Stage stage;

    //pictures and labels
    Texture sititingLama;
    Texture grass;
    Texture whiteS;
    Texture background;
    ArrayList<Texture> backgrounds;
    Image tubePink;
    Image moneyBag;
    Image bonusesImage[];
    ArrayList<Tube> tubesGrey;
    Cloud lastCloud,lowestCloud;
    ArrayList<Cloud> clouds; //all the clouds
    Label moneyCount, scoreCount;
    Label bonusesNumberLabel[];

    //variables
    boolean bonusesOn[];
    int bonusesNumber[];
    boolean begins=true;
    int timer=0;
    public long score; // current score
    public long money; //current coins
    public int gameMode;
    float lamePrev;
    public int timeCounter;
    public boolean drawMagnet=true;
    public boolean paused=false;
    public static final int SPACE_BETWEEN_CLOUDS = 60;
    public static final int CLOUDS_COUNT = 18;
    public static final int bonusTimer = 1000; //(*delta time (0.22sec))

    //sounds
    public Sound coinSound;
    public Sound endSound;


    //constructor
    public GameScreen(StartClass game, int gameMode) {
        this.game = game;
        this.gameMode=gameMode;
        spriteBatch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, StartClass.WIDTH/2, StartClass.HEIGHT/2);

        initPictures();
        initSound();
        initClouds();

        score=0;
        money=game.user.getMoney();
        lama=new Lame(clouds.get(0).position.x+10, clouds.get(0).position.y+35, game);
        lamePrev = lama.position.y;
        camera.position.y=lama.position.y+80;
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.draw(background, camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2, camera.viewportWidth, camera.viewportHeight);
        spriteBatch.draw(grass, 0, 0, grass.getWidth() / 2, grass.getHeight() / 2);

        //clouds with bonuses/coins
        for (Cloud c : clouds) {
            if (c.visited) c.toDraw = false;
            if (lama.isOnCloud && lama.currentCloud == c) c.toDraw = true;
            c.move(); //for gameMode 2
            c.resize(); //for gameMode 5
            if (c.toDraw || gameMode != 3) {
                spriteBatch.draw(c.cloud, c.position.x, c.position.y, c.width, c.height);
                if (c.hasCoin) spriteBatch.draw(c.coin.texture, c.coin.position.x, c.coin.position.y);
                if (c.hasBonus) spriteBatch.draw(c.bonus.texture, c.bonus.position.x, c.bonus.position.y);
            }
        }

        //timer for bonuses
        if (bonusesOn[0] || bonusesOn[1]|| bonusesOn[2]|| bonusesOn[3])
            spriteBatch.draw(whiteS, camera.position.x - 20, camera.position.y - camera.viewportHeight / 2 + 30, 40 * (timeCounter * 1.0f / bonusTimer), 4);

        if (!begins) spriteBatch.draw(lama.currentSprite, lama.position.x, lama.position.y, lama.width / 2, lama.height / 2);
        else {
            spriteBatch.draw(sititingLama, lama.position.x, lama.position.y - 10, lama.width / 2, lama.height / 2);
            if (timer == 80) {
                begins = false;
            }
        }
        timer++;
        spriteBatch.end();

        stage.act();
        stage.draw();
    }



    public void handleInput() {
        if (!paused) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                if (gameMode != 4 && !lama.bonusesOn[2]) {
                    lama.jump();
                    lama.isOnCloud = false;
                }
                score += 3;
                if (bonusesOn[3]) score += 3;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                lama.left();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                lama.right();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
                if ((!bonusesOn[0]) && bonusesNumber[0]!=0) {
                    magnitOn();
                } else {
                    magnitOf();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
                if ((!bonusesOn[1]) && bonusesNumber[1]!=0) {
                    wingsOn();
                } else {
                    wingsOf();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
                if ((!bonusesOn[2])&& bonusesNumber[2]!=0) {
                    jetpackOn();
                } else {
                    jetpackOf();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
                if ((!bonusesOn[3]) && bonusesNumber[3]!=0) {
                    doubleOn();
                } else {
                    doubleOf();
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_5))
            pause();

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_6))
            resume();
    }



    public void magnitOn() {
        if (!(bonusesOn[1] || bonusesOn[2] || bonusesOn[3])) {
            tubesGrey.get(0).tube = new Image(new Texture("tube.png"));
            bonusesOn[0] = true;
            lama.bonusesOn[0] = true;
            bonusesNumber[0]--;
        }
    }
    public void magnitOf() {
        tubesGrey.get(0).tube = new Image(new Texture("tubeGrey.png"));
        bonusesOn[0]=false;
        timeCounter=bonusTimer-1;
    }



    public void wingsOn() {
        if (!(bonusesOn[0] || bonusesOn[2] || bonusesOn[3])) {
            tubesGrey.get(1).tube = new Image(new Texture("tube.png"));
            bonusesOn[1] = true;
            lama.bonusesOn[1] = true;
            bonusesNumber[1]--;
        }
    }
    public void wingsOf() {
        tubesGrey.get(1).tube = new Image(new Texture("tubeGrey.png"));
        bonusesOn[1]=false;
        timeCounter=bonusTimer-1;

    }

    public void jetpackOn() {
        if (!(bonusesOn[1] || bonusesOn[0] || bonusesOn[3])) {
            tubesGrey.get(2).tube = new Image(new Texture("tube.png"));
            bonusesOn[2] = true;
            lama.bonusesOn[2] = true;
            bonusesNumber[2]--;
        }
    }
    public void jetpackOf () {
        tubesGrey.get(2).tube = new Image(new Texture("tubeGrey.png"));
        bonusesOn[2]=false;
        timeCounter=bonusTimer-1;
    }

    public void doubleOn() {
        if (!(bonusesOn[1] || bonusesOn[2] || bonusesOn[0])) {
            tubesGrey.get(3).tube = new Image(new Texture("tube.png"));
            bonusesOn[3] = true;
            bonusesNumber[3]--;
            lama.bonusesOn[3] = true;
        }
    }
    public void doubleOf() {
        tubesGrey.get(3).tube = new Image(new Texture("tubeGrey.png"));
        bonusesOn[3]=false;
        timeCounter = bonusTimer-1;
    }

    public void update(float dt) {
        if (!begins) handleInput();
        if (!paused) {
            lama.update(dt);
            float plus = 1f;
            if (gameMode == 4) plus = 0.95f;
            drawStage();

            if (timer >= 120) {
                if (!lama.bonusesOn[2]) camera.position.y += plus;
                else {
                    camera.position.y = lama.position.y + 80;
                }
            }
            for (int d = 0; d < clouds.size(); d++)//repos the clouds due to gameMode
            {
                Cloud cl = clouds.get(d);
                if (gameMode == 2) cl.moveable = true;
                if (gameMode == 5) {
                    cl.resizable = true;
                    if (d % 2 == 0) cl.sizeVel = 0.15f;
                    if (d % 3 == 0) cl.sizeVel = 0.07f;
                    if (d % 5 == 0) cl.sizeVel = 0.2f;
                }

                if ((camera.position.y - (camera.viewportHeight / 2)) > (cl.position.y + 19 + 200)) {
                    cl.reposition(cl.position.y + ((19 + SPACE_BETWEEN_CLOUDS) * CLOUDS_COUNT), lastCloud);
                    lastCloud = cl;
                    lowestCloud = clouds.get((d + 1) % 10);
                    score += 5;
                    if (bonusesOn[3]) score += 5;
                }
            }
            //checking all the clouds to understand on which lama is staying now
            for (Cloud c : clouds) {
                if (c.toDraw || gameMode != 3) {
                    if ((lama.position.y <= c.position.y + c.height + 5) && (lama.position.y >= c.position.y + c.height - 4) && (lama.position.x + lama.width / 4 >= c.position.x) && (lama.position.x <= c.position.x + c.width - lama.width / 6)) {
                        if (c.bad && !lama.bonusesOn[1] && !lama.bonusesOn[2]) gameOver();
                        lama.velocity.set(new Vector3(0, 0, 0));
                        if (!lama.bonusesOn[2]) lama.position.y = c.position.y + c.height - 2;
                        if (gameMode == 4 && !lama.bonusesOn[2] && !begins) lama.jump();

                        score = lama.onCloud(score, c.visited); //if this cloud wasn't visited earlier - score++
                        c.visited = true;
                        lama.currentCloud = c;


                        if ((lama.isOnCloud) && (c.moveable))
                            lama.position.x += c.velocity;        // if cloud is moveable - lama moves with it

                        //picking a coin
                        if (c.hasCoin) {
                            if ((lama.position.x + lama.currentSprite.getWidth() >= c.coin.position.x) && (lama.position.x <= c.coin.position.x + c.coin.texture.getWidth()))
                                c.hasCoin = false;
                            if (game.musicOn) coinSound.play();
                            money++;
                            if (bonusesOn[3]) money++;
                            score += 7;
                            if (bonusesOn[3]) score += 7;
                        }

                        //picking a bonus
                        if (c.hasBonus) {
                            if ((lama.position.x + lama.currentSprite.getWidth() >= c.bonus.position.x) && (lama.position.x <= c.bonus.position.x + c.bonus.texture.getWidth())) {
                                if (c.bonus.bonusType==0) {
                                    magnitOn();
                                    bonusesNumber[0]++;
                                    lama.bonusesOn[0] = true;
                                }
                                if (c.bonus.bonusType==1) {
                                    wingsOn();
                                    bonusesNumber[1]++;
                                    lama.bonusesOn[1] = true;
                                }
                                if (c.bonus.bonusType==2) {
                                    jetpackOn();
                                    bonusesNumber[2]++;
                                    lama.bonusesOn[2] = true;
                                }
                                if (c.bonus.bonusType==3) {
                                    doubleOn();
                                    bonusesNumber[3]++;
                                    lama.bonusesOn[3] = true;
                                }
                                c.bonus.exists = false;
                                c.hasBonus=false;
                                if (game.musicOn) coinSound.play(); //new sound here for a bonus
                            }
                        }
                    }
                }
            }
            magniting();
            jetpack();
            wings();
            doubleSc();
            //end of game
            if (lama.position.y + 20 < camera.position.y - camera.viewportHeight / 2 && !lama.bonusesOn[1]) {
                gameOver();
            } else {
                if (lama.bonusesOn[1] && lama.position.y < camera.position.y - camera.viewportHeight / 2) {
                    lama.isOnCloud = true;
                    lama.position.y = camera.position.y - camera.viewportHeight / 2;
                    lama.GRAVITY = 0;
                    if (lama.lookingLeft) lama.currentSprite = new Sprite(new Texture("lamaWingsLeftStay.PNG"));
                    else lama.currentSprite = new Sprite(new Texture("lamaWingsRightStay.PNG"));
                }
            }
            camera.update();
            scoreCount.setPosition(game.WIDTH / 2 - scoreCount.getWidth() / 2, game.HEIGHT - 8 - scoreCount.getHeight());
            moneyCount.setPosition(game.WIDTH - moneyCount.getWidth() - 45, game.HEIGHT - 14.5f - moneyCount.getHeight());
        }
    }



    //magniting all the coins
    public void magniting() {
        if (lama.bonusesOn[0]) {
            timeCounter++;
            if (timeCounter==bonusTimer) {
                lama.bonusesOn[0]=false;
                magnitOf();
                timeCounter=0;
                drawMagnet=true;
                for (Cloud cloud: clouds)
                    cloud.canBeMagnit=true;
            }
            else {
                for (Cloud cloud: clouds) {
                    cloud.canBeMagnit=false;
                    if ((cloud.hasCoin)&&(camera.position.y+camera.viewportHeight/2>=cloud.position.y+30)) {
                        Vector2 vector = new Vector2((lama.position.x-cloud.coin.position.x),(lama.position.y-cloud.coin.position.y));
                        if (vector.len()<=200) {
                            vector.scl(5/vector.len());
                            cloud.coin.position.x+=vector.x;
                            cloud.coin.position.y+=vector.y;
                            if ((lama.position.x+lama.currentSprite.getWidth()>=cloud.coin.position.x) && (lama.position.x<=cloud.coin.position.x+cloud.coin.texture.getWidth()) && (cloud.coin.position.y<=lama.position.y+5) && (cloud.coin.position.y>=lama.position.y)) {
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
        if (lama.bonusesOn[2]) {
            timeCounter++;
            if (timeCounter == bonusTimer) {
                lama.bonusesOn[2] = false;
                jetpackOf();
                timeCounter = 0;
                lama.velocity.y=0;
            }
            lama.velocity.y=400;
        }
    }

    public void wings() {
        if (lama.bonusesOn[1]) {
            timeCounter++;
            if (timeCounter == bonusTimer) {
                lama.bonusesOn[1] = false;
                wingsOf();
                timeCounter = 0;
                lama.GRAVITY=-15;
            }
        }
    }

    public void doubleSc() {
        if (lama.bonusesOn[3]) {
            timeCounter++;
            if (timeCounter == bonusTimer) {
                lama.bonusesOn[3]=false;
                doubleOf();
                timeCounter = 0;
                lama.GRAVITY=-15;
            }
        }
    }

    public void gameOver() {
        StartClass.music.stop();
        if(game.musicOn) endSound.play();
        game.user.setMagnetPurchased(this.bonusesNumber[0]);
        game.user.setWingsPurchased(this.bonusesNumber[1]);
        game.user.setRocketPurchased(this.bonusesNumber[2]);
        game.user.setDoubleBonusPurchased(this.bonusesNumber[3]);
        this.dispose();
        game.setScore(this.score);
        game.user.updateHighScore(this.score);
        game.setGameOverScreen(game.user.newHighScoreBool);
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        System.out.println("pause");
        paused =true;

    }

    @Override
    public void resume() {
        System.out.println("resume");
        paused = false;

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        background.dispose();
        grass.dispose();
        lama.currentSprite.getTexture().dispose();
        spriteBatch.dispose();
        for (Cloud c: clouds) {
            if (c.hasBonus) c.bonus.texture.dispose();
            if (c.hasCoin) c.coin.texture.dispose();
            c.cloud.dispose();
        }
        stage.dispose();
    }



    private void initPictures() {
        bonusesImage = new Image[4];
        bonusesImage[0] = new Image(new Texture("magnit.png"));
        bonusesImage[1] = new Image( new Texture("wings.png"));
        bonusesImage[2] = new Image(new Texture("jetpack.png"));
        bonusesImage[3] = new Image(new Texture("doubleBonus.png"));
        whiteS = new Texture("whiteS.png");
        moneyBag = new Image(new Texture("moneybag.png"));
        tubesGrey = new ArrayList<Tube>();
        bonusesOn = new boolean[4];
        bonusesNumber = new int[4];
        bonusesNumberLabel = new Label[4];
        bonusesNumber[0] = game.user.getMagnetPurchased();
        bonusesNumber[1] = game.user.getWingsPurchased();
        bonusesNumber[2] = game.user.getRocketPurchased();
        bonusesNumber[3] = game.user.getDoubleBonusPurchased();
        for (int i=0;i<4;i++) {
            bonusesOn[i]=false;
            Image t = new Image(new Texture("tubeGrey.png"));
            Tube tu = new Tube();
            tu.tube=t;
            tubesGrey.add(tu);
        }

        tubePink = new Image(new Texture("tube.png"));
        backgrounds = new ArrayList<Texture>();
        backgrounds.add(new Texture("d.jpg"));
        backgrounds.add(new Texture("pinkBackGround.jpg"));
        backgrounds.add(new Texture("yellowBackGround.png"));
        backgrounds.add(new Texture("blueBackGround.jpg"));
        backgrounds.add(new Texture("greenBackGround.jpg"));
        background = backgrounds.get(gameMode-1);
        sititingLama = new Texture("sittingLama.png");
        grass = new Texture("grass.JPEG");
    }



    private void initSound() {
        coinSound = Gdx.audio.newSound(Gdx.files.internal("coin_sound.wav"));
        endSound = Gdx.audio.newSound(Gdx.files.internal("end.wav"));
    }

    private void initClouds() {
        clouds = new ArrayList<Cloud>();
        for (int i=1; i<=CLOUDS_COUNT; i++) {
            Cloud cll = new Cloud(i*(SPACE_BETWEEN_CLOUDS+19),lastCloud);
            clouds.add(cll);
            lastCloud = cll;
            if (Cloud.ran()) {
                Cloud pr = new Cloud(i*(SPACE_BETWEEN_CLOUDS+19)+30,null);
                pr.isInter=true;
                clouds.add(pr);
                if (i*(SPACE_BETWEEN_CLOUDS+19)<230) clouds.get(clouds.size()-1).bad=false;
            }
        }
        lowestCloud=clouds.get(0);
    }



    private void drawStage() {
        scoreCount = new Label(String.format("%d", this.score), new Label.LabelStyle(game.countFont, Color.BLACK));
        moneyCount = new Label(String.format("%d", this.money), new Label.LabelStyle(game.moneyFont, game.moneyFont.getColor()));
        moneyBag.setBounds(game.WIDTH - 47, game.HEIGHT - 17 - moneyCount.getHeight(), 40, 40);

        tubesGrey.get(0).tube.setBounds(game.WIDTH / 2 - 100, 0, 50, 50);
        tubesGrey.get(1).tube.setBounds(game.WIDTH / 2 - 50, 0, 50, 50);
        tubesGrey.get(2).tube.setBounds(game.WIDTH / 2, 0, 50, 50);
        tubesGrey.get(3).tube.setBounds(game.WIDTH / 2 + 50, 0, 50, 50);

        bonusesImage[0].setBounds(game.WIDTH / 2 - 90, 14, 30, 28);
        bonusesImage[1].setBounds(game.WIDTH / 2 - 50, 20, 50, 20);
        bonusesImage[2].setBounds(game.WIDTH / 2 + 5, 18, 40, 28);
        bonusesImage[3].setBounds(game.WIDTH / 2 + 60, 16, 30, 30);

        bonusesNumberLabel[0] = new Label(String.format("%d", bonusesNumber[0]), new Label.LabelStyle(game.bonusFont, Color.BLACK));
        bonusesNumberLabel[1] = new Label(String.format("%d", bonusesNumber[1]), new Label.LabelStyle(game.bonusFont, Color.BLACK));
        bonusesNumberLabel[2] = new Label(String.format("%d", bonusesNumber[2]), new Label.LabelStyle(game.bonusFont, Color.BLACK));
        bonusesNumberLabel[3] = new Label(String.format("%d", bonusesNumber[3]), new Label.LabelStyle(game.bonusFont, Color.BLACK));

        bonusesNumberLabel[0].setPosition(game.WIDTH / 2 - 78, 2);
        bonusesNumberLabel[1].setPosition(game.WIDTH / 2 - 28, 2);
        bonusesNumberLabel[2].setPosition(game.WIDTH / 2 + 21, 2);
        bonusesNumberLabel[3].setPosition(game.WIDTH / 2 + 71, 2);
        stage.clear();

        for (int i = 0; i < 4; i++) stage.addActor(tubesGrey.get(i).tube);
        stage.addActor(bonusesImage[0]);
        stage.addActor(bonusesImage[1]);
        stage.addActor(bonusesImage[2]);
        stage.addActor(bonusesImage[3]);
        stage.addActor(moneyBag);
        stage.addActor(scoreCount);
        stage.addActor(moneyCount);
        for (int i = 0; i < 4; i++) stage.addActor(bonusesNumberLabel[i]);
    }
}
