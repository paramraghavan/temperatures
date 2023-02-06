package com.temperatures.logic;

// Begin imports

import com.temperatures.helper.DirectoryWatcher;
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

import java.io.BufferedReader;
import java.io.FileReader;

// End imports

public class FileReaderSource extends RichParallelSourceFunction<LineOfText> implements ParallelSourceFunction<LineOfText>  {

// Begin declarations

	private static final Logger LOG = LoggerFactory.getLogger(FileReaderSource.class);

	private static final long serialVersionUID = 1L;
	
	private boolean running = false;

	private String filePath = null;

// End declarations

	public FileReaderSource(String filePath) throws Exception {
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
	public void run(SourceContext<LineOfText> context) throws Exception {

		// Begin run logic
		FileReader fileReader = null;
		BufferedReader buffer = null;

		DirectoryWatcher dw = new DirectoryWatcher(filePath, ".csv");

		running = true;
		while (running) {
			if( dw.hasNext()) {
				String filePathWithName = dw.next();
				fileReader = new FileReader(filePathWithName);
				buffer = new BufferedReader(fileReader);

				//TODO ask Chris purpose of this sync block
				synchronized (context.getCheckpointLock()) {
					// Read a line from the file and emit it using an instance of LineOfText
					String line;
					while ((line = buffer.readLine()) != null) {
						if (!line.contains("Region")) {
							LOG.debug(line);
							context.collect(new LineOfText(line));
						}
					}
					if (line == null) {
						// no more lines close file handles
						try {
							try {
								if (buffer != null) {
									buffer.close();
								}
								if (fileReader != null) {
									fileReader.close();
								}
							} catch (Throwable t) {
							} finally {
								fileReader = null;
								buffer = null;
							}
							Thread.sleep(1000);
						} catch (Exception ex) {
							;
						}
					}
				}
			}
		}
		// End run logic
		
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
