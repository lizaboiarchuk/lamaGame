package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.states.*;

public class StartClass extends Game {

	public final static int WIDTH = 500;
	public final static int HEIGHT = 700;

	final static String title = "Jumping Lama";

	private GameStateManager gsm;
	private SpriteBatch batch;
	public static Music music;
	UserBase userBase;

	Texture img;
	Info inf = new Info();


	@Override
	public void create () {
		userBase = new UserBase();
		batch = new SpriteBatch();
<<<<<<< HEAD:core/src/com/mygdx/game/StartClass.java
		gsm = new GameStateManager(userBase);
		//setScreen(new Menu(this,inf));
=======
		gsm = new GameStateManager();
>>>>>>> c3d598bad8e76c43f8646c6d9ece4feed2cc237a:core/src/com/mygdx/game/Lama.java
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1,1,1,1);
		gsm.push(new MainState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
