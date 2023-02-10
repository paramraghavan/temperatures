package com.temperatures.state;

import java.io.Serializable;

public class FileProcessKeyState implements Serializable {
	public FileProcessKeyState() {
	}

	private static final long serialVersionUID = 1L;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Override
	public String toString() {
		return "FileProcessKeyState{" +
				"key='" + key + '\'' +
				", lineNumber=" + lineNumber +
				'}';
	}

	private String key;
	private int lineNumber;

}
