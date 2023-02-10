package com.temperatures.cargo;

import java.util.Objects;

public class FileRecord {
    public FileRecord(String fileName, String fileNameWithPath) {
        this.fileName = fileName;
        this.fileNameWithPath = fileNameWithPath;
    }

    public FileRecord() {
    }

    @Override
    public String toString() {
        return "FileRecord{" +
                "fileName='" + fileName + '\'' +
                ", fileNameWithPath='" + fileNameWithPath + '\'' +
                '}';
    }

    String fileName;
    String fileNameWithPath;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileRecord that = (FileRecord) o;
        return Objects.equals(getFileName(), that.getFileName()) && Objects.equals(getFileNameWithPath(), that.getFileNameWithPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFileName(), getFileNameWithPath());
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileNameWithPath() {
        return fileNameWithPath;
    }

    public void setFileNameWithPath(String fileNameWithPath) {
        this.fileNameWithPath = fileNameWithPath;
    }
}
