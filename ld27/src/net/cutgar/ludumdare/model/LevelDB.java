package net.cutgar.ludumdare.model;

import net.cutgar.ludumdare.state.ChapterState;
import net.cutgar.ludumdare.state.EndState;
import net.cutgar.ludumdare.state.PlayState;

public class LevelDB {

	public static int KNIGHT_START = 0;
	public static int THIEF_START = 5;
	public static int PARTY_START = 10;
	
	public static Level getLevel(int level){
		Level l = new Level();
		switch(level){
		case 0: levelShowMovement(l); break;
		case 1: levelCombatIntro(l); break;
		case 2: levelMonstersChaseOnSight(l); break;
		case 3: levelCombatChoices(l); break;
		case 4: levelLockedAmulets(l); break;
		
		case 5: levelThiefIntro(l); break;
		case 6: levelThiefDash(l); break;
		case 7: levelAvoidCombat(l); break;
		case 8: levelCombatOverwhelm(l); break;
		case 9: levelMonsterBlock(l); break;
		
		case 10: levelPartySwitch(l); break;
		case 11: levelCombatSwitch(l); break;
		case 12: levelDashToExit(l); break;
		case 13: levelDashAttack(l); break;
		case 14: levelEndGame(l); break;
		}
		return l;
	}

	private static void levelShowMovement(Level l) {
		l.map = new int[10][10];
		
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				
				{1,0,0,0,0, 0,0,0,0,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 1;
		l.py = 5;
		
		l.ax = 8;
		l.ay = 5;
		
		l.message = "Use the arrow keys to move.\n\nPress R to retry a level.";//, numpad, or mouse
		l.icon = "pack.atlas:amulet";
	}
	
	private static void levelLockedAmulets(Level l) {
		l.map = new int[10][10];
		
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				
				{1,0,0,0,0, 0,0,0,0,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 2;
		l.py = 5;
		
		l.ax = 1;
		l.ay = 5;
		l.locked = true;
		
		l.monsters.add(new Monster(5,5));
		
		l.message = "Locked amulets are released when all monsters are killed.";
		l.icon = "pack.atlas:amuletcage";
		l.after = new ChapterState("pack.atlas:knight", "Chapter 1 Complete!", new ChapterState("pack.atlas:thief", "Chapter 2\nThe Thief", new PlayState(THIEF_START)));
	}
	
	private static void levelMonstersChaseOnSight(Level l) {
		l.map = new int[10][10];
		
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,1,1,1, 1,1,1,0,1},	
				{1,1,1,1,1, 1,1,1,0,1},	
				{1,1,1,1,1, 1,1,1,0,1},	
				{1,0,0,0,0, 0,0,2,0,1},	
				
				{1,0,0,0,0, 0,0,2,0,1},	
				{1,1,1,1,1, 1,1,1,0,1},	
				{1,1,1,1,1, 1,1,1,0,1},	
				{1,1,1,1,1, 1,1,1,0,1},	
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 8;
		l.py = 1;
		
		l.ax = 8;
		l.ay = 8;
		
		l.monsters.add(new Monster(1, 4));
		l.monsters.add(new Monster(1, 5));
		
		l.message = "Monsters chase on sight.\nYou can see across lava, but not move through it.";
		l.icon = "pack.atlas:beholder";
	}
	
	private static void levelCombatIntro(Level l) {
		l.map = new int[10][10];
		
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,0,0,0, 0,0,0,1,1},	
				{1,0,0,1,1, 1,0,0,0,1},	
				
				{1,0,0,1,1, 1,0,0,0,1},	
				{1,1,0,0,0, 0,0,0,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 1;
		l.py = 5;
		
		l.ax = 8;
		l.ay = 5;
		
		l.monsters.add(new Dummy(4, 3));
		l.monsters.add(new Dummy(4, 6));
		
		l.message = "Move onto monsters to attack them.\nMouse over things for information.";
		l.icon = "pack.atlas:dummy";
	}
	
	private static void levelThiefIntro(Level l) {
		l.map = new int[10][10];
		
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,0,0,0, 0,0,0,1,1},	
				{1,1,0,1,1, 1,1,0,1,1},	
				{1,1,0,0,0, 0,0,0,1,1},	
				{1,0,0,1,1, 1,1,0,0,1},	
				
				{1,0,0,1,1, 1,1,0,0,1},	
				{1,1,0,0,0, 0,0,0,1,1},	
				{1,1,0,1,1, 1,1,0,1,1},	
				{1,1,0,0,0, 0,0,0,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 2;
		l.py = 1;
		l.startingClass = 1;
		
		l.ax = 7;
		l.ay = 8;
		
		l.message = "The Thief can dash across two tiles at once.\nHold Shift while you move.";
		l.icon = "pack.atlas:thief";
	}

	private static void levelCombatChoices(Level l) {
		l.map = new int[10][10];
		
		l.map = new int[][]{
				
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,0,0,0, 0,0,0,1,1},	
				
				{1,0,0,1,1, 1,1,0,0,1},	
				{1,1,0,0,0, 0,0,0,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 1;
		l.py = 4;
		l.startingClass = 0;
		
		l.ax = 8;
		l.ay = 4;
		
		l.monsters.add(new Troll(4, 3));
		l.monsters.add(new Monster(4, 5));
		
		l.message = "Some monsters are stronger than others. Mouse over to check.";
		l.icon = "pack.atlas:troll";
	}
	
	private static void levelCombatOverwhelm(Level l) {
		l.map = new int[10][10];
		
		l.map = new int[][]{
				
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,0,0,0, 1,1,1,1,1},	
				{1,1,0,0,0, 0,0,0,1,1},	
				
				{1,0,0,0,0, 0,0,0,0,1},	
				{1,1,0,0,0, 0,0,0,1,1},	
				{1,1,0,0,0, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 1;
		l.py = 4;
		l.startingClass = 1;
		
		l.ax = 8;
		l.ay = 4;
		l.locked = true;
		
		l.monsters.add(new Monster(2, 6));
		l.monsters.add(new Monster(4, 4));
		l.monsters.add(new Monster(2, 2));
		
		l.message = "When you attack, you deal damage before your target. Don't get surrounded!";
		l.icon = "pack.atlas:beholder";
		//l.after = new ChapterState("pack.atlas:knight", "Chapter 1 Complete!", new ChapterState("pack.atlas:thief", "Chapter 2\nThe Thief", new PlayState(THIEF_START)));
	}
	
	private static void levelThiefDash(Level l){
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},	//0
				{1,1,1,1,0, 1,1,1,1,1},	
				{1,1,1,0,0, 1,1,1,1,1},	
				{1,1,1,1,0, 1,1,1,1,1},	//3
				
				{1,1,1,1,0, 1,1,1,1,1},	
				{1,1,1,1,0, 0,1,1,1,1},	//5
				{1,1,1,1,0, 1,1,1,1,1},	
				{1,1,1,1,0, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1}, //8
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 4;
		l.py = 1;
		l.startingClass = 1;
		
		l.ax = 4;
		l.ay = 7;
		l.monsters.add(new Troll(3, 2));
		l.monsters.add(new Troll(5, 5));
		
		l.message = "Most monsters can only see in straight lines. Use the Thief's two-tile dash to sneak past.";
		l.icon = "pack.atlas:troll";
	}
	
	private static void levelAvoidCombat(Level l){
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,0,0,0,0, 0,0,0,1,1},	
				{1,0,1,0,1, 0,1,0,1,1},	
				{1,0,0,0,0, 0,0,0,1,1},	
				
				{1,0,1,0,0, 0,1,0,1,1},	
				{1,0,0,0,0, 0,0,0,1,1},	
				{1,0,1,0,1, 0,1,0,1,1},	
				{1,0,0,0,0, 0,0,0,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 1;
		l.py = 1;
		l.startingClass = 1;
		
		l.ax = 4;
		l.ay = 4;
		l.monsters.add(new Troll(3, 3));
		l.monsters.add(new Troll(5, 3));
		l.monsters.add(new Troll(5, 5));
		l.monsters.add(new Troll(3, 5));
		
		l.message = "Sometimes it's better \nto avoid combat...";
		l.icon = "pack.atlas:thief";
	}
	
	private static void levelMonsterBlock(Level l){
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 0,1,1,1,1},	
				{1,1,1,1,1, 0,1,1,1,1},	
				{1,1,1,0,0, 0,1,1,1,1},	
				
				{1,1,1,0,0, 2,1,1,1,1},	
				{1,1,1,0,0, 0,1,1,1,1},	
				{1,1,1,0,0, 0,1,1,1,1},	
				{1,1,1,0,0, 0,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 4;
		l.py = 6;
		l.startingClass = 1;
		
		l.ax = 5;
		l.ay = 1;
		l.monsters.add(new Troll(3, 3));
		l.monsters.add(new Monster(5, 2));
		
		l.message = "The Thief can use her dash to leap across lava.";
		l.icon = "pack.atlas:lava";
		l.after = new ChapterState("pack.atlas:knight", "Chapter 2 Complete!", new ChapterState("pack.atlas:party", "Chapter 3\nThe Party", new PlayState(PARTY_START), 20));
	}
	
	private static void levelPartySwitch(Level l){
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				
				{1,1,0,0,2, 0,0,0,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 2;
		l.py = 5;
		
		l.ax = 6;
		l.ay = 5;
		
		l.message = "Press Tab to swap between party members. Swapping doesn't take up any moves.";
		l.icon = "pack.atlas:party";
		l.iconoff = 20;
	}
	
	private static void levelCombatSwitch(Level l){
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,0,0,0,0, 0,0,0,1,1},	
				{1,0,2,0,2, 0,2,0,1,1},	
				{1,0,0,0,0, 0,0,0,1,1},	
				
				{1,0,2,0,0, 0,2,0,1,1},	
				{1,0,0,0,0, 0,0,0,1,1},	
				{1,0,2,0,2, 0,2,0,1,1},	
				{1,0,0,0,0, 0,0,0,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 1;
		l.py = 1;
		l.startingClass = 0;
		
		l.ax = 4;
		l.ay = 4;
		l.locked = true;
		
		l.monsters.add(new Troll(3, 3));
		l.monsters.add(new Troll(5, 3));
		l.monsters.add(new Troll(5, 5));
		l.monsters.add(new Troll(3, 5));
		l.monsters.add(new Monster(4, 1));
		l.monsters.add(new Monster(1, 4));
		
		l.message = "The Knight has more health and does more damage than the Thief.\n\nThe Thief's Dash move can be used to escape fights.";
		l.icon = "pack.atlas:knight";
	}
	
	private static void levelDashToExit(Level l){
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,0,0,0,0, 0,0,0,0,1},	
				
				{1,0,2,0,2, 2,0,2,0,1},	
				{1,0,0,0,0, 0,0,0,0,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 1,1,1,1,1},
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 1;
		l.py = 4;
		l.startingClass = 1;
		
		l.ax = 8;
		l.ay = 4;
		l.locked = true;
		
		l.monsters.add(new Troll(3, 3));
		l.monsters.add(new Troll(3, 5));
		l.monsters.add(new Monster(5, 3));
		l.monsters.add(new Monster(5, 5));
		
		
		l.message = "Use the Thief's Dash to cover ground quickly. You only have ten moves!";
		l.icon = "pack.atlas:thief";
	}
	
	private static void levelDashAttack(Level l){
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,1,1, 0,1,1,1,1},	
				{1,1,1,0,0, 0,0,0,1,1},	
				{1,1,1,2,2, 2,2,2,1,1},	
				
				{1,1,1,0,0, 0,0,0,1,1},	
				{1,1,1,0,0, 0,0,0,1,1},	
				{1,1,1,2,2, 2,2,2,1,1},	
				{1,1,1,0,0, 0,0,0,1,1},	
				{1,1,1,1,1, 0,1,1,1,1},
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 5;
		l.py = 8;
		l.startingClass = 1;
		
		l.ax = 5;
		l.ay = 1;
		l.locked = true;
		
		l.monsters.add(new Troll(7, 5));
		l.monsters.add(new Troll(3, 5));
		l.monsters.add(new Monster(5,5));
		
		
		l.monsters.add(new Monster(3, 2));
		l.monsters.add(new Troll(5, 2));
		l.monsters.add(new Monster(7, 2));
		
		l.message = "The Thief's Dash can be used to attack from long distances.";
		l.icon = "pack.atlas:thief";
	}
	
	private static void levelEndGame(Level l){
		l.map = new int[][]{
				{1,1,1,1,1, 1,1,1,1,1},	
				{1,1,1,0,0, 0,0,0,1,1},	
				{1,1,1,0,0, 0,0,0,1,1},	
				{1,1,1,0,0, 0,0,0,1,1},
				{1,1,1,0,0, 0,0,0,1,1},	
			
				{1,1,1,0,0, 0,0,0,1,1},	
				{1,1,1,0,0, 0,0,0,1,1},	
				{1,1,1,0,0, 0,0,0,1,1},	
				{1,1,1,0,0, 0,0,0,1,1},
				{1,1,1,1,1, 1,1,1,1,1},
		};
		l.px = 5;
		l.py = 5;
		l.startingClass = 1;
		
		l.ax = 5;
		l.ay = 1;
		l.locked = true;
		
		l.monsters.add(new Monster(3,5));
		l.monsters.add(new Monster(7,5));
		l.monsters.add(new Monster(5,4));
		l.monsters.add(new Monster(5,6));
		
		l.monsters.add(new Troll(3, 3));
		l.monsters.add(new Troll(7, 3));
//		l.monsters.add(new Monster(4, 2));
//		l.monsters.add(new Monster(6, 2));
		
		l.message = "Good Luck!";
		l.icon = "pack.atlas:thief";
		l.after = new EndState();
	}
}
