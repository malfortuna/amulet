package net.cutgar.ludumdare.model;

import net.cutgar.ludumdare.state.PlayState;

import org.flixel.FlxG;
import org.flixel.FlxSprite;

public class Player extends FlxSprite {
	
	public int knight_hp = 2;
	public int thief_hp = 1;
	public int hp = 2;
	public int dmg = 2;
	public int playerClass = 0;
	public String name = "";
	public String description = "";
	
	public Player(int x, int y, int type){
		super(x*PlayState.RES, y*PlayState.RES);
		switch(type){
		case 0: loadGraphic("pack.atlas:knight"); name = "The Knight"; description = "A valiant guard of the realm."; break;
		case 1: loadGraphic("pack.atlas:thief"); name = "The Thief"; description = "SHIFT + Move: Move 2 tiles at once"; hp = 1; dmg = 1; break;
		}
		playerClass = type;
	}
	
	public void update(){
		
	}

	public void nextClass() {
		playerClass = (playerClass + 1) % 2;
		switch(playerClass){
		case 0: loadGraphic("pack.atlas:knight"); name = "The Knight"; description = "A valiant guard of the realm."; thief_hp = hp; hp = knight_hp; dmg = 2; break;
		case 1: loadGraphic("pack.atlas:thief"); name = "The Thief"; description = "SHIFT + Move: Dash 2 tiles at once"; knight_hp = hp; hp = thief_hp; dmg = 1; break;
		}
	}

}
