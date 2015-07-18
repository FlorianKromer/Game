package characters;

public class BlackMage extends Character {

	public BlackMage(char sex, String name, String classType) {
		super(sex, name, classType);
		this.hpMax = 70;
		this.hp = this.hpMax;
		this.manaMax = 65;
		this.mana = manaMax;
		this.strength = 50;
		this.speed = 50;
		this.intel = 70;
		this.wearableWeapons.add("Bâtons");
		this.wearableWeapons.add("Arcs");

		
	}

}
