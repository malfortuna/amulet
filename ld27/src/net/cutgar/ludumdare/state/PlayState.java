package net.cutgar.ludumdare.state;

import java.util.Random;

import net.cutgar.ludumdare.Util;
import net.cutgar.ludumdare.model.Amulet;
import net.cutgar.ludumdare.model.Level;
import net.cutgar.ludumdare.model.LevelDB;
import net.cutgar.ludumdare.model.Monster;
import net.cutgar.ludumdare.model.Player;

import org.flixel.FlxG;
import org.flixel.FlxSound;
import org.flixel.FlxSprite;
import org.flixel.FlxState;
import org.flixel.FlxText;

public class PlayState extends FlxState
{
	public enum MOVETYPE {BLOCKED, CLEAR, AMULET, MONSTER, PLAYER; int x = 0; int y = 0; FlxSprite target;};
	
	public static int RES = 20;
	
	public boolean levelStarted = false;
	public boolean fading_out = false;
	public boolean fading_in = false;
	public float fadeAmount = 1.0f;
	
	public int levelNum;
	public Level level;
	public Player player;
	public Amulet amulet;
	
	public int moves;
	public boolean turnEnded = false;
	public boolean levelComplete = false;
	public boolean gameOver = false;
	private FlxText moveCounter;

	private FlxText intro_message;
	private FlxSprite intro_icon;
	private FlxSprite intro_backdrop;
	
	public Random r = new Random();

	private FlxText healthText;
	private FlxText damageText;
	private FlxText targetText;
	private FlxText infoText;
	
	private FlxSprite selector;
	private FlxSprite healthIcon;
	private FlxSprite damageIcon;
	
	//For debug
	public PlayState(){
		levelNum = 14;
	}
	
	public PlayState(int levelNumber){
		levelNum = levelNumber;
	}
	
	@Override
	public void create()
	{
		FlxG.setBgColor(0xff000000);
		this.level = LevelDB.getLevel(levelNum);
		
		addLevel();
		
		intro_backdrop = new FlxSprite(0, 0);
		intro_backdrop.makeGraphic(FlxG.width, FlxG.height, 0xff000000);
		add(intro_backdrop);
		
		intro_message = new FlxText(0, FlxG.height/2, FlxG.width, level.message+"\n\n\nPress a Key");
		intro_message.setAlignment("center");
		add(intro_message);
		
		intro_icon = new FlxSprite(FlxG.width/2 - level.iconoff, FlxG.height/2 - FlxG.height/4);
		intro_icon.loadGraphic(level.icon);
		add(intro_icon);
		
		selector = new FlxSprite(-20, -20);
		selector.loadGraphic("pack.atlas:selector");
		add(selector);
	}
	
	public void addLevel(){
		for(int i=0; i<level.map.length; i++){
			for(int j=0; j<level.map[0].length; j++){
				if(level.map[i][j] == 1){
					FlxSprite tile = new FlxSprite(j*20, i*20);
					tile.makeGraphic(20, 20, 0xff000000);
					//tile.loadGraphic("pack.atlas:bricktile");
					add(tile);
				}
				else if(level.map[i][j] == 2){
					FlxSprite tile = new FlxSprite(j*20, i*20);
					tile.loadGraphic("pack.atlas:lava");
					add(tile);
				}
				else{
					FlxSprite tile = new FlxSprite(j*20, i*20);
					//tile.makeGraphic(20, 20, 0xff000000);
					tile.loadGraphic("pack.atlas:floortile");
					add(tile);					
				}
			}
		}
		
		amulet = new Amulet(level.ax,level.ay,level.locked);
		add(amulet);
		
		player = new Player(level.px,level.py,level.startingClass);
		if(levelNum < 14){
			switch(level.startingClass){
			case 0: FlxG.playMusic("knight.wav"); break;
			case 1: FlxG.playMusic("thief.wav"); break;
			}
		}
		else{
			FlxG.playMusic("maintheme.wav");
		}
		add(player);
		
		for(Monster m : level.monsters){
			add(m);
		}
		
		moveCounter = new FlxText(0,  0,  FlxG.width, "Moves: 0");
		add(moveCounter);
		
		healthIcon = new FlxSprite(10, 215);
		healthIcon.loadGraphic("pack.atlas:health");
		add(healthIcon);
		
		healthText = new FlxText(10, 200, FlxG.width/2, "2 HP");
		add(healthText);
		
		damageIcon = new FlxSprite(170, 215);
		damageIcon.loadGraphic("pack.atlas:damage");
		add(damageIcon);
		
		damageText = new FlxText(165, 200, 30, "1 DMG");
		damageText.setAlignment("center");
		add(damageText);
		
		targetText = new FlxText(0, 200, FlxG.width, player.name);
		targetText.setAlignment("center");
		add(targetText);
		
		infoText = new FlxText(30, 210, FlxG.width-60, player.description);
		infoText.setAlignment("center");
		add(infoText);
	}
	
