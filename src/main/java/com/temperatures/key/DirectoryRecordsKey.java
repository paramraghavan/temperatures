package com.temperatures.key;


import java.util.Objects;

public class DirectoryRecordsKey {
	String fileNameWithPath;

	@Override
	public String toString() {
		return "DirectoryRecordsKey{" +
				"fileNameWithPath='" + fileNameWithPath + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DirectoryRecordsKey that = (DirectoryRecordsKey) o;
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

	public DirectoryRecordsKey(String fileNameWithPath) {
		this.fileNameWithPath = fileNameWithPath;
	}

	public DirectoryRecordsKey() {
	
	}

// Implement hashcode() and equals()


}
