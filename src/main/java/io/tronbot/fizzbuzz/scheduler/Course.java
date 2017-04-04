package io.tronbot.fizzbuzz.scheduler;

public class Course implements TimeslotItem {
	private final String name;
	private final Integer mins;
	private boolean assigned;

	public Course(String name, Integer mins) {
		super();
		this.name = name;
		this.mins = mins;
	}

	public String getName() {
		return name;
	}

	public Integer getDuration() {
		return mins;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	@Override
	public String toString() {
		return String.format(this.name + " " + (this.mins > 0 ? this.mins + "mins" : ""));
	}
}