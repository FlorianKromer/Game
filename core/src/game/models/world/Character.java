package game.models.world;

import game.controllers.MyGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Character extends Sprite{
	

	//animation
	private static final int nb_colone = 4;
	private static final int nb_ligne = 4;
	private static final int nombre_image_animation = 4;
	private static final float duree_animation = 1f;
	protected Texture sheetTexture;
	protected Animation animationLapin[];
	protected TextureRegion regionCourante,regionInitial;
	protected  int largeur_texture ;
	protected  int hauteur_texture;
	protected  float   temps;
	protected int typeAnimation;
	protected boolean animationStop;
	private  float vitesse = 32f;
	
	//status fields
	private String classType;
	private String name;
	private char sex;

	public Character(char sex, String name, String classType) {
		this.setClassType(classType);
		this.setName(name);
		
		
		sheetTexture = new Texture(Gdx.files.internal("character/ff/"+classType+"_"+sex+".PNG"));
		largeur_texture = sheetTexture.getWidth();
		hauteur_texture = sheetTexture.getHeight();
		animationStop = false;
		animationLapin = new Animation[nb_ligne] ;
		typeAnimation=0;
		temps=0.0f;

		// Construction du tableau d'images constituants l'animation
		TextureRegion[][] tmp = TextureRegion.split(sheetTexture, largeur_texture/nb_colone, hauteur_texture/nb_ligne);
		regionInitial = tmp[0][1];


		// Instancier l'animation
		for(int i=0;i<nb_ligne;i++)
			animationLapin[i] = new Animation(duree_animation/nombre_image_animation, tmp[i]);
        
	}

	public void initSprite(String classType){
		this.classType = classType;
		
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
		this.setSize(32, 48);
//		this.setScale(0.8f);

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



	public String getClassType() {
		return classType;
	}



	public void setClassType(String classType) {
		this.classType = classType;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public char getSex() {
		return sex;
	}



	public void setSex(char sex) {
		this.sex = sex;
	}
}