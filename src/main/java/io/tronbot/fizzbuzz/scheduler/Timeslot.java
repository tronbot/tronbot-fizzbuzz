package io.tronbot.fizzbuzz.scheduler;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Timeslot<O extends TimeslotItem> {
	private Integer capacity;
	private final String name;
	private List<O> items = new ArrayList<>();
	private LocalTime startTime;

	public Timeslot(String name, LocalTime startTime, Integer capacity) {
		super();
		this.capacity = capacity;
		this.name = name;
		this.startTime = startTime;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public String getName() {
		return name;
	}

	public boolean add(O o) {
		if (hasCapacity(o)) {
			items.add(o);
			this.capacity -= o.getDuration();
			o.setAssigned(true);
			return true;
		}
		return false;
	}

	public void addAll(Collection<O> os) {
		os.forEach(o -> this.add(o));
	}

	public boolean hasCapacity(O o) {
		return capacity.compareTo(o.getDuration()) > -1;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * Pretty print timeslot assignment
	 * 
	 */
	public String prettyPrint() {
		StringBuilder sb;
		if (null != this.getName() && !getName().isEmpty()) {
			sb = new StringBuilder(this.name);
			sb.append(System.lineSeparator());
		} else {
			sb = new StringBuilder();
		}
		items.forEach(item -> {
			sb.append(startTime.format(DateTimeFormatter.ofPattern("hh:mma"))).append(" ").append(item.toString())
					.append(System.lineSeparator());
			startTime = startTime.plusMinutes(item.getDuration());
		});
		String printable = sb.toString();
		return printable.substring(0, printable.lastIndexOf(System.lineSeparator()));
	}
}
