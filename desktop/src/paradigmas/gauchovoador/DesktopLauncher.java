package paradigmas.gauchovoador;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import paradigmas.gauchovoador.Main;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static final int windowWidth = 1280;
	public static final int windowHeight = 720;

	public static void main (String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Gaúcho Voador");
		config.setWindowedMode(windowWidth, windowHeight);
		config.setForegroundFPS(60);
		config.useVsync(true);
		new Lwjgl3Application(new Main(), config);
	}
}