	public void update(){
		super.update();
		
		if(levelNum > 9 && FlxG.keys.justPressed("TAB")){
			player.nextClass();
		}
		
		if(!levelComplete && FlxG.keys.R){
			FlxG.switchState(new PlayState(levelNum));
		}
		if(!levelStarted){
			if(FlxG.keys.any() && (!fading_out && !fading_in)){
				fading_out = true;
			}
			if(fading_out){
				fadeAmount -= FlxG.elapsed;
				intro_icon.setAlpha(fadeAmount);
				intro_message.setAlpha(fadeAmount);
				if(fadeAmount < 0){
					intro_icon.kill();
					intro_message.kill();
					//fading_out = false;
					fading_in = true;
					fadeAmount = 1.0f;
				}
			}
			if(fading_in){
				fadeAmount -= FlxG.elapsed;
				intro_backdrop.setAlpha(fadeAmount);
				if(fadeAmount < 0){
					levelStarted = true;
					fading_in = false;
				}
			}
			return;
		}
		if(!levelComplete && !gameOver){
			turnEnded = false;
			if(FlxG.keys.justPressed("RIGHT")){
				if(player.playerClass == 1 && FlxG.keys.pressed("SHIFT"))
					processPlayerMove(move(player, 2, 0));
				else
					processPlayerMove(move(player, 1, 0));
			}
			else if(FlxG.keys.justPressed("LEFT")){
				if(player.playerClass == 1 && FlxG.keys.pressed("SHIFT"))
					processPlayerMove(move(player, -2, 0));
				else
					processPlayerMove(move(player, -1, 0));
			}
			else if(FlxG.keys.justPressed("UP")){
				if(player.playerClass == 1 && FlxG.keys.pressed("SHIFT"))
					processPlayerMove(move(player, 0, -2));
				else
					processPlayerMove(move(player, 0, -1));
			}
			else if(FlxG.keys.justPressed("DOWN")){
				if(player.playerClass == 1 && FlxG.keys.pressed("SHIFT"))
					processPlayerMove(move(player, 0, 2));
				else
					processPlayerMove(move(player, 0, 1));
			}
			
			/*float cx = (player.x + RES/2); float dx = FlxG.mouse.x - cx;
			float cy = (player.y + RES/2); float dy = FlxG.mouse.y - cy;
			double dist = Math.sqrt((dx*dx)+(dy*dy));
			if(dist < 3*RES/2 || 
					(player.playerClass == 1 && FlxG.keys.SHIFT && dist < 5*RES/2 && ((int) (FlxG.mouse.x-player.x)/RES == 0 || (int) (FlxG.mouse.y-player.y)/RES == 0))){
				int tx = (int) (FlxG.mouse.x-player.x)/RES;
				int ty = (int) (FlxG.mouse.y-player.y)/RES;
				if(FlxG.mouse.justPressed()){
					processPlayerMove(move(player, tx, ty));
				}
				else{
					if((tx != 0 || ty != 0) && checkmove((int)(FlxG.mouse.x/RES), (int)(FlxG.mouse.y/RES)) != MOVETYPE.BLOCKED){
						selector.x = ((int)(FlxG.mouse.x/RES))*RES;
						selector.y = ((int)(FlxG.mouse.y/RES))*RES;
					}
				}
			}
			else{
				selector.x = -20;
				selector.y = -20;
			}*/
			
		}
		if(!gameOver && turnEnded){
			selector.x = -20;
			selector.y = -20;
			moves++;
			moveCounter.setText("Moves: "+moves);
			
			if(moves == 10 && !levelComplete){
				gameOver("Your Ten Moves Are Up!\nThere must be a quicker way...\n\nPress R to Retry");
				return;
			}
			
			if(levelComplete){
				
			}
			else{
				for(Monster m : level.monsters){
					doMonsterTurn(m);
				}
			}
			turnEnded = false;
		}
		if(levelComplete){
			if(FlxG.keys.any() && !fading_out){
				fading_out = true;
				fadeAmount = 0f;
			}
			if(fading_out){
				fadeAmount += FlxG.elapsed;
				intro_backdrop.setAlpha(fadeAmount);
				if(fadeAmount >= 2.0f){
					if(level.after != null){
						FlxG.switchState(level.after);
					}
					else{
						FlxG.switchState(new PlayState(levelNum+1));
					}
				}
			}
		}
		float mx = FlxG.mouse.x; float my = FlxG.mouse.y; boolean hovering = false;
		for(Monster m : level.monsters){
			if(m.x < mx && m.x+RES > mx && m.y < my && m.y+RES > my){
				healthText.setText(((int)m.hp)+" HP");
				damageText.setText(((int)m.dmg)+" DMG");
				targetText.setText(m.name);
				infoText.setText(m.description);
				hovering = true;
				break;
			}
		}
		if(!hovering){
			healthText.setText(((int)player.hp)+" HP");
			damageText.setText(((int)player.dmg)+" DMG");
			targetText.setText(player.name);
			infoText.setText(player.description);
		}
	}
	
