package paradigmas.gauchovoador;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;

public class Bagualo {
    private final Sprite sprite;

    public Bagualo() {
        sprite = new Sprite(new Texture("bagualo.png"));
        sprite.setSize(sprite.getWidth() * 0.2f, sprite.getHeight() * 0.2f);
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
}
