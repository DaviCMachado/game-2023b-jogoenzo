package paradigmas.gauchovoador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    private final Main game;
    private final OrthographicCamera camera;
    static public Viewport viewport;
    static public Vector3 worldCoordinates;
    private final Bagualo bagualo;
    static public final float WORLD_WIDTH = 1280;
    static public final float WORLD_HEIGHT = 720;
    private final Quiz quiz;
    private OptionCircles optionCircles;
    private final Texture backgroundTexture;

    public GameScreen(final Main game) {
        this.game = game;

        quiz = new Quiz();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        worldCoordinates = new Vector3();

        bagualo = new Bagualo();

        backgroundTexture = new Texture("poa-bg.png");
    }

    @Override
    public void render(float delta) {
        updateCoordinates();

        ScreenUtils.clear(Color.ROYAL);
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.batch.draw(backgroundTexture, 0, 0);

        bagualo.update();
        bagualo.render(game.batch);

        game.batch.end();

        advance();
    }

    private void updateCoordinates() {
        camera.update();
        worldCoordinates = camera.unproject(
                new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0),
                viewport.getScreenX(),
                viewport.getScreenY(),
                viewport.getScreenWidth(),
                viewport.getScreenHeight()
        );
    }

    private void advance() {
        if ((optionCircles == null || optionCircles.allOutOfBounds()) && quiz.hasNext()) {
            optionCircles = new OptionCircles(quiz.next(), 3);
        }

        optionCircles.update();
        optionCircles.render();
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