	private void doMonsterTurn(Monster m) {
		if(!m.moves)
			return;
		if(clearLineOfSightFromTo((int)m.x/RES, (int)m.y/RES, (int)player.x/RES, (int)player.y/RES)){
			moveTowards(m, player);
		}
		else{ //Move randomly
			/*boolean moved = false; int dx = 0; int dy = 0; MOVETYPE res;
			while(!moved){
				dx = (r.nextInt(3)-1);
				dy = (r.nextInt(3)-1);
				res = move(m, dx, dy);
				if(res == MOVETYPE.CLEAR || res == MOVETYPE.AMULET){
					moved = true;
				}
			}*/
		}
	}

	private void moveTowards(Monster s1, FlxSprite s2) {
		double currentDist = Util.distBetween(s1.x, s1.y, s2.x, s2.y);
		int bx = (int)s1.x/RES, by = (int)s1.y/RES;
		if(currentDist <= 3*RES/2){
			player.hp -= s1.dmg;
			healthIcon.flicker();
			if(player.hp <= 0){
				player.setAlpha(0.5f);
				player.flicker(2.0f);
				gameOver("You were killed by a "+s1.name+"!\n\nR to Retry");
			}
		}
		for(int i=-1; i<2; i++){
			for(int j=-1; j<2; j++){
				int dx = (int)(s1.x/RES) + i; int dy = (int)(s1.y/RES)+j;
				if(checkmove(dx, dy) == MOVETYPE.CLEAR){
					double newDist = Util.distBetween(dx*RES, dy*RES, s2.x, s2.y);
					if(newDist < currentDist){
						currentDist = newDist;
						bx = dx; by = dy;
					}
				}
			}
		}
		s1.x = bx*RES; s1.y = by*RES;
	}

