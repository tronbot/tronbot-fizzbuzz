package io.tronbot.fizzbuzz.scheduler;

public  interface TimeslotItem {
	public Integer getDuration();
	public void setAssigned(boolean assigned);
	public boolean isAssigned();
	
}
