package me.bleuzen.javux;

public class NotifySend {

	/**
	 * @param title Title
	 * @param message Message
	 * @return Whether there was no error
	 * */
	public static boolean showNotification(String title, String message) {
		return Controller.execSuccess("notify-send \"" + title + "\" \"" + message + "\"");
	}

}
