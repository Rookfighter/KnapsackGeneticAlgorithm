package knapsack.misc;

public class StopWatch {

	private long lastNsec;
	private long diffNsec;
	private boolean running;
	
	public StopWatch() {
		running = false;
	}
	
	public void start() {
		if(running)
			throw new IllegalStateException("Cannot start. StopWatch is still running.");
		running = true;
		lastNsec = System.nanoTime();
	}
	
	public void stop() {
		if(!running)
			throw new IllegalStateException("Cannot stop. StopWatch is not running.");
		diffNsec = System.nanoTime() - lastNsec;
		running = false;		
	}
	
	public float sec() {
		return ((float) msec()) / 1000.0f;
	}
	
	public int msec() {
		return (int) (usec() / 1000);
	}
	
	public long usec() {
		return nsec() / 1000;
	}
	
	public long nsec() {
		if(running)
			throw new IllegalStateException("StopWatch is still running.");
		return diffNsec;
	}
}
