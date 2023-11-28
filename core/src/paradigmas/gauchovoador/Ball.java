package paradigmas.gauchovoador;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

public class Ball {
    ShapeRenderer renderer;
    private final Vector2 position;
    private final float r;

    public Ball(int x, int y, int r) {
        renderer = new ShapeRenderer();
        position = new Vector2(x, y);
        this.r = r;
    }

    public void render() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.circle(position.x, position.y, r);
        renderer.end();
    }

    public void update() {
        float mouseY = Main.worldCoordinates.y;
        position.y = Interpolation.linear.apply(position.y, mouseY, 0.05f);
        position.y = Math.max(Math.min(position.y, Main.WORLD_HEIGHT - r), r);
    }
}
