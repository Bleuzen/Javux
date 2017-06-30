package me.bleuzen.javux;

class Log {

	static void err(String msg) {
		System.out.println("[" + Javux.PROJECT_NAME + "] [Error] " + msg);
	}

	static void out(String msg) {
		System.out.println("[" + Javux.PROJECT_NAME + "] [Info] " + msg);
	}

	static void printException(Exception e) {
		err(e.getMessage());
	}

	static void debug(String msg) {
		if(Javux.DEBUG) {
			System.out.println("[" + Javux.PROJECT_NAME + "] [Debug] [" + getCallerClassName() + "] " + msg);
		}
	}

	// source: http://stackoverflow.com/questions/11306811/how-to-get-the-caller-class-in-java
	private static String getCallerClassName() {
		StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
		for (int i = 1; i < stElements.length; i++) {
			StackTraceElement ste = stElements[i];
			if (!ste.getClassName().equals(Log.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0) {
				String className = ste.getClassName();
				return className.substring(className.lastIndexOf(".") + 1);
			}
		}
		return null;
	}

}
