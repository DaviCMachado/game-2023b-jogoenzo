package paradigmas.gauchovoador;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Interpolation;

public class Ball {
    private final Circle circle;
    private final Color color;
    private final ShapeRenderer renderer;

    public Ball(Circle circle, Color color) {
        renderer = new ShapeRenderer();
        this.circle = circle;
        this.color = color;
    }

    public void render() {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(color);
        renderer.circle(circle.x, circle.y, circle.radius);
        renderer.end();
    }

    public void update() {
        float mouseY = Main.worldCoordinates.y;
        circle.y = Interpolation.linear.apply(circle.y, mouseY, 0.05f);
        circle.y = Math.max(Math.min(circle.y, Main.WORLD_HEIGHT - circle.radius), circle.radius);
    }
}
