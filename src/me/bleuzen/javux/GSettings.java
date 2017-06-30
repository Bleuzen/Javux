package me.bleuzen.javux;

public class GSettings {

	/**
	 * @param schema Schema
	 * @param key Key
	 * @param value Value
	 * @return Whether there was no error
	 * */
	public static boolean set(String schema, String key, String value) {
		return Controller.execSuccess("gsettings set " + schema + " " + key + " " + value);
	}

	/**
	 * @param schema Schema
	 * @param key Key
	 * @return The read value (or null if there was an error)
	 * */
	public static String get(String schema, String key) {
		String value;
		try {
			value = Controller.execProcessResult(false, "gsettings get " + schema + " " + key).getOutput();
		} catch (NullPointerException e) {
			value = null;
		}
		return value;
	}

	/**
	 * @param schema Schema
	 * @param key Key
	 * @return Whether there was no error
	 * */
	public static boolean reset(String schema, String key) {
		return Controller.execSuccess("gsettings reset " + schema + " " + key);
	}

	/**
	 * @param schema Schema
	 * @return Whether there was no error
	 * */
	public static boolean resetRecursively(String schema) {
		return Controller.execSuccess("gsettings reset-recursively " + schema);
	}

}
