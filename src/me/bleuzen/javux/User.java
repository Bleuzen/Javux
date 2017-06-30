package me.bleuzen.javux;

public class User {

	/**
	 * @return The name of the current user
	 * */
	public static String getName() {
		return System.getenv("USER");
	}

	/**
	 * @return A list of the groups the user is in
	 * */
	public static String[] getGroups() {
		return Controller.execProcessResult(false, "groups").getOutput().split(" ");
	}

	/**
	 * @return The home directory of the user
	 * */
	public static String getHome() {
		return System.getenv("HOME");
	}

	/**
	 * @return Whether user is root
	 * */
	public static boolean isRoot() {
		return Controller.execProcessResult(false, "whoami").getOutput().equals("root");
	}

}
