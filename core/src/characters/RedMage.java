package characters;

public class RedMage extends Character {

	public RedMage(char sex, String name, String classType) {
		super(sex, name, classType);
		this.hpMax = 80;
		this.hp = this.hpMax;
		this.manaMax = 50;
		this.mana = manaMax;
		this.strength = 60;
		this.speed = 50;
		this.intel = 50;
		this.wearableWeapons.add("Arcs");
		this.wearableWeapons.add("Epées");
		this.wearableWeapons.add("Dagues");
		this.wearableWeapons.add("Lances");
	}

}
