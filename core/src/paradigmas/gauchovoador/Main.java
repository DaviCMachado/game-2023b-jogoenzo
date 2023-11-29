package paradigmas.gauchovoador;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Main extends ApplicationAdapter {
    private OrthographicCamera camera;
    static public final float WORLD_WIDTH = 1280;
    static public final float WORLD_HEIGHT = 720;
    static public Viewport viewport;
    static public Vector3 worldCoordinates;
    private Ball ball;
    private Quiz quiz;

    @Override
    public void create() {
        quiz = new Quiz();
        System.out.println(quiz);

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);

        worldCoordinates = new Vector3();

        ball = new Ball(
                Gdx.graphics.getWidth() * 15 / 100,
                Gdx.graphics.getHeight() * 50 / 100,
                Gdx.graphics.getHeight() * 6 / 100,
                Color.BLACK
        );
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void render() {
        worldCoordinates = camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0),
                viewport.getScreenX(),
                viewport.getScreenY(),
                viewport.getScreenWidth(),
                viewport.getScreenHeight()
        );

        ScreenUtils.clear(Color.ROYAL);

        update();
        ball.render();
    }

    private void update() {
        ball.update();
    }
}
