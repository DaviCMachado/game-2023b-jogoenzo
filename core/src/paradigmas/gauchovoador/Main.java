package paradigmas.gauchovoador;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class Main extends ApplicationAdapter {
    Ball ball;

    @Override
    public void create() {
        ball = new Ball(
                Gdx.graphics.getWidth() * 15 / 100,
                Gdx.graphics.getHeight() * 50 / 100,
                Gdx.graphics.getHeight() * 6 / 100
        );
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ball.update();
        ball.render();
    }
}
