package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.StartClass;
import com.mygdx.game.StartClass;

public class DesktopLauncher {
	public static void main (String[] arg) throws ClassNotFoundException {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new StartClass(), config);
		config.height=700;
		config.width=500;
		config.title="Jumping Lama";
	}
}
