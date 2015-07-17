package game.views;

import game.controllers.AbstractScreen;
import game.controllers.MyGame;
import game.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class AbstractUI extends AbstractScreen{
	protected Stage stage;
	protected Image playerBar;
	protected Image levelBar;
	//life 
	protected Pixmap health;
	protected Texture healthTexture;
	//exp
	protected Pixmap exp;
	protected Texture expTexture;

	public AbstractUI(MyGame game) {
		super(game);
		this.stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

	}

	@Override
	public void show() {
		//Gdx.input.setInputProcessor(stage);
		float w = Constants.VIEWPORT_GUI_WIDTH;
		float h =Constants.VIEWPORT_GUI_HEIGHT;

		TextureAtlas atlas = MyGame.manager.get("ui/ui.pack",TextureAtlas.class);

		stage.clear();
		// Background initialisation
		Stack stack = new Stack();
		stage.addActor(stack);

		stack.setSize(w, h);
		stack.add(buildPlayerBarLayer(atlas));
		stack.add(buildLevelBarLayer(atlas));
		stage.addActor(buildPlayerInformation());

	};

	private Label buildPlayerInformation() {

		Label name= new Label(game.player.getName(),skin);
		name.setPosition(124, Constants.VIEWPORT_GUI_HEIGHT - 54);

		return name;
	}

	private Actor buildLevelBarLayer(TextureAtlas atlas) {
		Table t = new Table();

		levelBar = new Image(atlas.findRegion("level-bar"));
		levelBar.setPosition(0, 0);
		levelBar.pack();
		t.add(levelBar);
		t.top();
		t.left();
		t.pack();
		return t;
	}

	private Actor buildPlayerBarLayer(TextureAtlas atlas) {
		Table t = new Table();
		playerBar = new Image(atlas.findRegion("player-bar"));
		//		playerBar.setSize((float) (Constants.VIEWPORT_GUI_WIDTH*0.5), (float) (Constants.VIEWPORT_GUI_HEIGHT*0.35));
		playerBar.setPosition(0, 0);
		//		playerBar.addAction(sequence(Actions.fadeOut(0.0001f),Actions.fadeIn(3f)));
		playerBar.pack();

		t.add(playerBar);
		t.top();
		t.left();
		t.pack();
		return t;
	}

	public Texture buildHealthBar(int width, int height, int radius, Color color) {

		health = new Pixmap(width, height, Format.RGBA8888);
		health.setColor(color);

//		health.fillRectangle(0, radius, health.getWidth(), health.getHeight()-2*radius);

		health.fillRectangle(radius, 0, health.getWidth() - 2*radius, health.getHeight());

		// Bottom-left circle
//		health.fillCircle(radius, radius, radius);

		// Top-left circle
//		health.fillCircle(radius, health.getHeight()-radius, radius);

		// Bottom-right circle
		health.fillCircle(health.getWidth()-radius, radius, radius);

		// Top-right circle
		health.fillCircle(health.getWidth()-radius, health.getHeight()-radius, radius);
		
		healthTexture = new Texture(health);
		
		return healthTexture;
	}
	
	public Texture buildExpBar(int width, int height, int radius, Color color) {

		exp = new Pixmap(width, height, Format.RGBA8888);
		exp.setColor(color);

		exp.fillRectangle(radius, 0, health.getWidth() - 2*radius, health.getHeight());

		// Bottom-right circle
		exp.fillCircle(health.getWidth()-radius, radius, radius);

		// Top-right circle
		exp.fillCircle(health.getWidth()-radius, health.getHeight()-radius, radius);
		
		expTexture = new Texture(exp);
		
		return expTexture;
	}
	@Override
	public void render(float delta) {
		
		batch.setProjectionMatrix(cameraGUI.combined);
        
        buildHealthBar(160, 15, 8, new Color(Color.valueOf("f4121a")));
        buildExpBar(165, 15, 8, new Color(Color.valueOf("50c064")));

		stage.act(delta);
		stage.draw();
		
        batch.begin();
        batch.draw(healthTexture, 118, Constants.VIEWPORT_GUI_HEIGHT - 78);
        batch.draw(expTexture, 117, Constants.VIEWPORT_GUI_HEIGHT - 99);
        batch.end();

	}

	@Override
	public void dispose() {
		skin.dispose();
		batch.dispose();
		stage.dispose();
	};
}
