package com.temperatures.state;

import java.io.Serializable;
import java.util.Objects;

public class FileReaderSourceCheckpointItem implements Serializable {

	private static final long serialVersionUID = 1L;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Override
	public String toString() {
		return "FileReaderSourceCheckpointItem{" +
				"fileName='" + fileName + '\'' +
				", lineNumber=" + lineNumber +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FileReaderSourceCheckpointItem that = (FileReaderSourceCheckpointItem) o;
		return Objects.equals(getFileName(), that.getFileName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFileName());
	}

	String fileName;
	int lineNumber;

}
