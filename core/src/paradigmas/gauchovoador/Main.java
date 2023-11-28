package paradigmas.gauchovoador;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends ApplicationAdapter {
	ShapeRenderer ball;
	int x;
	int y;
	int r;
	
	@Override
	public void create () {
		ball = new ShapeRenderer();
		x = Gdx.graphics.getWidth() * 15 / 100;
		y = Gdx.graphics.getHeight() * 50 / 100;
		r = Gdx.graphics.getHeight() * 6 / 100;
	}

	@Override
	public void render () {
		ball.begin(ShapeRenderer.ShapeType.Filled);
		ball.circle(x, y, r);
		ball.end();
	}
}
