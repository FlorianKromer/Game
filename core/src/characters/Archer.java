package characters;

public class Archer extends Character {

	public Archer(char sex, String name, String classType) {
		super(sex, name, classType);
		this.hpMax = 80;
		this.hp = this.hpMax;
		this.manaMax = 35;
		this.mana = manaMax;
		this.strength = 70;
		this.speed = 60;
		this.intel = 45;
		
	}

}
