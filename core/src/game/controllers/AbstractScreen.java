package game.controllers;

import game.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public abstract class AbstractScreen implements com.badlogic.gdx.Screen {

	protected MyGame game;
	protected Skin skin;
	protected SpriteBatch batch;
	protected OrthographicCamera cameraGUI;
	private static volatile long timePlayed;
    protected InputMultiplexer inputMultiplexer;
    protected Stage stage;

	public AbstractScreen(MyGame game) {
		this.game = game;
		this.skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		this.skin.getFont("default-font").getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		this.batch = new SpriteBatch();
		this.cameraGUI = new OrthographicCamera(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		this.cameraGUI.setToOrtho(false); // flip y-axis
		this.cameraGUI.update();
		this.stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

		this.inputMultiplexer = new InputMultiplexer();
		this.inputMultiplexer.addProcessor(stage);

		AbstractScreen.timePlayed = getTimePlayed();
	}

	@Override
	public void pause() {
	};

	@Override
	public void resume() {
	};

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		batch.dispose();
	};

	@Override
	public void hide() {
	};

	@Override
	public void show() {
		// on dit a l'appli d'ecouter ce stage quand la methode show est appelee
		Gdx.input.setInputProcessor(inputMultiplexer);

	};
	
	public void destroy() {
	};

	@Override
	public void render(float arg0) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
	    Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cameraGUI.combined);

	}

	@Override
	public void resize(int width, int height) {
		batch.setProjectionMatrix(cameraGUI.combined);
		
	}
	
	/**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
    public final static long getTimePlayed() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet 
        //d'éviter un appel coûteux à synchronized, 
        //une fois que l'instanciation est faite.
        if (AbstractScreen.timePlayed == 0) {
           // Le mot-clé synchronized sur ce bloc empêche toute instanciation
           // multiple même par différents "threads".
           // Il est TRES important.
           synchronized(AbstractScreen.class) {
             if (AbstractScreen.timePlayed == 0) {
            	 AbstractScreen.timePlayed = TimeUtils.millis();
             }
           }
        }
        return AbstractScreen.timePlayed;
    }

}