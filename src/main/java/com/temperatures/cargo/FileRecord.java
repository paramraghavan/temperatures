package com.temperatures.cargo;

import java.util.Objects;
import java.util.UUID;

public class FileRecord {
    public FileRecord(String fileName, String fileNameWithPath) {
        this.fileName = fileName;
        this.fileNameWithPath = fileNameWithPath;
    }

    public FileRecord(boolean empty) {
        this.empty = empty;
        String value= UUID.randomUUID() + "";
        this.fileNameWithPath = value;
        this.fileName = value;
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

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    boolean empty = false;

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
