package com.temperatures.key;

// Begin imports

import com.temperatures.cargo.FileRecord;
import com.temperatures.cargo.ParsedRecord;
import com.temperatures.helper.Helper;
import org.apache.flink.api.java.functions.KeySelector;

// End imports

public class FileRecordsKeySelector implements KeySelector<FileRecord, FileRecordsKey> {

	// Begin declarations

	private static final long serialVersionUID = 1L;

	// End declarations

	@Override
	public FileRecordsKey getKey(FileRecord value) throws Exception {

	// Begin selector logic

		// Derive and instance of ParsedRecordsKey as the key from value

		return Helper.parseRecordKey(value);
		
	// End selector logic
	
	}

}
