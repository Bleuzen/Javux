package me.bleuzen.javux;

import java.util.ArrayList;
import java.util.List;

public class RandR {

	public static final String ROTATION_LEFT = "left", ROTATION_RIGHT = "right", ROTATION_NORMAL = "normal", ROTATION_INVERTED = "inverted";

	/**
	 * @return A list of all connected displays
	 * */
	public static List<String> getConnectedDisplays() {
		List<String> out = new ArrayList<>();
		String[] lines = getQueryInfoLines();
		for(int i = 0; i < lines.length; i++) {
			String[] inLine = lines[i].split(" ");
			if(inLine[1].equals("connected")) {
				out.add(inLine[0]);
			}
		}
		return out;
	}

	/**
	 * @param display The name of the display
	 * @return The resolution of the specified display
	 * */
	public static String getResolution(String display) {
		String[] lines = getQueryInfoLines();
		for(int i = 0; i < lines.length; i++) {
			String[] inLine = lines[i].replace(" primary", "").replace("+", "#").split(" ");
			if(inLine[0].equals(display) && inLine[1].equals("connected")) {
				return inLine[2].split("#")[0];
			}
		}
		return null;
	}

	private static String[] getQueryInfoLines() {
		return Controller.execProcessResult(false, "xrandr --query").getOutput().split(System.lineSeparator());
	}

	/**
	 * @return The name of the main display
	 * */
	public static String getPrimaryDisplay() {
		String[] lines = getQueryInfoLines();
		for(int i = 0; i < lines.length; i++) {
			String[] inLine = lines[i].split(" ");
			if(inLine[2].equals("primary")) {
				return inLine[0];
			}
		}
		return null;
	}

	/**
	 * @param display The name of the screen
	 * @param width The new width
	 * @param height The new height
	 * @return Whether there was no error
	 * */
	public static boolean changeResolution(String display, int width, int height) {
		return Controller.execSuccess("xrandr --output " + display + " --mode " + width + "x" + height);
	}

	/**
	 * @param display The name of the screen
	 * @param refreshRate The new refresh rate
	 * @return Whether there was no error
	 * */
	public static boolean changeRefreshRate(String display, int refreshRate) {
		return Controller.execSuccess("xrandr --output " + display + " --mode " + getResolution(display) + " --rate " + refreshRate);
	}

	/**
	 * @param display The name of the screen
	 * @return Whether there was no error
	 * */
	public static boolean setAsPrimary(String display) {
		return Controller.execSuccess("xrandr --output " + display + " --primary");
	}

	/**
	 * @param display The name of the screen
	 * @param rotation Rotation
	 * @return Whether there was no error
	 * */
	public static boolean rotate(String display, String rotation) {
		return Controller.execSuccess("xrandr --output " + display + " --rotate " + rotation + " --orientation " + rotation);
	}

	/**
	 * @param display The name of the screen
	 * @param width Width
	 * @param height Height
	 * @return Whether there was no error
	 * */
	public static boolean panning(String display, int width, int height) {
		return Controller.execSuccess("xrandr --output " + display + " --panning " + width + "x" + height);
	}

	/**
	 * @param display The name of the screen
	 * @return Whether there was no error
	 * */
	public static boolean off(String display) {
		return Controller.execSuccess("xrandr --output " + display + " --off");
	}

	/**
	 * @param display The name of the screen
	 * @return Whether there was no error
	 * */
	public static boolean auto(String display) {
		return Controller.execSuccess("xrandr --output " + display + " --auto");
	}

	/**
	 * @param display The name of the screen
	 * @param x +X
	 * @param y +Y
	 * @return Whether there was no error
	 * */
	public static boolean position(String display, int x, int y) {
		return Controller.execSuccess("xrandr --output " + display + " --pos " + x + "x" + y);
	}

	/**
	 * @param display1 The name of the screen to be moved
	 * @param display2 The name of the second screen
	 * @return Whether there was no error
	 * */
	public static boolean moveLeftOf(String display1, String display2) {
		return Controller.execSuccess("xrandr --output " + display1 + " --left-of " + display2);
	}

	/**
	 * @param display1 The name of the screen to be moved
	 * @param display2 The name of the second screen
	 * @return Whether there was no error
	 * */
	public static boolean moveRightOf(String display1, String display2) {
		return Controller.execSuccess("xrandr --output " + display1 + " --right-of " + display2);
	}

	/**
	 * @param display1 The name of the screen to be moved
	 * @param display2 The name of the second screen
	 * @return Whether there was no error
	 * */
	public static boolean moveAbove(String display1, String display2) {
		return Controller.execSuccess("xrandr --output " + display1 + " --above " + display2);
	}

	/**
	 * @param display1 The name of the screen to be moved
	 * @param display2 The name of the second screen
	 * @return Whether there was no error
	 * */
	public static boolean moveBelow(String display1, String display2) {
		return Controller.execSuccess("xrandr --output " + display1 + " --below " + display2);
	}

	/**
	 * @param display The name of the screen to be moved
	 * @param scaleX Scaling of X
	 * @param scaleY Scaling of Y
	 * @return Whether there was no error
	 * */
	public static boolean scale(String display, double scaleX, double scaleY) {
		return Controller.execSuccess("xrandr --output " + display + " --scale " + scaleX + "x" + scaleY);
	}

	/**
	 * @param display The name of the screen
	 * @param d The new brightness (Default: 1.0)
	 * @return Whether there was no error
	 * */
	public static boolean setBrightness(String display, double d) {
		return Controller.execSuccess("xrandr --output " + display + " --brightness " + d);
	}

	/**
	 * @param display The name of the screen
	 * @param gamma The new gamma (Default: 1.0)
	 * @return Whether there was no error
	 * */
	public static boolean setGamma(String display, double gamma) {
		return setGamma(display, gamma, gamma, gamma);
	}

	/**
	 * @param display The name of the screen
	 * @param red Red (Default: 1.0)
	 * @param green Green (Default: 1.0)
	 * @param blue Blue (Default: 1.0)
	 * @return Whether there was no error
	 * */
	public static boolean setGamma(String display, double red, double green, double blue) {
		return Controller.execSuccess("xrandr --output " + display + " --gamma " + red + ":" + green + ":" + blue);
	}

}
