package net.cutgar.ludumdare;

import net.cutgar.ludumdare.state.MenuState;

import org.flixel.FlxGame;

public class LudumDare extends FlxGame
{
	public LudumDare()
	{
		super(200, 240, MenuState.class, 2, 50, 50, false);
	}
}
