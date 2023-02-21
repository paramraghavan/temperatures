package com.temperatures.logic;

// Begin imports

import com.temperatures.cargo.LineOfText;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// End imports

public class LineOfTextParserProcess extends ProcessFunction<String, LineOfText>  {

// Begin declarations

	private static final Logger LOG = LoggerFactory.getLogger(LineOfTextParserProcess.class);

	private static final long serialVersionUID = 1L;
// End declarations

	public LineOfTextParserProcess() {
		super();
	}
	
	@Override
	public void open(Configuration config) throws Exception {

		// Begin open logic

		super.open(config);
		
		ParameterTool parameters = (ParameterTool) getRuntimeContext().getExecutionConfig().getGlobalJobParameters();
		
//		int max = parameters.getInt("Parser.max", 30);


		// End open logic
		
	}


	@Override
	public void processElement(String value,
			ProcessFunction<String, LineOfText>.Context context, Collector<LineOfText> collector)
			throws Exception {

		// Emit objects using:
		//
		//     collector.collect( obj );

		// Begin process logic
		if (!value.contains("Region")) {
					System.out.println(this.getClass().getName() + ": " + value);
					collector.collect(new LineOfText(value));
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
