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

    private enum Endgame {
        LOSE,
        WIN,
    }

    public GameScreen(final Main game) {
        this.game = game;

        quiz = new Quiz();

        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

        worldCoordinates = new Vector3();

        bagualo = new Bagualo(3);

        backgroundTexture = new Texture("quarta-bg.png");
    }

    @Override
    public void render(float delta) {
        updateCoordinates();
        advanceLogic();

        ScreenUtils.clear(Color.BLACK);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0);
        game.batch.end();

        optionCircles.renderCircles();

        game.batch.begin();
        bagualo.render(game.batch, game.font);
        optionCircles.renderText(game.batch, game.font);
        game.batch.end();
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

    private void advanceLogic() {
        if (!quiz.hasNext()) {
            if (bagualo.getLives() > 0) {
                endgame(Endgame.WIN);
            } else {
                endgame(Endgame.LOSE);
            }
        }

        if ((optionCircles == null || optionCircles.allOutOfBounds())) {
            optionCircles = new OptionCircles(quiz.next(), 3.5f);
        }

        optionCircles.update();
        bagualo.update();

        OptionCircles.IntersectionWithCircles intersectionResult = optionCircles.hitsAnyCircle(bagualo.getBoundingRectangle());
        switch (intersectionResult) {
            case NO:
                break;
            case YES_WRONG:
                optionCircles.setActive(false);
                if (bagualo.decreaseLive()) {
                    endgame(Endgame.LOSE);
                }
                break;
            case YES_CORRECT:
                optionCircles.setActive(false);
                bagualo.increaseScore(5);
                break;
        }
    }

    void endgame(Endgame result) {
        throw new UnsupportedOperationException();
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
