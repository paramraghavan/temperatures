package com.temperatures.state;

import java.io.Serializable;
import java.util.HashMap;

public class AggregatorProcessKeyState implements Serializable {
	public AggregatorProcessKeyState() {
	}

	private static final long serialVersionUID = 1L;

	private String key;

	@Override
	public String toString() {
		return "AggregatorProcessKeyState{" +
				"key='" + key + '\'' +
				", count=" + count +
				", avgTemp=" + avgTemp +
				'}';
	}

	private int count;
	private double avgTemp;

	public void setAvgTemp(double avgTemp) {
		this.avgTemp = avgTemp;
	}

	private Long timer = null;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Double getAvgTemp() {
		return avgTemp;
	}

	public void setAvgTemp(Double avgTemp) {
		this.avgTemp = avgTemp;
	}

	public Long getTimer() {
		return timer;
	}

	public void setTimer(Long timer) {
		this.timer = timer;
	}
}
