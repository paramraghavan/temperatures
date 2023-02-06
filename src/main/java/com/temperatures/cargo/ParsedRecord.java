package com.temperatures.cargo;

public class ParsedRecord {
	//Region,Country,State,City,Month,Day,Year,AvgTemperature



	public ParsedRecord(String region, String country, String state, String city,
						int month, int day, int year, double avgTemperature) {
		this.region = region;
		this.country = country;
		this.state = state;
		this.city = city;
		this.month = month;
		this.day = day;
		this.year = year;
		this.avgTemperature = avgTemperature;
	}



	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getAvgTemperature() {
		return avgTemperature;
	}

	public void setAvgTemperature(double avgTemperature) {
		this.avgTemperature = avgTemperature;
	}

	public String getKey() {
				return 	"region='" + region + '\'' +
				", country='" + country + '\'' +
				", state='" + state + '\'' +
				", city='" + city + '\'' +
				", month=" + month +
				", year=" + year ;
	}

	@Override
	public String toString() {
		return "ParsedRecord{" +
				"region='" + region + '\'' +
				", country='" + country + '\'' +
				", state='" + state + '\'' +
				", city='" + city + '\'' +
				", month=" + month +
				", day=" + day +
				", year=" + year +
				", avgTemperature=" + avgTemperature +
				'}';
	}

	String region;
	String country;
	String state;
	String city;
	int month;
	int day;
	int year;
	double avgTemperature;

	public ParsedRecord() {
	
	}
	
}
