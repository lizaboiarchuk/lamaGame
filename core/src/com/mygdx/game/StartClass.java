package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.AuthorizationScreen;
import com.mygdx.game.screens.ScreenEnum;
import com.mygdx.game.screens.ScreenManager;
import com.mygdx.game.states.AuthorizationState;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MainState;

public class StartClass extends Game  {

	public final static int WIDTH = 500;
	public final static int HEIGHT = 700;
	final static String title = "Jumping Lama";
	public GameStateManager gsm;
	public SpriteBatch batch;
	public static Music music;
	UserBase userBase;
	Texture img;
	Info inf = new Info();

	@Override
	public void create () {
		userBase = new UserBase();
		batch = new SpriteBatch();
		setScreen(new AuthorizationScreen(this));
	//	gsm = new GameStateManager(userBase);
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
	//	gsm.push(new AuthorizationState(gsm));
	//	ScreenManager.getInstance().initialize(this);
	//	ScreenManager.getInstance().showScreen( ScreenEnum.AUTHORIZATION_SCREEN );
	//	Gdx.gl.glClearColor(1,1,1,1);
	//	gsm.push(new MainState(gsm, this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

}
