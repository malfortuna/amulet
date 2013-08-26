package net.cutgar.ludumdare.model;

import java.util.LinkedList;
import java.util.List;

import org.flixel.FlxState;

public class Level {

	public int[][] map;
	
	public int px;
	public int py;
	public int startingClass = 0;
	
	public int ax;
	public int ay;
	public boolean locked;
	
	public List<Monster> monsters = new LinkedList<Monster>();
	
	public String message = "";
	public String icon = "";
	
	public FlxState after = null;

	public float iconoff = 10;
	
	public Level(){
		
	}
	
}
