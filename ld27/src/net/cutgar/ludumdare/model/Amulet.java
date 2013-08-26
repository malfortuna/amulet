package net.cutgar.ludumdare.model;

import net.cutgar.ludumdare.state.PlayState;

import org.flixel.FlxG;
import org.flixel.FlxSprite;

public class Amulet extends FlxSprite {

	public boolean caged;
	
	public Amulet(int x, int y, boolean caged){
		super(PlayState.RES*x, PlayState.RES*y);
		this.caged = caged;
		if(!caged)
			loadGraphic("pack.atlas:amulet");
		else
			loadGraphic("pack.atlas:amuletcage");
	}
	
}
