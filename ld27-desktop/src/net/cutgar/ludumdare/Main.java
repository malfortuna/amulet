package net.cutgar.ludumdare;

import org.flixel.FlxDesktopApplication;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;
import com.badlogic.gdx.tools.imagepacker.TexturePacker2.Settings;

public class Main {
	public static void main(String[] args) {
//		Settings settings = new Settings();
//		settings.maxWidth = 512;
//		settings.maxHeight = 512;
//		TexturePacker2.process(settings, "../ld27-android/raw",
//				"../ld27-android/assets", "pack");

		new FlxDesktopApplication(new LudumDare(), 400, 480);
	}
}
