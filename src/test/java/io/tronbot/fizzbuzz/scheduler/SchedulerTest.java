package io.tronbot.fizzbuzz.scheduler;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.tronbot.fizzbuzz.scheduler.Course;
import io.tronbot.fizzbuzz.scheduler.Scheduler;
import io.tronbot.fizzbuzz.scheduler.Timeslot;

public class SchedulerTest {
	private static List<Course> courses;
	private static List<Timeslot<Course>> timeslots;

	@BeforeClass
	public static void init() {
		courses = Arrays.asList(new Course("Writing Fast Tests Against Enterprise Rails", 60),
				new Course("Overdoing it in Python", 45), new Course("Lua for the Masses", 30),
				new Course("Ruby Errors from Mismatched Gem Versions", 45), new Course("Common Ruby Errors", 45),
				new Course("Rails for Python Developers lightning", 30), new Course("Communicating Over Distance", 60),
				new Course("Accounting-Driven Development", 45), new Course("Woah", 30),
				new Course("Sit Down and Write", 30), new Course("Pair Programming vs Noise", 45),
				new Course("Rails Magic", 60), new Course("Ruby on Rails: Why We Should Move On", 60),
				new Course("Clojure Ate Scala (on my project)", 45),
				new Course("Programming in the Boondocks of Seattle", 30),
				new Course("Ruby vs. Clojure for Back-End Development ", 30),
				new Course("Ruby on Rails Legacy App Maintenance", 60), new Course("A World Without HackerNews", 30),
				new Course("User Interface CSS in Rails Apps", 30));
		timeslots = Arrays.asList(new Timeslot<Course>("Track 1:", LocalTime.of(9, 0), 180),
				new Timeslot<Course>(null, LocalTime.of(12, 0), 60) {
					{
						add(new Course("Lunch", 60));
					}
				}, new Timeslot<Course>(null, LocalTime.of(13, 0), 240),
				new Timeslot<Course>(null, LocalTime.of(5, 0), 0) {
					{
						add(new Course("Networking Event", 0));
					}
				}, new Timeslot<Course>("Track 2:", LocalTime.of(9, 0), 180),
				new Timeslot<Course>(null, LocalTime.of(12, 0), 60) {
					{
						add(new Course("Lunch", 60));
					}
				}, new Timeslot<Course>(null, LocalTime.of(13, 0), 240),
				new Timeslot<Course>(null, LocalTime.of(5, 0), 0) {
					{
						add(new Course("Networking Event", 0));
					}
				});
	}

	@Test
	public void testScheduler() {
		Scheduler<Course> scheduler = new Scheduler<>();
		// shuffle makes things interesting
		Collections.shuffle(courses);
		scheduler.assign(timeslots, courses);
		scheduler.printOut(timeslots);
		// Make sure all course assigned
		long unassignedCount = courses.stream().filter(c -> !c.isAssigned()).collect(Collectors.counting());
		Assert.assertEquals(unassignedCount, 0L);
		System.out.println("===All course assigned!===");
	}
}
