package me.bleuzen.javux;

public class Javux {

	static final String PROJECT_NAME = "Javux";
	static final String PROJECT_VERSION = "0.1";
	static final String PROJECT_AUTHOR = "Bleuzen <supgesu@gmail.com>";

	static final boolean DEBUG = false;

	public static final boolean isLinux = System.getProperty("os.name").toLowerCase().equals("linux");

	public static void printAbout() {
		System.out.println(PROJECT_NAME + " v" + PROJECT_VERSION + " by " + PROJECT_AUTHOR);
	}

	public static String getKernelRelease() {
		return Controller.execProcessResult(false, "uname --kernel-release").getOutput();
	}

	public static String getDistributionID() {
		return Controller.execProcessResult(false, "lsb_release --short --id").getOutput();
	}

	public static String getDistributionDescription() {
		return Controller.execProcessResult(false, "lsb_release --short --description").getOutput();
	}

	public static String getDistributionRelease() {
		return Controller.execProcessResult(false, "lsb_release --short --release").getOutput();
	}

	public static String getDistributionCodename() {
		return Controller.execProcessResult(false, "lsb_release --short --codename").getOutput();
	}


}
