package game.controllers;

import game.util.Constants;
import game.views.AbstractUI;
import game.views.MapScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class AbstractController implements InputProcessor {
	private MapScreen mapScreen;
	protected int mapPixelWidth;
	protected int mapPixelHeight;
	protected TiledMapTileLayer collisionLayer ;

	public AbstractController(MapScreen mapScreen) {
		// TODO Auto-generated constructor stub
		this.mapScreen = (MapScreen) mapScreen;
		MapProperties prop = mapScreen.getTiledMapRenderer().getMap().getProperties();

		int mapWidth = prop.get("width", Integer.class);
		int mapHeight = prop.get("height", Integer.class);
		int tilePixelWidth = prop.get("tilewidth", Integer.class);
		int tilePixelHeight = prop.get("tileheight", Integer.class);

		mapPixelWidth = mapWidth * tilePixelWidth;
		mapPixelHeight = mapHeight * tilePixelHeight;
		
		collisionLayer = (TiledMapTileLayer) mapScreen.getTiledMapRenderer().getMap().getLayers().get("walkable");
		


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
		float charXPosition = mapScreen.getCharacter().getX();
		float charYPosition = mapScreen.getCharacter().getY();
		float moveSize = mapScreen.getCharacter().getVitesse();
		//camera
//		float camViewX = screen.getTiledMapRenderer().getViewBounds().width/2;
//		float camViewY = screen.getTiledMapRenderer().getViewBounds().height/2;
//		float camXPosition = screen.getCamera().position.x;
//		float camYPosition = screen.getCamera().position.y;

		//boolean 
		boolean isMovePossible = false;
//		boolean isCameraNeedToMove = false;
//		boolean camPositionUnderViewPort = false;
//		
		//debug
//		System.out.println("camViewY:"+camViewY);
//		System.out.println("screen.getCamera().position:"+screen.getCamera().position);
//		System.out.println("screen.getCamera().viewportHeight:"+screen.getCamera().viewportHeight );
//		System.out.println("screen.getTiledMapRenderer().getViewBounds():"+screen.getTiledMapRenderer().getViewBounds());
//		System.out.println("mapPixelWidth:"+mapPixelWidth+" | mapPixelHeight:"+mapPixelHeight);
		
		//cam
		OrthographicCamera  cam =  mapScreen.getCamera();
		
		switch (keycode) {
		case Input.Keys.UP:
			//position + move <= mapHeight
			isMovePossible = charYPosition+moveSize <= mapPixelHeight - moveSize;

			if(isMovePossible && ! isCellBlocked(charXPosition, charYPosition+moveSize) ){
				mapScreen.getCharacter().setPosition(charXPosition, charYPosition+moveSize);
				mapScreen.getCharacter().setTypeAnimation(3);	
					cam.translate(0, moveSize, 0);

			}
			break;
			
		case Input.Keys.DOWN:
			isMovePossible = charYPosition + moveSize >= moveSize;
			if(isMovePossible && ! isCellBlocked(charXPosition, charYPosition-moveSize) ){
				mapScreen.getCharacter().setPosition(charXPosition, charYPosition-moveSize);
				mapScreen.getCharacter().setTypeAnimation(0);
				cam.translate(0, -moveSize, 0);


			}
			break;
		case Input.Keys.RIGHT:
			isMovePossible = charXPosition+ 2*moveSize <= mapPixelWidth;

			if(isMovePossible && ! isCellBlocked(charXPosition+moveSize, charYPosition) ){
				mapScreen.getCharacter().setPosition(charXPosition+mapScreen.getCharacter().getVitesse(), charYPosition);
				mapScreen.getCharacter().setTypeAnimation(2);
				cam.translate(moveSize,0, 0);

			}
			break;
		case Input.Keys.LEFT:
			isMovePossible = charXPosition-moveSize >= 0;
			if(isMovePossible && ! isCellBlocked(charXPosition-moveSize, charYPosition) ){

				mapScreen.getCharacter().setPosition(charXPosition-mapScreen.getCharacter().getVitesse(), charYPosition);
				mapScreen.getCharacter().setTypeAnimation(1);
				cam.translate(- moveSize,0, 0);

			}
			break;
		case Input.Keys.TAB:
			
			mapScreen.toggleMenu();
			Gdx.app.log("Input", "tab");
			break;
		default:
			break;
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
