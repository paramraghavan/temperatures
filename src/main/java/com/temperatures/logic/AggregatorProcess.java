package com.temperatures.logic;

// Begin imports

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import com.temperatures.cargo.ParsedRecord;
import com.temperatures.cargo.Result;

import com.temperatures.key.ParsedRecordsKey;
import com.temperatures.state.AggregatorProcessKeyState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// End imports

public class AggregatorProcess extends KeyedProcessFunction<ParsedRecordsKey, ParsedRecord, Result>  {

// Begin declarations

	private static final Logger LOG = LoggerFactory.getLogger(AggregatorProcess.class);

	private static final long serialVersionUID = 1L;

	private ValueStateDescriptor<AggregatorProcessKeyState> stateDescriptor;
	private ValueState<AggregatorProcessKeyState> 			state;
// End declarations
	
	public AggregatorProcess() {
		super();
	}
	
	@Override
	public void open(Configuration config) throws Exception {

		// Begin open logic

		super.open(config);
		
		ParameterTool parameters = (ParameterTool) getRuntimeContext().getExecutionConfig().getGlobalJobParameters();
		
//		int max = parameters.getInt("Aggregator.max", 30);


		stateDescriptor = new ValueStateDescriptor<AggregatorProcessKeyState>(
				"AggregatorProcessKeyState", 
				TypeInformation.of(AggregatorProcessKeyState.class));
		state = getRuntimeContext().getState(stateDescriptor);

		// End open logic
		
	}


	@Override
	public void processElement(ParsedRecord value,
			KeyedProcessFunction<ParsedRecordsKey, ParsedRecord, Result>.Context context, Collector<Result> collector)
			throws Exception {

		// Emit objects using:
		//
		//     collector.collect( obj );

		// Begin process logic
		AggregatorProcessKeyState item = state.value();

		if (item == null) {
			Long timer = context.timerService().currentProcessingTime() + 10*1000L;
//			Long timer = System.currentTimeMillis() + 1*1000L;
			context.timerService().registerProcessingTimeTimer(timer);
			System.out.println("Timer Registered");
			item = new AggregatorProcessKeyState();
			item.setTimer(timer);
			item.setKey(value.getKey());
		} else {
			System.out.println("Timer  Not Registered");
		}
		item.setCount(item.getCount() + 1);
		double newSumOfAvgTemp = (item.getAvgTemp() + value.getAvgTemperature());
		item.setAvgTemp(newSumOfAvgTemp);
		System.out.println(this.getClass().getName() + ": " + item);
		state.update(item);
		//collector.collect(new Result(item.getCount(), item.getAvgTemp()/ item.getCount(), item.getKey()));
		// End process logic
		
	}

	@Override
	public void onTimer(long timestamp, KeyedProcessFunction<ParsedRecordsKey, ParsedRecord, Result>.OnTimerContext context, Collector<Result> out) throws Exception {
		super.onTimer(timestamp, context, out);
		AggregatorProcessKeyState item = state.value();
		if (item != null) {
			out.collect(new Result(item.getCount(), item.getAvgTemp() / item.getCount(), item.getKey()));
			System.out.println(this.getClass().getName() + ": " + "publish" );
			state.clear();
		}

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
