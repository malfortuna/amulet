package net.cutgar.ludumdare.model;

import net.cutgar.ludumdare.state.PlayState;

public class Dummy extends Monster {

	public Dummy(int x, int y) {
		super(x, y);
		loadGraphic("pack.atlas:dummy");
		hp = 1;
		dmg = 0;
		moves = false;
		attacks = false;
		name = "Dummy";
		description = "Terrifying! Until you hit it.";
	}

}
