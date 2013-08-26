package net.cutgar.ludumdare.model;

public class Troll extends Monster {
	
	public Troll(int x, int y){
		super(x, y);
		loadGraphic("pack.atlas:troll");
		this.hp = 2;
		this.dmg = 2;
		name = "Troll";
		description = "A towering, muscular giant. Powerful and hard to kill.";
		
	}

}
