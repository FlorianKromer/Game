package game.controllers;

import game.views.TiledTest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Vector3;

public class MovingController implements InputProcessor {
	TiledTest screen;
	int mapPixelWidth;
	int mapPixelHeight;
	
	public MovingController(TiledTest screen) {
		// TODO Auto-generated constructor stub
		this.screen = screen;
		MapProperties prop = screen.getTiledMapRenderer().getMap().getProperties();

		int mapWidth = prop.get("width", Integer.class);
		int mapHeight = prop.get("height", Integer.class);
		int tilePixelWidth = prop.get("tilewidth", Integer.class);
		int tilePixelHeight = prop.get("tileheight", Integer.class);

		mapPixelWidth = mapWidth * tilePixelWidth;
		mapPixelHeight = mapHeight * tilePixelHeight;
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		float x = screen.getCharacter().getX();
		float y = screen.getCharacter().getY();
		float camViewX = screen.getTiledMapRenderer().getViewBounds().width/2;
		float camViewY = screen.getTiledMapRenderer().getViewBounds().height/2;
		System.out.println("xLeft:"+camViewY);
		System.out.println("screen.getCamera().position:"+screen.getCamera().position);
		System.out.println("screen.getCamera().viewportHeight:"+screen.getCamera().viewportHeight );
//		System.out.println("screen.getTiledMapRenderer().getViewBounds():"+screen.getTiledMapRenderer().getViewBounds());
//		System.out.println("mapPixelWidth"+mapPixelWidth+"mapPixelHeight"+mapPixelHeight);
		System.out.println("x:"+x+"y:"+y);
		
		if(keycode == Input.Keys.UP)
		{
			if(y+screen.getCharacter().getVitesse() <= mapPixelHeight - screen.getCharacter().getVitesse()){
				screen.getCharacter().setPosition(x, y+screen.getCharacter().getVitesse());
				screen.getCharacter().setTypeAnimation(4);				
				if(screen.getCamera().position.y  <= screen.getCamera().viewportHeight + camViewY && screen.getCamera().position.y  >= camViewY ){
					screen.getCamera().position.y = y;
				}
			}

		}
		if(keycode == Input.Keys.DOWN)
		{
			if(y + screen.getCharacter().getVitesse() >= screen.getCharacter().getVitesse()){
				screen.getCharacter().setPosition(x, y-screen.getCharacter().getVitesse());
				screen.getCharacter().setTypeAnimation(0);
				if(screen.getCamera().position.y  >= camViewY && screen.getCamera().position.y  <= screen.getCamera().viewportHeight + camViewY ){
					screen.getCamera().position.y = y;
				}
			}
		}
		if(keycode == Input.Keys.RIGHT )
		{
			if(x+screen.getCharacter().getVitesse() <= mapPixelWidth){
				screen.getCharacter().setPosition(x+screen.getCharacter().getVitesse(), y);
				screen.getCharacter().setTypeAnimation(7);
				if(screen.getCamera().position.x <= screen.getCamera().viewportWidth - camViewX/2 ){
					screen.getCamera().position.x = x;
					
				}
			}
		}
		if(keycode == Input.Keys.LEFT )
		{
			if(x-screen.getCharacter().getVitesse() >= 0){

				screen.getCharacter().setPosition(x-screen.getCharacter().getVitesse(), y);
				screen.getCharacter().setTypeAnimation(3);

				if(screen.getCamera().position.x   >= camViewX + screen.getCharacter().getVitesse()){
					screen.getCamera().position.x = x;
					
				}
			}
		}

		//		if(screen.getCamera().position.x - xLeft - screen.getCharacter().getVitesse()>=0 || screen.getTiledMapRenderer().getViewBounds().width - x < screen.getTiledMapRenderer().getViewBounds().width){


		if(keycode == Input.Keys.NUM_1)
			screen.getTiledMap().getLayers().get(0).setVisible(!screen.getTiledMap().getLayers().get(0).isVisible());
		if(keycode == Input.Keys.NUM_2)
			screen.getTiledMap().getLayers().get(1).setVisible(!screen.getTiledMap().getLayers().get(1).isVisible());
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
