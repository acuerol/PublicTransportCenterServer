package util;

import model.Bus;
import model.Semaphore;

public class PhysicalCalculations {

	public static final double TIME = 0.5; 
	public static final double MAX_ACCELERATION = 1.37;
	public static final double ADEQUATE_ACCELERATION = 1.18;
	public static final double MAX_BREAKING = 2.45;
	public static final double ADEQUATE_BREAKING = 1.84;
	public static final int STATION_STOP_TIME = 8;
	public static final double MAX_SPEED = 13.89;
	public static final double IDEAL_DISTANCE = 1; 
	public static final double SEMAPHORE_TOLERANCE = 5e-2;
	
	public static double getTimeAvaible(Semaphore semaphore)
	{
		if(semaphore.getState())
		{
			return (semaphore.getTimeGreen() - semaphore.getTime());
		}
		else
		{
			return (semaphore.getTimeRed() - semaphore.getTime());
		}
	}
	
	public static boolean isPossiblePassGreen(Bus bus, double nodeDistance, double avaibleTime) {
		
		nodeDistance += SEMAPHORE_TOLERANCE;
		
		if(minTimeToMaxSpeed(bus.getSpeed()) + timeToPosition(MAX_SPEED, nodeDistance) < avaibleTime)
		{
			return true;
		}
		
		return false;
	}

	private static double timeToPosition(double speed, double nodeDistance)
	{
		return (nodeDistance/speed);
	}
	
	private static double minTimeToMaxSpeed(double initialSpeed)
	{
		return ((MAX_SPEED - initialSpeed) / MAX_ACCELERATION);
	}
	
	public static double getMinimumAcceleration(Bus bus) {
		
		return 0;
	}

	public static boolean isPossiblePassRed(Bus bus, double nodeDistance, double avaibleTime) {
		return false;
	}
}
