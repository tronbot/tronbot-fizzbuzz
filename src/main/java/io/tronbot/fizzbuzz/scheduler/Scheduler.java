package io.tronbot.fizzbuzz.scheduler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Scheduler<T extends TimeslotItem> {
	public List<Timeslot<T>> assign(List<Timeslot<T>> timeslots, List<T> items) {
		if (null == items) {
			return timeslots;
		}
		// Iterate unassigned item
		for (T item : items.stream().filter(itm -> !itm.isAssigned()).collect(Collectors.toList())) {
			//Find big enough timeslot 
			Optional<Timeslot<T>> timeslot = timeslots.stream().filter(ts -> ts.hasCapacity(item)).findAny();
			if (!timeslot.isPresent()) {
				throw new RuntimeException("Not enough space for item:" + item.toString());
			}
			timeslot.get().add(item);
		}
		return timeslots;
	}

	public void printOut(List<Timeslot<T>> timeslots) {
		if (null != timeslots) {
			timeslots.forEach(ts -> {
				System.out.println(ts.prettyPrint());
			});
		}
	}
}
