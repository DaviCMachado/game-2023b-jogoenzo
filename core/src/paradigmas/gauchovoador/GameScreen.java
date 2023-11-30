package paradigmas.gauchovoador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    private final Main game;
    private final OrthographicCamera camera;
    static public Viewport viewport;
    static public Vector3 worldCoordinates;
    private final Ball ball;
    static public final float WORLD_WIDTH = 1280;
    static public final float WORLD_HEIGHT = 720;

    public GameScreen(final Main game) {
        this.game = game;

        Quiz quiz = new Quiz();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        worldCoordinates = new Vector3();

        ball = new Ball(
                new Circle(
                        WORLD_WIDTH * 15 / 100f,
                        WORLD_HEIGHT * 50 / 100f,
                        WORLD_HEIGHT * 6 / 100f),
                Color.RED
        );
    }

    @Override
    public void render(float delta) {
        camera.update();
        worldCoordinates = camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0),
                viewport.getScreenX(),
                viewport.getScreenY(),
                viewport.getScreenWidth(),
                viewport.getScreenHeight()
        );

        ScreenUtils.clear(Color.ROYAL);

        ball.update();
        ball.render();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
