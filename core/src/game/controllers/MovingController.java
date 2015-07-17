package game.controllers;

import game.util.Constants;
import game.views.MapScreen;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class MovingController implements InputProcessor {
	MapScreen screen;
	int mapPixelWidth;
	int mapPixelHeight;
	TiledMapTileLayer collisionLayer ;

	public MovingController(MapScreen screen) {
		// TODO Auto-generated constructor stub
		this.screen = (MapScreen) screen;
		MapProperties prop = screen.getTiledMapRenderer().getMap().getProperties();

		int mapWidth = prop.get("width", Integer.class);
		int mapHeight = prop.get("height", Integer.class);
		int tilePixelWidth = prop.get("tilewidth", Integer.class);
		int tilePixelHeight = prop.get("tileheight", Integer.class);

		mapPixelWidth = mapWidth * tilePixelWidth;
		mapPixelHeight = mapHeight * tilePixelHeight;
		
		collisionLayer = (TiledMapTileLayer) screen.getTiledMapRenderer().getMap().getLayers().get("walkable");
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	private boolean isCellBlocked(float x, float y) {
		int posX =  (int) (x / collisionLayer.getTileWidth())  ;
		int posY =  (int) (y / collisionLayer.getTileHeight());
	
		
		Cell cell = collisionLayer.getCell(posX, posY);
		System.out.println("x"+posX+"| y:"+posY);
		return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(Constants.blockedKey);
	}
	@Override
	public boolean keyUp(int keycode) {
		//player
		float charXPosition = screen.getCharacter().getX();
		float charYPosition = screen.getCharacter().getY();
		float moveSize = screen.getCharacter().getVitesse();
		//camera
		float camViewX = screen.getTiledMapRenderer().getViewBounds().width/2;
		float camViewY = screen.getTiledMapRenderer().getViewBounds().height/2;
		float camXPosition = screen.getCamera().position.x;
		float camYPosition = screen.getCamera().position.y;

		//boolean 
		boolean isMovePossible = false;
		boolean isCameraNeedToMove = false;
		boolean camPositionUnderViewPort = false;
		
		//debug
//		System.out.println("camViewY:"+camViewY);
//		System.out.println("screen.getCamera().position:"+screen.getCamera().position);
//		System.out.println("screen.getCamera().viewportHeight:"+screen.getCamera().viewportHeight );
//		System.out.println("screen.getTiledMapRenderer().getViewBounds():"+screen.getTiledMapRenderer().getViewBounds());
//		System.out.println("mapPixelWidth:"+mapPixelWidth+" | mapPixelHeight:"+mapPixelHeight);
		
		//cam
		OrthographicCamera  cam =  screen.getCamera();
		
		if(keycode == Input.Keys.UP)
		{
			//position + move <= mapHeight
			isMovePossible = charYPosition+moveSize <= mapPixelHeight - moveSize;

			if(isMovePossible && ! isCellBlocked(charXPosition, charYPosition+moveSize) ){
				screen.getCharacter().setPosition(charXPosition, charYPosition+moveSize);
				screen.getCharacter().setTypeAnimation(3);	
					cam.translate(0, moveSize, 0);

			}
		}
		else if(keycode == Input.Keys.DOWN)
		{
			isMovePossible = charYPosition + moveSize >= moveSize;
			if(isMovePossible && ! isCellBlocked(charXPosition, charYPosition-moveSize) ){
				screen.getCharacter().setPosition(charXPosition, charYPosition-moveSize);
				screen.getCharacter().setTypeAnimation(0);
				cam.translate(0, -moveSize, 0);


			}
		}
		if(keycode == Input.Keys.RIGHT )
		{
			isMovePossible = charXPosition+ 2*moveSize <= mapPixelWidth;

			if(isMovePossible && ! isCellBlocked(charXPosition+moveSize, charYPosition) ){
				screen.getCharacter().setPosition(charXPosition+screen.getCharacter().getVitesse(), charYPosition);
				screen.getCharacter().setTypeAnimation(2);
				cam.translate(moveSize,0, 0);

			}
		}
		if(keycode == Input.Keys.LEFT )
		{
			isMovePossible = charXPosition-moveSize >= 0;
			if(isMovePossible && ! isCellBlocked(charXPosition-moveSize, charYPosition) ){

				screen.getCharacter().setPosition(charXPosition-screen.getCharacter().getVitesse(), charYPosition);
				screen.getCharacter().setTypeAnimation(1);
				cam.translate(- moveSize,0, 0);

			}
		}


		return false;
		
		
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//	    Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
		//	    Vector3 position = screen.getCamera().unproject(clickCoordinates);
		//	    screen.getCharacter().setPosition(position.x, position.y);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
