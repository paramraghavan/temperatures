package com.temperatures.logic;

// Begin imports

import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import com.temperatures.cargo.LineOfText;
import com.temperatures.state.FileReaderSourceCheckpointItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// End imports

public class FileReaderSource extends RichParallelSourceFunction<LineOfText> implements ParallelSourceFunction<LineOfText>  {

// Begin declarations

	private static final Logger LOG = LoggerFactory.getLogger(FileReaderSource.class);

	private static final long serialVersionUID = 1L;
	
	private boolean running = false;
	

// End declarations

	public FileReaderSource() throws Exception {
		super();
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
	public void run(SourceContext<LineOfText> context) throws Exception {

		// Begin run logic

		running = true;
		
		while (running) {
			
			synchronized (context.getCheckpointLock()) {
				
				// Read a line from the file and emit it using an instance of LineOfText
				
			}
			
		}
		
		// End run logic
		
	}

	@Override
	public void close() throws Exception {

		// Begin close logic

		super.close();

		// End close logic

	}


	// Begin custom methods
	
	
	// End custom methods
	
}
