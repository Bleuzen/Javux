package me.bleuzen.javux;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Controller {

	public static boolean execSuccess(String command) {
		try {
			Process process = new ProcessBuilder("sh", "-c", command).start();
			return process.waitFor() == 0;
		} catch (Exception e) {
			Log.printException(e);
			return false;
		}
	}

	public static ProcessResult execProcessResult(boolean redirectErrorStream, String command) {
		try {
			ProcessBuilder builder = new ProcessBuilder("sh", "-c", command);
			builder.redirectErrorStream(redirectErrorStream);
			Process process = builder.start();

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			String output = "";
			while((line = bufferedReader.readLine()) != null) {
				output += (line + System.lineSeparator());
			}

			bufferedReader.close();

			if(output.isEmpty()) {
				return null;
			}

			output = output.substring(0, output.lastIndexOf(System.lineSeparator()));

			return new ProcessResult(process.waitFor(), output);
		} catch (Exception e) {
			Log.printException(e);
			return null;
		}
	}

}
