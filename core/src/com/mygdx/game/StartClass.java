package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.screens.*;
import com.mygdx.game.states.GameStateManager;

public class StartClass extends Game implements ApplicationListener {

	public final static int WIDTH = 500;
	public final static int HEIGHT = 700;
	final static String title = "Jumping Lama";
	public GameStateManager gsm;
	public int gameMode;
	public long score;
	public SpriteBatch batch;
	public static Music music;
	UserBase userBase;
	Texture img;
	Info inf = new Info();
	public FreeTypeFontGenerator fontGenerator;
	public FreeTypeFontGenerator.FreeTypeFontParameter welcomeFontParameter;
	public BitmapFont welcomeFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter whiteFontParameter;
	public BitmapFont whiteFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter buttonsFontParameter;
	public BitmapFont buttonsFont;

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
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Modulus-Bold.otf"));
		welcomeFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		welcomeFontParameter.size = 35;
		welcomeFontParameter.color = Color.PINK;
		welcomeFont = fontGenerator.generateFont(welcomeFontParameter);
		whiteFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		whiteFontParameter.size = 25;
		whiteFontParameter.color = Color.WHITE;
		whiteFont = fontGenerator.generateFont(whiteFontParameter);
		buttonsFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		buttonsFontParameter.size = 30;
		buttonsFontParameter.color = Color.WHITE;
		buttonsFont = fontGenerator.generateFont(whiteFontParameter);
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		setAuthorizationScreen();
		//	gsm = new GameStateManager(userBase);
	//	gsm.push(new AuthorizationState(gsm));
	//	ScreenManager.getInstance().initialize(this);
	//	ScreenManager.getInstance().showScreen( ScreenEnum.AUTHORIZATION_SCREEN );
	//	Gdx.gl.glClearColor(1,1,1,1);
	//	gsm.push(new MainState(gsm, this));
	}

	public void setFontParameterSize(int size){
		this.welcomeFontParameter.size = size;
	}

	public void setFontParameterColor(Color color){
		this.welcomeFontParameter.color = color;
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
		gameScreen = new GameScreen(this, getGameMode());
		setScreen(gameScreen);
	}
	public void setGameOverScreen() {
		gameOverScreen = new GameOverScreen(this, score);
		setScreen(gameOverScreen);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	public void setGameMode(int gameMode){
		this.gameMode = gameMode;
	}

	public void setScore(long score){
		this.score = score;
	}

	public int getGameMode(){
		return gameMode;
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
