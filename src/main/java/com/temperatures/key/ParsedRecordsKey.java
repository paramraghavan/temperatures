package com.temperatures.key;


import java.util.Objects;

public class ParsedRecordsKey {
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public ParsedRecordsKey(String region, String country, String state, String city, int month, int year) {
		this.region = region;
		this.country = country;
		this.state = state;
		this.city = city;
		this.month = month;
		this.year = year;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ParsedRecordsKey that = (ParsedRecordsKey) o;
		return month == that.month && year == that.year && region.equals(that.region) && country.equals(that.country) && state.equals(that.state) && city.equals(that.city);
	}

	@Override
	public int hashCode() {
		return Objects.hash(region, country, state, city, month, year);
	}

	String region;
	String country;
	String state;
	String city;
	int month;

	@Override
	public String toString() {
		return 	"region='" + region + '\'' +
				", country='" + country + '\'' +
				", state='" + state + '\'' +
				", city='" + city + '\'' +
				", month=" + month +
				", year=" + year ;
	}

	int year;

	
	public ParsedRecordsKey() {
	
	}

// Implement hashcode() and equals()


}
