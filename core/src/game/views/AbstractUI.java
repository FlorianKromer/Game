package game.views;

import game.controllers.AbstractScreen;
import game.controllers.MyGame;
import game.util.Constants;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class AbstractUI extends AbstractScreen{
	protected Image playerBar;
	protected Image levelBar;
	//life 
	protected Pixmap health;
	protected Image healthBar;
	//exp
	protected Pixmap sp;
	protected Image spBar;
	
	protected Window menu;

	public AbstractUI(MyGame game) {
		super(game);

	}

	@Override
	public void show() {
		super.show();
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

        int fullWidthHealthBar = 160;
        double hpPerc = game.player.getHp() / game.player.getHpMax() * 100;
        int fullWidthSpBar = 165;
        double spPerc = game.player.getMana() / game.player.getManaMax() * 100;
        
        buildSpBar((int) (spPerc*fullWidthSpBar/100), 15, 8, new Color(Color.valueOf("50c064")));
		buildHealthBar((int) (hpPerc*fullWidthHealthBar/100), 15, 8, new Color(Color.valueOf("f4121a")));
		
		stage.addActor(healthBar);
		stage.addActor(spBar);
		
		stage.addActor(buildMenu());

		inputMultiplexer.addProcessor(stage);


	};

	private Window buildMenu() {

		menu = new Window("Menu", skin);
		menu.getButtonTable().add(new TextButton("X", skin)).height(menu.getPadTop());
		menu.setFillParent(true);
		VerticalGroup listChars = new VerticalGroup();
		Table charTable = new Table();
		Image playerImage = new Image(game.player.getPortrait());
		Label playerName = new Label(game.player.getName(),skin);
		Label playerClass = new Label(game.player.getClassType(),skin);
		Label playerLevel = new Label(""+game.player.getLevel(),skin);
		charTable.add(playerImage).padRight(100);
		charTable.add(playerName).padRight(100);
		charTable.add(playerClass).padRight(100);
		charTable.add(playerLevel).padRight(100);
		charTable.row();
		charTable.top();
		charTable.left();
		charTable.pack();

		listChars.addActor(charTable);
		listChars.left();
		listChars.pack();
		menu.add(listChars);
		menu.pack();
		menu.setVisible(false);
		return menu;
	}

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

	public Image buildHealthBar(int width, int height, int radius, Color color) {

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
		
		healthBar = new Image(new Texture(health));
		healthBar.setPosition(118, Constants.VIEWPORT_GUI_HEIGHT - 78);
		
		return healthBar;
	}
	
	public Image buildSpBar(int width, int height, int radius, Color color) {

		sp = new Pixmap(width, height, Format.RGBA8888);
		sp.setColor(color);

		sp.fillRectangle(radius, 0, sp.getWidth() - 2*radius, sp.getHeight());

		// Bottom-right circle
		sp.fillCircle(sp.getWidth()-radius, radius, radius);

		// Top-right circle
		sp.fillCircle(sp.getWidth()-radius, sp.getHeight()-radius, radius);
		
		spBar = new Image(new Texture(sp));
		spBar.setPosition( 116, Constants.VIEWPORT_GUI_HEIGHT - 99);
		
		return spBar;
	}
	
	public void toggleMenu(){
		menu.setVisible(!menu.isVisible());
	}
	@Override
	public void render(float delta) {
		
		batch.setProjectionMatrix(cameraGUI.combined);
        int fullWidthHealthBar = 160;
        double hpPerc = game.player.getHp() / game.player.getHpMax() * 100;
        int fullWidthSpBar = 165;
        double spPerc = game.player.getMana() / game.player.getManaMax() * 100;
        
        buildSpBar((int) (spPerc*fullWidthSpBar/100), 15, 8, new Color(Color.valueOf("50c064")));
		buildHealthBar((int) (hpPerc*fullWidthHealthBar/100), 15, 8, new Color(Color.valueOf("f4121a")));

		stage.act(delta);
		stage.draw();
		
//        batch.begin();
//        batch.draw(healthTexture, 118, Constants.VIEWPORT_GUI_HEIGHT - 78);
//        batch.draw(spTexture, 117, Constants.VIEWPORT_GUI_HEIGHT - 99);
//        batch.end();

	}

	@Override
	public void dispose() {
		skin.dispose();
		batch.dispose();
		stage.dispose();
	};
}
