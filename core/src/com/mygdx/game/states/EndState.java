package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EndState extends State {

    public long gameScore;
    public long moneyScore;



    EndState(GameStateManager gsm, long gs, long ms) {
        super(gsm);
     /*   gameScore = gs;
        moneyScore = ms;
        gsm.currentUser.money+=ms;
        System.out.println("money at all");
        if (gsm.currentUser.score<gameScore) {
            gsm.currentUser.score=gameScore;
            System.out.println("new best score");
        }*/
    }



    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void dispose() {

    }
}
