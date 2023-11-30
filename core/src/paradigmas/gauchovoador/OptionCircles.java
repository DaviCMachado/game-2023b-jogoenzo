package paradigmas.gauchovoador;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class OptionCircles {
    public enum IntersectionWithCircles {
        NO,
        YES_CORRECT,
        YES_WRONG,
    }

    private final Array<String> options;
    private final int answer;
    private final Array<Circle> circles;
    private final float speed;
    private final float circleRadius = GameScreen.WORLD_HEIGHT * 8 / 100f;
    private final ShapeRenderer renderer;
    public boolean active;

    public OptionCircles(Question question, float speed) {
        renderer = new ShapeRenderer();
        options = question.getOptions();
        answer = question.getAnswer();
        active = true;
        this.speed = speed;
        circles = new Array<>();
        randomizeCircles();
    }

    private void randomizeCircles() {
        assert options.size == 4 : "Question does not have 4 options.";
        Random rand = new Random();
        for (int i = 0; i < options.size; i++) {
            circles.add(new Circle(
                    // TODO: dividir em seções
                    GameScreen.WORLD_WIDTH + circleRadius + rand.nextFloat(GameScreen.WORLD_WIDTH / 2),
                    rand.nextFloat(circleRadius, GameScreen.WORLD_HEIGHT - circleRadius),
                    circleRadius
            ));
        }
    }

    public boolean allOutOfBounds() {
        boolean r = true;
        for (Circle c : circles) {
            if (c.x + c.radius > 0) {
                r = false;
                break;
            }
        }
        return r;
    }

    public IntersectionWithCircles hitsAnyCircle(Rectangle rect) {
        if (!active) return IntersectionWithCircles.NO;

        for (int i = 0; i < circles.size; i++) {
            if (Intersector.overlaps(circles.get(i), rect)) {
                if (i == answer) {
                    return IntersectionWithCircles.YES_CORRECT;
                }
                return IntersectionWithCircles.YES_WRONG;
            }
        }
        return IntersectionWithCircles.NO;
    }

    public void update() {
        for (Circle c : circles) {
            c.x -= speed;
        }
    }

    public void render() {
        ShapeRenderer.ShapeType type = (active) ? ShapeRenderer.ShapeType.Filled : ShapeRenderer.ShapeType.Line;

        renderer.begin(type);
        for (Circle c : circles) {
            renderer.circle(c.x, c.y, c.radius);
        }
        renderer.end();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
