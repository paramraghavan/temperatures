package com.temperatures.cargo;

public class Result {

	public Result() {
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

	public Result(int count, Double avgTemp, String key) {
		this.count = count;
		this.avgTemp = avgTemp;
		this.key = key;
	}

	public String key;
	public int count;
	public Double avgTemp;

	@Override
	public String toString() {
		return "Result{" +
				"key='" + key + '\'' +
				", count=" + count +
				", avgTemp=" + avgTemp +
				'}';
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
