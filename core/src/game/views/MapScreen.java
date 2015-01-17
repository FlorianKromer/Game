package game.views;

import game.controllers.AbstractScreen;
import game.controllers.MovingController;
import game.controllers.MyGame;
import game.util.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Premier ecran en début de partie
 * 
 * @author Florian
 * 
 */
public class MapScreen extends AbstractScreen {

	/**
	 * {@link Stage}
	 */
	private Stage stage;

    Texture img;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;
    SpriteBatch sb;
    Texture texture;
    private Sprite sprite;

	/**
	 * 
	 * @param myGame
	 */
	public MapScreen(MyGame myGame) {
		super(myGame);
		this.stage = new Stage(new StretchViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT));

		this.stage.getViewport().setCamera(cameraGUI);
		

	}

	@Override
	public void resize(int width, int height) {
		Vector2 size = Scaling.fit.apply(Constants.VIEWPORT_GUI_WIDTH,
				Constants.VIEWPORT_GUI_HEIGHT, width, height);
		int viewportX = (int) (width - size.x) / 2;
		int viewportY = (int) (height - size.y) / 2;
		int viewportWidth = (int) size.x;
		int viewportHeight = (int) size.y;
		Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
		stage.getViewport().update(width, height, false);

	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		batch.dispose();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.setProjectionMatrix(stage.getCamera().combined);

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        sb.begin();
        getSprite().draw(sb);
        sb.end();
        
        // Get the width and height of our maps
        // Then halve it, as our sprites are 64x64 not 32x32 that our map is made of
        int mapWidth = tiledMap.getProperties().get("width",Integer.class)/2;
        int mapHeight = tiledMap.getProperties().get("height",Integer.class)/2;

        // Create a new map layer
        TiledMapTileLayer tileLayer = new TiledMapTileLayer(mapWidth,mapHeight,64,64);
        
        // Create a cell(tile) to add to the layer
        TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
        
        // The sprite/tilesheet behind our new layer is a single image (our sprite)
        // Create a TextureRegion that is the entire size of our texture
        TextureRegion textureRegion = new TextureRegion(texture,64,64);
        
        // Now set the graphic for our cell to our newly created region
        cell.setTile(new StaticTiledMapTile(textureRegion));
        
        // Now set the cell at position 4,10 ( 8,20 in map coordinates ).  This is the position of a tree
        // Relative to 0,0 in our map which is the bottom left corner
        tileLayer.setCell(4,10,cell);

        // Ok, I admit, this part is a gross hack. 
        // Get the current top most layer from the map and store it
        MapLayer tempLayer = tiledMap.getLayers().get(tiledMap.getLayers().getCount()-1);
        // Now remove it
        tiledMap.getLayers().remove(tiledMap.getLayers().getCount()-1);
        // Now add our newly created layer
        tiledMap.getLayers().add(tileLayer);
        // Now add it back, now our new layer is not the top most one.
        tiledMap.getLayers().add(tempLayer);
        
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show() {

		
		// on construit les layers
		update();
		// on informe le joueur qu'il est dans la vague X

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        
        setTiledMap(new TmxMapLoader().load("maps/firstmap.tmx"));
        tiledMapRenderer = new OrthogonalTiledMapRenderer(getTiledMap());
		// on dit a l'appli d'ecouter ce stage
//		Gdx.input.setInputProcessor(new MovingController(this));
	}

	/**
	 * methode qui maj tout les composants
	 */
	public void update() {

	}






	
	public void clearScreen(){
		stage.clear();
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

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

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

}
