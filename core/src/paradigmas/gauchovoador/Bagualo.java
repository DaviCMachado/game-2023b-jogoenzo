package paradigmas.gauchovoador;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Rectangle;

public class Bagualo {
    private final Sprite sprite;
    private int lives;
    private int score;

    public Bagualo(int lives) {
        this.lives = lives;
        score = 0;

        sprite = new Sprite(new Texture("bagualo.png"));
        sprite.setSize(sprite.getWidth() * 0.7f, sprite.getHeight() * 0.7f);
        sprite.setOrigin(0, 0);
        sprite.setCenter(GameScreen.WORLD_WIDTH * 12f / 100, GameScreen.WORLD_HEIGHT * 50f / 100);
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void update() {
        float mouseY = GameScreen.worldCoordinates.y;
        sprite.setY(Interpolation.linear.apply(sprite.getY(), mouseY, 0.05f));
        sprite.setY(
                Math.max(
                        Math.min(
                                sprite.getY(),
                                GameScreen.WORLD_HEIGHT - sprite.getHeight() * 0.9f),
                        0
                )
        );
    }

    public Rectangle getBoundingRectangle() {
        return sprite.getBoundingRectangle();
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int increase) {
        score += increase;
    }

    public boolean decreaseLive() {
        return --lives <= 0;
    }
}
