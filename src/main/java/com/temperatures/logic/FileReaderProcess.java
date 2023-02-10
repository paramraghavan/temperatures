package com.temperatures.logic;

// Begin imports

import com.temperatures.cargo.FileRecord;
import com.temperatures.cargo.LineOfText;
import com.temperatures.cargo.ParsedRecord;
import com.temperatures.cargo.Result;
import com.temperatures.key.FileRecordsKey;
import com.temperatures.key.ParsedRecordsKey;
import com.temperatures.state.AggregatorProcessKeyState;
import com.temperatures.state.FileProcessKeyState;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;

// End imports

public class FileReaderProcess extends KeyedProcessFunction<FileRecordsKey, FileRecord, LineOfText>  {

// Begin declarations

	private static final Logger LOG = LoggerFactory.getLogger(FileReaderProcess.class);

	private static final long serialVersionUID = 1L;

	private ValueStateDescriptor<FileProcessKeyState> stateDescriptor;
	private ValueState<FileProcessKeyState> 			state;
// End declarations

	public FileReaderProcess() {
		super();
	}

	@Override
	public void open(Configuration config) throws Exception {

		// Begin open logic

		super.open(config);
		
		ParameterTool parameters = (ParameterTool) getRuntimeContext().getExecutionConfig().getGlobalJobParameters();
		
//		int max = parameters.getInt("Aggregator.max", 30);


		stateDescriptor = new ValueStateDescriptor<FileProcessKeyState>(
				"FileProcessKeyState",
				TypeInformation.of(FileProcessKeyState.class));
		state = getRuntimeContext().getState(stateDescriptor);

		// End open logic
		
	}


	@Override
	public void processElement(FileRecord value,
							   KeyedProcessFunction<FileRecordsKey, FileRecord, LineOfText>.Context context,
							   Collector<LineOfText> out) throws Exception {
		// Emit objects using:
		//
		//     collector.collect( obj );

		// Begin process logic
		FileReader fileReader = null;
		BufferedReader buffer = null;
		String filePathWithName = value.getFileNameWithPath();

		FileProcessKeyState item = state.value();
		if (item == null) {
			item = new FileProcessKeyState();
			state.update(item);
		} else {
			System.out.println("item key: " + item.getKey() + " item linenumber : " + item.getLineNumber());
		}
		item.setKey(filePathWithName);
		fileReader = new FileReader(filePathWithName);
		buffer = new BufferedReader(fileReader);
		int lineNumber =0;
		int itemLineNumber = item.getLineNumber();
		while (true) {
			//TODO ask Chris purpose of this sync block
			// Read a line from the file and emit it using an instance of LineOfText
			String line;
			while ((line = buffer.readLine()) != null) {
				lineNumber++;
				if (itemLineNumber > lineNumber) {
					System.out.println("Skipping processed line times. itemLineNumber : " + itemLineNumber + " lineNumber : " + lineNumber);
					continue;
				}
				if (!line.contains("Region")) {
					System.out.println(this.getClass().getName() + ": " + line);
					out.collect(new LineOfText(line));
				}
				item.setLineNumber(lineNumber);
			}
			if (line == null) {
				state.clear();
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
		// End process logic
		
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
