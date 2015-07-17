package game.views;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


public class OrthogonalTiledMapRendererWithSprites extends OrthogonalTiledMapRenderer {
	//    private SpriteBatch sprite;
	private List<Sprite> sprites;
	private int drawSpritesAfterLayer = 1;

	public OrthogonalTiledMapRendererWithSprites(TiledMap map) {
		super(map);
		sprites = new ArrayList<Sprite>();
	}

	public void addSprite(Sprite sprite){
		sprites.add(sprite);
	}

	@Override
	public void render() {
		beginRender();
	    Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		int currentLayer = 0;
		for (MapLayer layer : map.getLayers()) {
			if (layer.isVisible()) {
				if (layer instanceof TiledMapTileLayer) {
					renderTileLayer((TiledMapTileLayer)layer);
					currentLayer++;
					if(currentLayer == drawSpritesAfterLayer){
						for(Sprite sprite : sprites){
							sprite.draw(this.getBatch());
						}
					}
				} else {
					for (MapObject object : layer.getObjects()) {
						renderObject(object);
					}
				}
			}
		}
	    Gdx.gl.glDisable(GL20.GL_BLEND);
		endRender();
	}
}