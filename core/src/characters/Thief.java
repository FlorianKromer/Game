package characters;

import java.util.ArrayList;

public class Thief extends Character {

	public Thief(char sex, String name, String classType) {
		super(sex, name, classType);
		this.hpMax = 80;
		this.hp = this.hpMax;
		this.manaMax = 30;
		this.mana = manaMax;
		this.strength = 70;
		this.speed = 70;
		this.intel = 35;
		this.wearableWeapons.add("Dagues");
	}

}
