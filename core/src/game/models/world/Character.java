package game.models.world;

import game.util.Constants;

import java.util.Arrays;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Character extends Sprite{
	private static final int nb_colone = 4;
	private static final int nb_ligne = 8;
	private static final int nombre_image_animation = 4;
	private static final float duree_animation = 1f;
	private  float vitesse = 32f;

	private Texture sheetTexture;
	private Animation animationLapin[];
	private TextureRegion regionCourante,regionInitial;

	private  int largeur_texture ;
	private  int hauteur_texture;

	private  float   temps;

	private int typeAnimation;
	private boolean animationStop;


	public Character() {
		       

		// Initialisation  
		sheetTexture = new Texture(Gdx.files.internal("maps/rabbitmanSheet.PNG"));
		largeur_texture = sheetTexture.getWidth();
		hauteur_texture = sheetTexture.getHeight();
		animationStop = false;
		animationLapin = new Animation[8] ;
		typeAnimation=0;
		temps=0.0f;

		// Construction du tableau d'images constituants l'animation
		TextureRegion[][] tmp = TextureRegion.split(sheetTexture, largeur_texture/nb_colone, hauteur_texture/nb_ligne);
		regionInitial = tmp[0][1];

		// Positionner le Lapin en milieu
		setX(Constants.VIEWPORT_GUI_WIDTH/2);
		setY(Constants.VIEWPORT_GUI_HEIGHT/2);

		// Instancier l'animation
		for(int i=0;i<8;i++)
			animationLapin[i] = new Animation(duree_animation/nombre_image_animation, tmp[i]);
        
	}



	@Override
	public void draw(Batch batch) {
		// Fixer lapin
		if(!animationStop) // si aucune manipulation en cours
		{
			temps += Gdx.graphics.getDeltaTime();                   
			regionCourante = animationLapin[typeAnimation].getKeyFrame(temps/1, true);  
		}
		// Dessiner Lapin
		this.setRegion(regionCourante);
		this.setRegionWidth(regionCourante.getRegionWidth());
		this.setRegionHeight(regionCourante.getRegionHeight());
		this.setSize(regionCourante.getRegionWidth()*2, regionCourante.getRegionHeight()*2);

		super.draw(batch);


	}
	
	@Override
	public void setPosition(float x, float y){
		super.setPosition(x, y);
	}






	public int getTypeAnimation() {
		return typeAnimation;
	}



	public void setTypeAnimation(int typeAnimation) {
		this.typeAnimation = typeAnimation;
	}



	public float getVitesse() {
		return vitesse;
	}



	public void setVitesse(float vitesse) {
		this.vitesse = vitesse;
	}
}