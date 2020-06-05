package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.screens.*;

public class StartClass extends Game implements ApplicationListener {

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
	public Sound clickSound;
	final static String title = "Jumping Lama";
	public boolean clicksoundbool = false;
	public SpriteBatch batch;
	public static Music music;
	public int gameMode;
	public long score;
	UserBase userBase;
	Info inf = new Info();
	Texture img;

	private AuthorizationScreen authorizationScreen;
	private RegistrationScreen registrationScreen;
	private MenuScreen menuScreen;
	private ChoiceScreen choiceScreen;
	private GameScreen gameScreen;
	private GameOverScreen gameOverScreen;
	private ShopScreen shopScreen;
	private InfoScreen infoScreen;
	public boolean musicOn;

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
		countFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		countFontParameter.size = 30;
		countFontParameter.color = Color.BLACK;
		countFont = fontGenerator.generateFont(countFontParameter);

		priceFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		priceFontParameter.size = 40;
		priceFontParameter.color = Color.YELLOW;
		priceFont = fontGenerator.generateFont(priceFontParameter);

		bonusFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		bonusFontParameter.size = 10;
		bonusFontParameter.color = Color.BLACK;
		bonusFont = fontGenerator.generateFont(bonusFontParameter);

		moneyFontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		moneyFontParameter.size = 20;
		moneyFontParameter.color = Color.YELLOW;
		moneyFont = fontGenerator.generateFont(moneyFontParameter);
		musicOn = true;
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		clickSound = Gdx.audio.newSound(Gdx.files.internal("clickSound.wav"));
		setAuthorizationScreen();
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
	public void setShopScreen(){
		shopScreen = new ShopScreen(this);
		setScreen(shopScreen);
	}
	public void setInfoScreen(){
		infoScreen = new InfoScreen(this);
		setScreen(infoScreen);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
		if(musicOn){
			music.play();
			if(clicksoundbool) {
				clickSound.play(1f);
				clicksoundbool = false;
			}
		} else{
			music.dispose();
		}
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
