package paradigmas.gauchovoador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    ShapeRenderer renderer;
    private int x;
    private int y;
    private final int r;
    private int xSpeed = 5, ySpeed = 5;

    public Ball(int x, int y, int r) {
        renderer = new ShapeRenderer();
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public void render() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.circle(x, y, r);
        renderer.end();
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;
        if (x < 0 || x > Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
        }
        if (y < 0 || y > Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
        }
    }
}
