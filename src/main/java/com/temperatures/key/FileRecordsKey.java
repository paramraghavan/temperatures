package com.temperatures.key;


import java.util.Objects;

public class FileRecordsKey {
	String fileNameWithPath;

	@Override
	public String toString() {
		return "FileRecordsKey{" +
				"fileNameWithPath='" + fileNameWithPath + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		FileRecordsKey that = (FileRecordsKey) o;
		return Objects.equals(getFileNameWithPath(), that.getFileNameWithPath());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getFileNameWithPath());
	}

	public String getFileNameWithPath() {
		return fileNameWithPath;
	}

	public void setFileNameWithPath(String fileNameWithPath) {
		this.fileNameWithPath = fileNameWithPath;
	}

	public FileRecordsKey(String fileNameWithPath) {
		this.fileNameWithPath = fileNameWithPath;
	}

	public FileRecordsKey() {
	
	}

// Implement hashcode() and equals()


}
