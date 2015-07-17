package game.views;

import game.controllers.MovingController;
import game.controllers.MyGame;
import game.models.world.Character;
import game.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * Premier ecran en début de partie
 * 
 * @author Florian
 * 
 */
public class MapScreen extends AbstractUI {

    private OrthographicCamera camera;
	private OrthogonalTiledMapRendererWithSprites tiledMapRenderer;
    private Character character;
    
    
    public MapScreen(MyGame myGame) {
		super(myGame);
        
        TiledMap tiledMap = new TmxMapLoader().load(("maps/rogue.tmx"));
        tiledMapRenderer = new OrthogonalTiledMapRendererWithSprites(tiledMap);
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer) tiledMapRenderer.getMap().getLayers().get(1);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera((int)(Constants.VIEWPORT_GUI_WIDTH*0.7),(int)( Constants.VIEWPORT_GUI_WIDTH*0.7 * (h / w)));
        camera.position.set(2 * collisionLayer.getTileWidth(), (collisionLayer.getHeight() - 2) * collisionLayer.getTileHeight(), 0);
        camera.update();
        
        
	}

    
    @Override
	public void show() {
    	character = super.game.player;
       
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer) tiledMapRenderer.getMap().getLayers().get(1);

		//init position
        character.setPosition(2 * collisionLayer.getTileWidth(), (collisionLayer.getHeight() - 2) * collisionLayer.getTileHeight());

        
        tiledMapRenderer.addSprite(character);
        Gdx.input.setInputProcessor(new MovingController(this));
        
        super.show();
    }

	@Override
	public void render(float delta) {

        
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
       
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        
		super.render(delta);

    }



	/**
	 * @return the tiledMapRenderer
	 */
	public OrthogonalTiledMapRendererWithSprites getTiledMapRenderer() {
		return tiledMapRenderer;
	}


	/**
	 * @param tiledMapRenderer the tiledMapRenderer to set
	 */
	public void setTiledMapRenderer(
			OrthogonalTiledMapRendererWithSprites tiledMapRenderer) {
		this.tiledMapRenderer = tiledMapRenderer;
	}


	/**
	 * @return the character
	 */
	public Character getCharacter() {
		return character;
	}


	/**
	 * @param character the character to set
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}


	/**
	 * @return the camera
	 */
	public OrthographicCamera getCamera() {
		return camera;
	}


	/**
	 * @param camera the camera to set
	 */
	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}



}
