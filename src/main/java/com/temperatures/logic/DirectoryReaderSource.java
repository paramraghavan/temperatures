package com.temperatures.logic;

// Begin imports

import com.temperatures.cargo.FileRecord;
import com.temperatures.cargo.LineOfText;
import com.temperatures.helper.DirectoryWatcher;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;

// End imports

public class DirectoryReaderSource extends RichParallelSourceFunction<FileRecord> implements ParallelSourceFunction<FileRecord>  {

// Begin declarations

	private static final Logger LOG = LoggerFactory.getLogger(DirectoryReaderSource.class);

	private static final long serialVersionUID = 1L;

	private boolean running = false;

	private String filePath = null;

// End declarations

	public DirectoryReaderSource(String filePath) throws Exception {
		super();
		this.filePath = filePath;
	}

	@Override
	public void cancel() {
		running = false;
	}

	@Override
	public void open(Configuration config) throws Exception {
		
		// Begin open logic

		super.open(config);
		
		ParameterTool parameters = (ParameterTool) getRuntimeContext().getExecutionConfig().getGlobalJobParameters();

//		int max = parameters.getInt("FileReader.max", 30);
		// End open logic
		
	}

	@Override
	public void run(SourceContext<FileRecord> context) throws Exception {
		DirectoryWatcher dw = new DirectoryWatcher(filePath, ".csv");
		running = true;
		while (running) {
			if (dw.hasNext()) {
				//TODO ask Chris purpose of this sync block
				synchronized (context.getCheckpointLock()) {
					String filePathWithName = dw.next();
					System.out.println(this.getClass().getName() + ": " + "filePathWithName: " + filePathWithName);
					context.collect(new FileRecord(filePathWithName, filePathWithName));
				}
			}
			// End run logic
		}
	}

	@Override
	public void close() throws Exception {
		super.close();
		// Begin close logic

		// End close logic
	}


	// Begin custom methods
	
	
	// End custom methods
	
}
