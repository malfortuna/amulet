package net.cutgar.ludumdare.state;

import net.cutgar.ludumdare.model.LevelDB;

import org.flixel.FlxG;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxText;

public class ChapterState extends FlxState {

	public boolean levelStarted = false;
	public boolean fading_out = false;
	public boolean fading_in = false;
	public float fadeAmount = 1.0f;
	
	private FlxText intro_message;
	private FlxSprite intro_icon;
	private FlxSprite intro_backdrop;
	
	public String icon;
	public String text;
	public FlxState nextState;
	
	public int iconwidth = 10;
	
	public ChapterState(){
		icon = "knight";
		text = "Chapter One\nThe Knight";
	}
	
	public ChapterState(String icon, String text, FlxState next){
		this.icon = icon;
		this.text = text;
		this.nextState = next;
	}
	
	public ChapterState(String icon, String text, FlxState next, int _iconwidth){
		this.icon = icon;
		this.text = text;
		this.nextState = next;
		this.iconwidth = _iconwidth;
	}
	
	@Override
	public void create() {
		FlxG.setBgColor(0xff333333);
		
		FlxG.playMusic("maintheme.wav");
		
		intro_backdrop = new FlxSprite(0, 0);
		intro_backdrop.makeGraphic(FlxG.width, FlxG.height, 0xff000000);
		add(intro_backdrop);
		
		intro_message = new FlxText(0, FlxG.height/2, FlxG.width, text);
		intro_message.setAlignment("center");
		add(intro_message);
		
		intro_icon = new FlxSprite(FlxG.width/2 - iconwidth, FlxG.height/2 - FlxG.height/4);
		intro_icon.loadGraphic(icon);
		add(intro_icon);
		
		FlxText footer = new FlxText(10, FlxG.height/2 + FlxG.height/4, FlxG.width-20, "Press A Key");
		footer.setAlignment("center");
		add(footer);
	}
	
	public void update(){
		if(FlxG.keys.any()){
			FlxG.switchState(nextState);
		}
	}

}
