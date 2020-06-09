package com.mygdx.game;

/*
"Jumping Lama" Game

Developers: Vareshchuk Mariia, Boiarchuk Yelyzaveta

file: lamaGame.java
 */

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.screens.*;

import java.io.IOException;

public class StartClass extends Game implements ApplicationListener {

	/**
	 * parameters
	 */
	public final static int WIDTH = 500;
	public final static int HEIGHT = 700;
	public FreeTypeFontGenerator fontGenerator;
	public FreeTypeFontGenerator.FreeTypeFontParameter welcomeFontParameter;
	public BitmapFont welcomeFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter whiteFontParameter;
	public BitmapFont whiteFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter buttonsFontParameter;
	public BitmapFont buttonsFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter countFontParameter;
	public BitmapFont countFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter priceFontParameter;
	public BitmapFont priceFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter bonusFontParameter;
	public BitmapFont bonusFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter moneyFontParameter;
	public BitmapFont moneyFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter messageFontParameter;
	public BitmapFont messageFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter pauseFontParameter;
	public BitmapFont pauseFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter sureFontParameter;
	public BitmapFont sureFont;
	public FreeTypeFontGenerator.FreeTypeFontParameter linkFontParameter;
	public BitmapFont linkFont;
	public Sound clickSound;
	final static String title = "Jumping Lama";
	public boolean clicksoundbool = false;
	public SpriteBatch batch;
	public static Music music;
	public int gameMode;
	public long score;
	public UserBase userBase;
	public User user;
	Info inf = new Info();
	Texture img;
	public boolean newUserBool;
	public boolean firstEntrance;
	public boolean newHighScore;
	public String userWelcomeString;
	public String highScoreString;

	private AuthorizationScreen authorizationScreen;
	private RegistrationScreen registrationScreen;
	private MenuScreen menuScreen;
	private ChoiceScreen choiceScreen;
	private GameScreen gameScreen;
	private GameOverScreen gameOverScreen;
	private ShopScreen shopScreen;
	private InfoScreen infoScreen;
	public boolean musicOn;
	public boolean pausedScreenOn;
	public boolean disposeGameScreen;

	/**
	 * create main stage
	 */
	@Override
	public void create () {

		userBase = new UserBase();
		try {
			userBase.renew();
		} catch (IOException e) {
			e.printStackTrace();
		}

		batch = new SpriteBatch();
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Modulus-Bold.otf"));
		user = new User();
		pausedScreenOn = false;
		disposeGameScreen = false;

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

		countFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		countFontParameter.size = 30;
		countFontParameter.color = Color.BLACK;
		countFont = fontGenerator.generateFont(countFontParameter);

		priceFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		priceFontParameter.size = 40;
		priceFontParameter.color = Color.YELLOW;
		priceFont = fontGenerator.generateFont(priceFontParameter);

		pauseFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		pauseFontParameter.size = 35;
		pauseFontParameter.color = Color.WHITE;
		pauseFont = fontGenerator.generateFont(pauseFontParameter);

		bonusFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		bonusFontParameter.size = 10;
		bonusFontParameter.color = Color.BLACK;
		bonusFont = fontGenerator.generateFont(bonusFontParameter);

		moneyFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		moneyFontParameter.size = 20;
		moneyFontParameter.color = Color.YELLOW;
		moneyFont = fontGenerator.generateFont(moneyFontParameter);

		messageFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		messageFontParameter.size = 20;
		messageFontParameter.color = Color.BLACK;
		messageFont = fontGenerator.generateFont(messageFontParameter);

		sureFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		sureFontParameter.size = 25;
		sureFontParameter.color = Color.BLACK;
		sureFont = fontGenerator.generateFont(sureFontParameter);

		linkFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		linkFontParameter.size = 20;
		linkFontParameter.color = Color.BLUE;
		linkFont = fontGenerator.generateFont(linkFontParameter);

		musicOn = true;
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		clickSound = Gdx.audio.newSound(Gdx.files.internal("clickSound.wav"));
		setAuthorizationScreen();

	}

	/**
	 * set authorization screen
	 */
	public void setAuthorizationScreen() {
		authorizationScreen = new AuthorizationScreen(this);
		setScreen(authorizationScreen);
	}

	/**
	 * set registration sceen
	 */
	public void setRegistrationScreen() {
		registrationScreen= new RegistrationScreen(this);
		setScreen(registrationScreen);
	}

	/**
	 * set Menu screen
	 * @param newUserBool
	 * @param firstEntrance
	 */
	public void setMenuScreen(boolean newUserBool, boolean firstEntrance) {
		this.newUserBool = newUserBool;
		this.firstEntrance = firstEntrance;
		if(firstEntrance) {
			if (newUserBool) this.userWelcomeString = "Glad you joined,\n" + user.getName();
			else this.userWelcomeString = "Happy to see you\nagain, " + user.getName();
		}
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	/**
	 * set choice screen
	 */
	public void setChoiceScreen() {
		choiceScreen = new ChoiceScreen(this);
		setScreen(choiceScreen);
	}

	/**
	 * set game screen
	 */
	public void setGameScreen() {
		gameScreen = new GameScreen(this, getGameMode());
		setScreen(gameScreen);
	}

	/**
	 * set Game screen
	 * @param newHighScore
	 */
	public void setGameOverScreen(boolean newHighScore) {
		this.newHighScore = newHighScore;
		if(newHighScore) highScoreString = "New high score :)";
		else highScoreString = "Your score";
		gameOverScreen = new GameOverScreen(this, score, newHighScore);
		setScreen(gameOverScreen);
	}

	/**
	 * set shop screen
	 */
	public void setShopScreen(){
		shopScreen = new ShopScreen(this);
		setScreen(shopScreen);
	}

	/**
	 * set info screen
	 */
	public void setInfoScreen(){
		infoScreen = new InfoScreen(this);
		setScreen(infoScreen);
	}

	/**
	 * set user
	 * @param user
	 */
	public void setUser (User user){
		this.user = user;
	}

	/**
	 * render screen
	 */
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		if(musicOn){
			music.play();
			if(clicksoundbool) {
				clickSound.play(0f);
				clicksoundbool = false;
			}
		} else{
			music.dispose();
		}
	}

	/**
	 * set Game mode
	 * @param gameMode
	 */
	public void setGameMode(int gameMode){
		this.gameMode = gameMode;
	}

	/**
	 * set score
	 * @param score
	 */
	public void setScore(long score){
		this.score = score;
	}

	/**
	 * get game mode
	 * @return
	 */
	public int getGameMode(){
		return gameMode;
	}

	/**
	 * dispose
	 */
	@Override
	public void dispose() {
		super.dispose();
	}

	/**
	 * resize screen
	 * @param width
	 * @param height
	 */
	@Override
	public void resize(int width, int height) {

		super.resize(width, height);
	}

	/**
	 * pause
	 */
	@Override
	public void pause() {
		super.pause();
	}

	/**
	 * resume
	 */
	@Override
	public void resume() {
		super.resume();
	}

}
