package characters;

public class WhiteMage extends Character {

	public WhiteMage(char sex, String name, String classType) {
		super(sex, name, classType);
		this.hpMax = 90;
		this.hp = this.hpMax;
		this.manaMax = 65;
		this.mana = manaMax;
		this.strength = 50;
		this.speed = 50;
		this.intel = 60;
		this.wearableWeapons.add("Bâtons");

	}

}
