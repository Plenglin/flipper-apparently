package io.github.plenglin.flipper.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import io.github.plenglin.flipper.Flipper;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
        config.width = 1280;
        config.height = 720;
		new LwjglApplication(new Flipper(), config);
	}
}
