package com.mygdx.game.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Lama;
import com.mygdx.game.sprites.Cloud;
import com.mygdx.game.sprites.Lame;
import com.mygdx.game.utilities.ScoreBar;
import java.util.ArrayList;


//ALL MOVABLE

public class PlayStateMode3 extends State{

    Lame lama; //main character
    Texture background; // background
    Texture coinPic;
    Texture magnitPic;
    Texture redString;




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

    ScoreBar scoreBar;
    ScoreBar moneyBar;

    float lamePrev;


    //sounds
    public Sound coinSound;
    public Sound endSound;



    PlayStateMode3(GameStateManager gsm) {
        super(gsm);

        score=0;
        money=0;

        scoreBar = new ScoreBar();
        moneyBar = new ScoreBar();
        coinSound = Gdx.audio.newSound(Gdx.files.internal("coin_sound.wav"));
        endSound = Gdx.audio.newSound(Gdx.files.internal("end.wav"));
        coinPic = new Texture("coin.png");
        magnitPic = new Texture("magnit.png");
        redString = new Texture("redS.png");

        camera.setToOrtho(false, Lama.WIDTH/2, Lama.HEIGHT/2);

        background = new Texture("yellowBackGround.jpg");


        //creating clouds
        clouds = new ArrayList<Cloud>();
        for (int i=1; i<=CLOUDS_COUNT; i++) {
            Cloud cll = new Cloud(i*(SPACE_BETWEEN_CLOUDS+Cloud.CLOUD_HEIGHT),lastCloud);
            clouds.add(cll);
            lastCloud = cll;
            if (Cloud.ran()) {
                clouds.add(new Cloud(i*(SPACE_BETWEEN_CLOUDS+Cloud.CLOUD_HEIGHT)+30,null));

            }
        }
        lama=new Lame(clouds.get(2).position.x+10, clouds.get(2).position.y+17);
        lamePrev = lama.position.y;

        lowestCloud=clouds.get(0);



        camera.position.y=lama.position.y+80;
    }



    @Override
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            lama.jump();
            lama.isOnCloud=false;
            score+=3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            lama.left();

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            lama.right();

        }
    }



    @Override
    public void update(float dt) {
        handleInput();
        lama.update(dt);
        camera.position.y+=0.95; //change this value to make camera move faster/slower

        for (int d=0; d<clouds.size(); d++)//repos the clouds when they are out of camera viewport
        {
            Cloud cl = clouds.get(d);

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
            if (c.toDraw) {
                if ((lama.position.y <= c.position.y + 21) && (lama.position.y >= c.position.y + 15) && (lama.position.x + 15 >= c.position.x) && (lama.position.x <= c.position.x + Cloud.CLOUD_WIDTH - 10)) {
                    lama.velocity.set(new Vector3(0, 0, 0));
                    lama.position.y = c.position.y + 17;

                    score = lama.onCloud(score, c.visited);//if this cloud wasn't visited earlier - score++
                    lama.currentCloud = c;
                    c.visited = true;


                    if ((lama.isOnCloud) && (c.moveable))
                        lama.position.x += c.velocity;        // if cloud is moveable - lama moves with it

                    //picking a coin
                    if (c.hasCoin) {
                        if ((lama.position.x + lama.lame.getWidth() >= c.coinPosition.x) && (lama.position.x <= c.coinPosition.x + c.coin.getWidth()))
                            c.hasCoin = false;
                        coinSound.play();
                        money++;
                        score += 7;
                    }

                    //picking a magnit
                    if (c.magnit) {
                        if ((lama.position.x + lama.lame.getWidth() >= c.magnitPosition.x) && (lama.position.x <= c.magnitPosition.x + c.mag.getWidth())) {
                            c.magnit = false;
                            lama.magnitism = true;
                            coinSound.play(); //new sound here for a bonus
                        }
                    }
                }
            }
        }
        magniting();

        //end of game
        if (lama.position.y+45<camera.position.y-camera.viewportHeight/2) {
            gsm.set(new EndState(gsm));
            Lama.music.stop();
            endSound.play();
        }

        camera.update();
    }



    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        sb.draw(background, camera.position.x-camera.viewportWidth/2, camera.position.y-camera.viewportHeight/2, camera.viewportWidth,camera.viewportHeight);

        //drawing clouds with coins/bonuses on them
        for (Cloud c:clouds) {
            c.move();
            if (c.visited) c.toDraw=false;
            if (lama.isOnCloud && lama.currentCloud==c) c.toDraw=true;

            if (c.toDraw){
                sb.draw(c.cloud, c.position.x, c.position.y);
                if (c.hasCoin) {
                    sb.draw(c.coin, c.coinPosition.x, c.coinPosition.y);
                }
                if (c.magnit)
                    sb.draw(c.mag, c.magnitPosition.x, c.magnitPosition.y);
            }

        }

        //drawing scorebar
        for (int i=0;i<5;i++) {
            sb.draw(scoreBar.show(score).get(i), camera.position.x+ 50+i*9,camera.position.y+camera.viewportHeight/2-13, 8,11);
        }

        //drawing moneybar
        for (int i=2;i<5;i++) {
            sb.draw(moneyBar.show(money).get(i), camera.position.x-120+(i-2)*9, camera.position.y+camera.viewportHeight/2-13,8,11);
        }
        sb.draw(coinPic,camera.position.x-80-coinPic.getWidth(), camera.position.y+camera.viewportHeight/2-coinPic.getHeight());

        if (lama.magnitism) {
            sb.draw(magnitPic, camera.position.x - camera.viewportWidth / 2 + 20, camera.position.y - camera.viewportHeight / 2 + 20);
            sb.draw(redString, camera.position.x - camera.viewportWidth / 2 + 15, camera.position.y - camera.viewportHeight / 2 + 10, 27*(timeCounter*1.0f/bonusTimer),15 );


        }



        sb.draw(lama.lame, lama.position.x, lama.position.y, lama.lame.getWidth()/2, lama.lame.getHeight()/2);
        sb.end();
    }



    @Override
    public void dispose() {
        background.dispose();
        for (Cloud c: clouds) {
            // c.dispose(); ??????????
        }
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
                                coinSound.play();
                                money++;
                                score += 7;
                            }
                        }
                    }
                }
            }

        }
    }



}
