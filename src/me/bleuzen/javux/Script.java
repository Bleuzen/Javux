package me.bleuzen.javux;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

public class Script {

	private static final String PREFIX = Javux.PROJECT_NAME + "Script";

	private static ArrayList<Integer> ids = new ArrayList<>();

	private int id;
	private final String interpreter;
	private final File script;
	private Writer writer;

	public Script() {
		this(ScriptInterpreter.SH);
	}

	public Script(ScriptInterpreter interpreter) {
		this.interpreter = interpreter.name().toLowerCase();
		do {
			id = new Random().nextInt();
			if (id < 0) {
				id = -id;
			}
		} while(ids.contains(id));
		ids.add(id);
		script = new File("/tmp/" + PREFIX + id);
		if(script.exists()) {
			script.delete();
		}
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(script)));
		} catch (Exception e) {
			Log.printException(e);
		}
	}

	/**
	 * @return Whether script was successfully deleted
	 * */
	public boolean delete() {
		return ids.remove(Integer.valueOf(id)) && script.delete();
	}

	/**
	 * @param line Line to be added to the script
	 * @return Whether there was no error
	 * */
	public boolean addLine(String line) {
		try {
			writer.write(line + System.lineSeparator());
			writer.flush();
			return true;
		} catch (Exception e) {
			Log.printException(e);
			return false;
		}
	}

	/**
	 * @param textFile A text file or existing script
	 * @return Whether there was no error
	 * */
	public boolean addLines(File textFile) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(textFile));
			String line;
			while((line = reader.readLine()) != null) {
				writer.write(line + System.lineSeparator());
			}
			reader.close();
			writer.flush();
			return true;
		} catch (Exception e) {
			Log.printException(e);
			return false;
		}
	}

	/**
	 * @return ProcessResult of the script or null if script is empty or deleted
	 * <br> Use {@link me.bleuzen.javux.ProcessResult#getOutput() getOutput()} to get the output
	 * <br> Use {@link me.bleuzen.javux.ProcessResult#getExitCode() getExitCode()} to get the exit code
	 * */
	public ProcessResult exec() {
		return exec(false);
	}

	/**
	 * @param keep Keep the script file for multiple execution
	 * @return ProcessResult of the script or null if script is empty or deleted
	 * <br> Use {@link me.bleuzen.javux.ProcessResult#getOutput() getOutput()} to get the output
	 * <br> Use {@link me.bleuzen.javux.ProcessResult#getExitCode() getExitCode()} to get the exit code
	 * */
	public ProcessResult exec(boolean keep) {
		if(!script.exists()) {
			return null;
		}
		try {
			writer.close();
		} catch (Exception e) {}
		ProcessResult result = Controller.execProcessResult(true, interpreter + " " + script.getAbsolutePath());
		if(!keep) {
			delete();
		}
		return result;
	}

}
