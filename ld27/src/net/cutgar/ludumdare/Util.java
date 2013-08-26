package net.cutgar.ludumdare;

public class Util {
	
	public static double distBetween(float x1, float y1, float x2, float y2){
		float dx = x1-x2;
		float dy = y1-y2;
		double diff = Math.sqrt((dx*dx)+(dy*dy));
		return diff;
	}

}
