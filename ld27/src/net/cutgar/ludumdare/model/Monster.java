package net.cutgar.ludumdare.model;

import net.cutgar.ludumdare.state.PlayState;

import org.flixel.FlxSprite;

public class Monster extends FlxSprite {

	public int hp = 1;
	public int dmg = 1;
	
	public boolean moves = true;
	public boolean attacks = true;
	
	public String name = "Beholder";
	public String description = "A terrifying creature with many tentacles.";
	
	public Monster(int x, int y){
		super(PlayState.RES*x, PlayState.RES*y);
		loadGraphic("pack.atlas:beholder");
	}
	
}
