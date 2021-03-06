package game.models.world;

import game.controllers.MyGame;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Item {
	//attributs de classe
	/**
	 * arraylist permettant de repertorier les items existants
	 */
	public static HashMap<Integer, Item> listItem;
	private static volatile TextureAtlas atlas;

	//attribut d'un objet
	/**
	 * identifiant
	 */
	private int id;
	/**
	 * nom de l'objet
	 */
	private String name;

	/**
	 * si oui ItemId correspondant
	 */
	private int SkillId;
	/**
	 * l'image de l'item
	 */
	private Image image;
	/**
	 * rate de l'item , � partir duquel on peut l'avoir
	 */
	private int rate;

	/**
	 * constructeur 
	 * @param id
	 * @param name
	 * @param usable
	 * @param rate
	 */
	public Item(int id, String name, int SkillId, int rate) {
		super();
		this.id = id;
		this.name = name;
		this.SkillId = SkillId;
		this.rate = rate;
		this.image = new Image(getInstance().findRegion(""+id));

	}
	/**
	 * initialise la liste d'item
	 */
	public static HashMap<Integer, Item> buildListItem() {
		if(listItem == null){
			listItem = new HashMap<Integer, Item>();
			listItem.put(7026, new Item(7026,"Cle",-1,30));
			listItem.put(7170,new Item(7170,"Costume",1,50));
			listItem.put(2407,new Item(2407,"Chaussures Femme",-1,50));
			listItem.put(2338,new Item(2338,"Robe",-1,50));
			listItem.put(2634,new Item(2634,"Alliance Homme",-1,50));
			listItem.put(2635,new Item(2635,"Alliance Femme",-1,50));
			listItem.put(7801,new Item(7801,"Lettre",-1,50));
			listItem.put(744,new Item(744,"Fleurs",-1,50));
			listItem.put(529,new Item(529,"Bonbons",-1,50));
			 
		}
		return listItem;

	} 
	/**
	 * retrouve un item parmis les existant
	 * @param itemId
	 * @return
	 */
	public static Item selectItemFromItemID(int itemId) {
		if(listItem.containsKey(itemId)){
			return listItem.get(itemId);
		}
		return null;
	}

	/**
	 * M�thode permettant de renvoyer une instance de la classe Singleton
	 * 
	 * @return Retourne l'instance du singleton.
	 */
	private final static TextureAtlas getInstance() {
		// Le "Double-Checked Singleton"/"Singleton doublement v�rifi�" permet
		// d'�viter un appel co�teux � synchronized,
		// une fois que l'instanciation est faite.
		if (Item.atlas == null) {
			// Le mot-cl� synchronized sur ce bloc emp�che toute instanciation
			// multiple m�me par diff�rents "threads".
			// Il est TRES important.
			synchronized (Item.class) {
				if (Item.atlas == null) {
					Item.atlas = MyGame.manager.get("items/item.pack",
							TextureAtlas.class);
					buildListItem();
				}
			}
		}
		return Item.atlas;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public int getSkillId() {
		return SkillId;
	}

	public void setSkillId(int ItemId) {
		this.SkillId = ItemId;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name 
				+ ", SkillId=" + SkillId + ", image=" + image + ", rate="
				+ rate + "]";
	}
	
}
