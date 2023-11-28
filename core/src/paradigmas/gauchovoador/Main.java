package paradigmas.gauchovoador;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;


public class Main extends ApplicationAdapter {
    private OrthographicCamera camera;
    static public Vector3 worldCoordinates;
    private Ball ball;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        worldCoordinates = new Vector3();

        ball = new Ball(
                Gdx.graphics.getWidth() * 15 / 100,
                Gdx.graphics.getHeight() * 50 / 100,
                Gdx.graphics.getHeight() * 6 / 100
        );
    }

    @Override
    public void render() {
        worldCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ball.update();
        ball.render();
    }
}
