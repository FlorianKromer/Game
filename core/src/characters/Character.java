package characters;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class Character extends Sprite{
	

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
	private TextureRegion portrait;
	
	//status fields
	private String classType;
	private String name;
	private char sex;
	private int level;
	protected int hp;
	protected int hpMax;
	protected int mana;
	protected int manaMax;
	protected int strength;
	protected int speed;
	protected int intel;
	protected ArrayList<String> wearableWeapons;

	public Character(char sex, String name, String classType) {
		this.setClassType(classType);
		this.setName(name);
		this.wearableWeapons = new ArrayList<String>();

		
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
        
		Texture tmpTexture = new Texture(Gdx.files.internal("character/ff/luneth.PNG"));
		portrait = new TextureRegion(tmpTexture);
		
		
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

	/**
	 * @return the temps
	 */
	public float getTemps() {
		return temps;
	}

	/**
	 * @param temps the temps to set
	 */
	public void setTemps(float temps) {
		this.temps = temps;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * @param hp the hp to set
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * @return the hpMax
	 */
	public int getHpMax() {
		return hpMax;
	}

	/**
	 * @param hpMax the hpMax to set
	 */
	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	/**
	 * @return the mana
	 */
	public int getMana() {
		return mana;
	}

	/**
	 * @param mana the mana to set
	 */
	public void setMana(int mana) {
		this.mana = mana;
	}

	/**
	 * @return the manaMax
	 */
	public int getManaMax() {
		return manaMax;
	}

	/**
	 * @param manaMax the manaMax to set
	 */
	public void setManaMax(int manaMax) {
		this.manaMax = manaMax;
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @return the intel
	 */
	public int getIntel() {
		return intel;
	}

	/**
	 * @param intel the intel to set
	 */
	public void setIntel(int intel) {
		this.intel = intel;
	}

	/**
	 * @return the wearableWeapons
	 */
	public ArrayList<String> getWearableWeapons() {
		return wearableWeapons;
	}

	/**
	 * @param wearableWeapons the wearableWeapons to set
	 */
	public void setWearableWeapons(ArrayList<String> wearableWeapons) {
		this.wearableWeapons = wearableWeapons;
	}


	public TextureRegion getPortrait() {
		return portrait;
	}


	public void setPortrait(TextureRegion portrait) {
		this.portrait = portrait;
	}
}