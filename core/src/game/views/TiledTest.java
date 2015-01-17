package game.views;

import game.controllers.AbstractScreen;
import game.controllers.MovingController;
import game.controllers.MyGame;
import game.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import game.models.world.Character;

public class TiledTest extends AbstractScreen {
	private Stage stage;

    private OrthographicCamera camera;
	private OrthogonalTiledMapRendererWithSprites tiledMapRenderer;
//    private Sprite sprite;
    private TiledMap tiledMap;
//    private Window selectWindow;
    
    private Character character;
    
    public TiledTest(MyGame myGame) {
		super(myGame);
        camera = new OrthographicCamera();
        character = new Character();
        
		this.stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

		this.stage.getViewport().setCamera(cameraGUI);
	}

    
    @Override
	public void show() {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();	

        camera.setToOrtho(false,Constants.VIEWPORT_GUI_WIDTH,Constants.VIEWPORT_GUI_HEIGHT);
        camera.update();



        TiledMap tiledMap = new TmxMapLoader().load(("maps/firstmap.tmx"));
        tiledMapRenderer = new OrthogonalTiledMapRendererWithSprites(tiledMap);
        tiledMapRenderer.addSprite(character);
        Gdx.input.setInputProcessor(new MovingController(this));
    }

	@Override
	public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
//        character.draw(batch);
    }


	public OrthographicCamera getCamera() {
		return camera;
	}


	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}


    public TiledMap getTiledMap() {
		return tiledMap;
	}


	public void setTiledMap(TiledMap tiledMap) {
		this.tiledMap = tiledMap;
	}


	public Character getCharacter() {
		return character;
	}


	public void setCharacter(Character character) {
		this.character = character;
	}


	public OrthogonalTiledMapRendererWithSprites getTiledMapRenderer() {
		return tiledMapRenderer;
	}


	public void setTiledMapRenderer(
			OrthogonalTiledMapRendererWithSprites tiledMapRenderer) {
		this.tiledMapRenderer = tiledMapRenderer;
	}

}