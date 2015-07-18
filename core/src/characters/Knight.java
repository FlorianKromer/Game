package characters;

public class Knight extends Character {

	public Knight(char sex, String name, String classType) {
		super(sex, name, classType);
		this.hpMax = 110;
		this.hp = this.hpMax;
		this.manaMax = 2;
		this.mana = manaMax;
		this.strength = 80;
		this.speed = 50;
		this.intel = 30;
		this.wearableWeapons.add("Arcs");
		this.wearableWeapons.add("Epées");
		this.wearableWeapons.add("Dagues");
		this.wearableWeapons.add("Haches");

	}

}
