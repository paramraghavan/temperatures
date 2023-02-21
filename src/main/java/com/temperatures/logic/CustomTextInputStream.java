package com.temperatures.logic;


import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.core.fs.Path;

import java.io.IOException;

class CustomTextInputStream extends TextInputFormat {

    public CustomTextInputStream(Path filePath) {
        super(filePath);
    }

    public String readRecord(String reusable, byte[]bytes, int offset, int numBytes)throws IOException {
        String fileName= String.valueOf(this.filePath);
        //Add FileName to the record!
       return super.readRecord(reusable,bytes,offset,numBytes)+","+fileName;
    }
}