	private boolean clearLineOfSightFromTo(int x1, int y1, int x2, int y2) {
		if(x1 == x2){
			for(int i=Math.min(y1,y2); i<Math.max(y1,y2); i++){
				if(level.map[i][x1] == 1){
					return false;
				}
			}
			return true;
		}
		else if(y1 == y2){
			for(int i=Math.min(x1,x2); i<Math.max(x1,x2); i++){
				if(level.map[y1][i] == 1){
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private void processPlayerMove(MOVETYPE move) {
		switch(move){
			case CLEAR: turnEnded = true; break;
			case BLOCKED: break;
			case AMULET: if(!amulet.caged){turnEnded = true; completeLevel(); amulet.kill();} break;
			case MONSTER: attackMonster(move); turnEnded = true; break; //TODO
		}
	}
	
	private void attackMonster(MOVETYPE move) {
		Monster m = (Monster) move.target;
		m.hp -= player.dmg;
		damageIcon.flicker();
		m.flicker();
		if(m.hp <= 0){
			m.kill(); level.monsters.remove(m);
			if(level.monsters.size() == 0){
				amulet.caged = false;
				amulet.loadGraphic("pack.atlas:amulet");
			}
			player.x = move.x * RES;
			player.y = move.y * RES;
		}
		else{
			//player.hp -= m.dmg;
		}
	}

	private void gameOver(String msg) {
		FlxText gameOverText = new FlxText(0, 100, FlxG.width, msg);
		gameOverText.setAlignment("center");
		add(gameOverText);
		gameOver = true;
	}

	public void completeLevel(){
		FlxText levelCompleteText = new FlxText(0, 100, FlxG.width, "Level Complete!");
		levelCompleteText.setAlignment("center");
		add(levelCompleteText);
		levelComplete = true;
		FlxG.playMusic("amulet.wav");
	}

	public MOVETYPE checkmove(int nx, int ny){
		if(level.map[0].length <= nx || nx < 0 || ny < 0 || ny >= level.map.length){
			return MOVETYPE.BLOCKED;
		}
		if(player.x/RES == nx && player.y/RES == ny)
			return MOVETYPE.PLAYER;
		for(Monster m : level.monsters){
			if(m.x/RES == nx && m.y/RES == ny){
				MOVETYPE.MONSTER.x = nx;
				MOVETYPE.MONSTER.y = ny;
				MOVETYPE.MONSTER.target = m;
				return MOVETYPE.MONSTER;
			}
		}
		if(level.map[ny][nx] > 0)
			return MOVETYPE.BLOCKED;
		else{
			if(amulet.x/RES == nx && amulet.y/RES == ny && !amulet.caged){
				return MOVETYPE.AMULET;
			}
			else if(amulet.x/RES == nx && amulet.y/RES == ny && amulet.caged){
				return MOVETYPE.BLOCKED;
			}
			return MOVETYPE.CLEAR;
		}
	}
	
	public MOVETYPE move(FlxSprite s, int dx, int dy){
		int ny = (((int)s.y)/RES)+dy;
		int nx = (((int)s.x)/RES)+dx;
		
		if(level.map[0].length <= nx || nx < 0 || ny < 0 || ny >= level.map.length){
			return MOVETYPE.BLOCKED;
		}
		for(Monster m : level.monsters){
			if(m.x/RES == nx && m.y/RES == ny){
				MOVETYPE.MONSTER.x = nx;
				MOVETYPE.MONSTER.y = ny;
				MOVETYPE.MONSTER.target = m;
				return MOVETYPE.MONSTER;
			}
		}
		if(level.map[ny][nx] > 0)
			return MOVETYPE.BLOCKED;
		else{
			if(amulet.x/RES == nx && amulet.y/RES == ny && !amulet.caged){
				s.x += RES*dx;
				s.y += RES*dy;
				return MOVETYPE.AMULET;
			}
			else if(amulet.x/RES == nx && amulet.y/RES == ny && amulet.caged){
				return MOVETYPE.BLOCKED;
			}
			s.x += RES*dx;
			s.y += RES*dy;
			/*if(amulet.x/RES == nx && amulet.y/RES == ny){
				return MOVETYPE.AMULET;
			}*/
			return MOVETYPE.CLEAR;
		}
	}
	
}