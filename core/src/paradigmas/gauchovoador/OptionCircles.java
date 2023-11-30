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
    private final int speed;
    private final float circleRadius = Main.WORLD_HEIGHT * 6 / 100f;
    private final ShapeRenderer renderer;

    public OptionCircles(Question question, int speed) {
        renderer = new ShapeRenderer();
        options = question.getOptions();
        answer = question.getAnswer();
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
                    Main.WORLD_WIDTH + circleRadius + rand.nextFloat(Main.WORLD_WIDTH / 2),
                    rand.nextFloat(circleRadius, Main.WORLD_HEIGHT - circleRadius),
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
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Circle c : circles) {
            renderer.circle(c.x, c.y, c.radius);
        }
        renderer.end();
    }
}
