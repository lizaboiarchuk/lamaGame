package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.*;
import com.mygdx.game.states.AuthorizationState;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MainState;

public class StartClass extends Game implements ApplicationListener {

	public final static int WIDTH = 500;
	public final static int HEIGHT = 700;
	final static String title = "Jumping Lama";
	public GameStateManager gsm;
	public SpriteBatch batch;
	public static Music music;
	UserBase userBase;
	Texture img;
	Info inf = new Info();

	private AuthorizationScreen authorizationScreen;
	private RegistrationScreen registrationScreen;
	private MenuScreen menuScreen;
	private ChoiceScreen choiceScreen;
	private GameScreen gameScreen;
	private GameOverScreen gameOverScreen;

	@Override
	public void create () {
		userBase = new UserBase();
		batch = new SpriteBatch();
		setAuthorizationScreen();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		//	gsm = new GameStateManager(userBase);
	//	gsm.push(new AuthorizationState(gsm));
	//	ScreenManager.getInstance().initialize(this);
	//	ScreenManager.getInstance().showScreen( ScreenEnum.AUTHORIZATION_SCREEN );
	//	Gdx.gl.glClearColor(1,1,1,1);
	//	gsm.push(new MainState(gsm, this));
	}

	public void setAuthorizationScreen() {
		authorizationScreen = new AuthorizationScreen(this);
		setScreen(authorizationScreen);
	}
	public void setRegistrationScreen() {
		registrationScreen= new RegistrationScreen(this);
		setScreen(registrationScreen);
	}
	public void setMenuScreen() {
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}
	public void setChoiceScreen() {
		choiceScreen = new ChoiceScreen(this);
		setScreen(choiceScreen);
	}
	public void setGameScreen() {
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}
	public void setGameOverScreen() {
		gameOverScreen = new GameOverScreen(this);
		setScreen(gameOverScreen);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {

		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

}
