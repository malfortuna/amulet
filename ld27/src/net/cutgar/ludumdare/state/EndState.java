package net.cutgar.ludumdare.state;

import org.flixel.FlxG;
import org.flixel.FlxObject;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxText;

import com.badlogic.gdx.Gdx;

public class EndState extends FlxState {
	
	int selected = 0;
	private FlxText start;
	private FlxText quit;
	private FlxSprite fadeLayer;
	private float fadeAmount = 0;
	private boolean fading = false;
	

	@Override
	public void create() {
		
		FlxG.playMusic("knight.wav");
		
		FlxText title = new FlxText(0, 10, FlxG.width, "Amulet In Ten");
		title.setAlignment("center");
		title.setSize(16);
		add(title);
		
		FlxSprite amulet = new FlxSprite(FlxG.width/2 - 10, FlxG.height/2 - 10);
		amulet.loadGraphic("pack.atlas:amulet");
		add(amulet);
		
		FlxSprite knight = new FlxSprite(10, FlxG.height/2-10);
		knight.loadGraphic("pack.atlas:knight", false, true);
		add(knight);
		
		FlxSprite thief = new FlxSprite(FlxG.width - 30, FlxG.height/2 - 10);
		thief.loadGraphic("pack.atlas:thief", false, true);
		thief.setFacing(FlxObject.LEFT);
		add(thief);
		
		FlxSprite monster1 = new FlxSprite(FlxG.width/2 - 30, FlxG.height/2 -10);
		monster1.loadGraphic("pack.atlas:troll");
		add(monster1);
		
		FlxSprite monster2 = new FlxSprite(FlxG.width/2 + 10, FlxG.height/2 -10);
		monster2.loadGraphic("pack.atlas:beholder");
		add(monster2);
		
		start = new FlxText(0, FlxG.height/2 - FlxG.height/4, FlxG.width, "Thanks for playing Amulet In Ten!\nCreated in 48 hours for Ludum Dare 27.");
		start.setAlignment("center");
		add(start);
		quit = new FlxText(0, FlxG.height/2 + FlxG.height/8, FlxG.width, "Press R to return to the Main Menu, or any other key to Exit.");
		quit.setAlignment("center");
		add(quit);
		
		FlxText credit = new FlxText(0, FlxG.height - 40, FlxG.width, "Cut Garnet Games\nwww.cutgar.net/games");
		credit.setAlignment("center");
		credit.setSize(8);
		credit.setColor(0xffaa0000);
		add(credit);
		
		fadeLayer = new FlxSprite(0, 0);
		fadeLayer.makeGraphic(FlxG.width, FlxG.height, 0xff000000);
		fadeLayer.setAlpha(0);
		add(fadeLayer);
	}
	
	public void update(){
		super.update();
		
		if(FlxG.keys.justPressed("R")){
			FlxG.switchState(new MenuState());
		}
		else if(FlxG.keys.any()){
			Gdx.app.exit();
		}
	}

	private void fadeInToGameStart() {
		//Might need more options later
		fading = true;
	}

	private void updateSelected() {
		start.setText("New Game");
		quit.setText("Quit");
		switch(selected){
		case 0: start.setText("> New Game <"); break;
		case 1: quit.setText("> Quit <"); break;
		}
	}

}
