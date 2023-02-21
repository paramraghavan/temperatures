package com.temperatures.key;

// Begin imports

import com.temperatures.cargo.FileRecord;
import com.temperatures.helper.Helper;
import org.apache.flink.api.java.functions.KeySelector;

// End imports

public class DirectoryRecordsKeySelector implements KeySelector<FileRecord, DirectoryRecordsKey> {

	// Begin declarations

	private static final long serialVersionUID = 1L;

	// End declarations

	@Override
	public DirectoryRecordsKey getKey(FileRecord value) throws Exception {

	// Begin selector logic

		// Derive and instance of ParsedRecordsKey as the key from value

		return Helper.parseRecordKey(value);
		
	// End selector logic
	
	}

}
