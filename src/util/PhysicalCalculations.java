package util;

import java.util.ArrayList;
import java.util.Collections;

import model.Bus;
import model.PublicTransportCenter;
import model.Semaphore;

public class PhysicalCalculations {

	public static final double TIME = 0.5; 
	
	public static final double MAX_ACCELERATION = 1.37;
	public static final double ADEQUATE_ACCELERATION = 1.18;
	public static final double MIN_ACCELERATION = 0.7;
	
	public static final double MAX_BREAKING = -2.45;
	public static final double ADEQUATE_BREAKING = -1.84;
	public static final double MIN_BREAKING = -1.0;

	public static final double MAX_SPEED = 13.89;
	public static final double IDEAL_DISTANCE = 1;
	public static final double TOLERANCE_DISTANCE = 1e-1; 
	public static final double SEMAPHORE_TOLERANCE = 5e-2;
	
	private static PublicTransportCenter pTC;
	
	public static double getTimeAvaible(Semaphore semaphore) {
		if(semaphore.getState())
		{
			return (semaphore.getTimeGreen() - semaphore.getTime());
		}
		else
		{
			return (semaphore.getTimeRed() - semaphore.getTime());
		}
	}
	
	public static int conditionToPass(Bus bus, double nodeDistance, double avaibleTime) {
		double timeUniformSpeed = 0.0;
		
		if(bus.getSpeed() == 0 && bus.getMovementState() == 0)
		{
			return 4;
		}
		else
		{
			if(bus.getSpeed() != 0)
			{
				timeUniformSpeed = (SEMAPHORE_TOLERANCE + nodeDistance) / bus.getSpeed();
			}
		}
		
		if(timeUniformSpeed <= avaibleTime)
		{
			return 1;
		}else
		{
			double bestAcceleration = bestAcceleration(bus, nodeDistance, avaibleTime);
			
			if(bestAcceleration < ADEQUATE_ACCELERATION)
			{
				return 2;
			}else
			{
				if(bestAcceleration > ADEQUATE_ACCELERATION && bestAcceleration < MAX_ACCELERATION)
				{
					return 3;
				}
			}
		}
		
		return 0;
	}
	
	public static double bestAcceleration(Bus bus, double nodeDistance, double avaibleTime) {
		double time1 = 0.0;
		double distance1 = 0.0;
		double time2 = 0.0;
		double distance2 = 0.0;
		
		for (double a = MIN_ACCELERATION ; a < MAX_ACCELERATION ; a += 1e-2)
		{
			time1 = ((MAX_SPEED - bus.getSpeed()) / a);
			distance1 = bus.getPosition() + (Math.pow(MAX_SPEED, 2) - Math.pow(bus.getSpeed(), 2));
			distance2 = nodeDistance - distance1;
			time2 = distance2 / MAX_SPEED;
			
			if((time1 + time2) < avaibleTime)
			{
				return a;
			}
		}
		
		return Double.NaN;
	}
	
	public static int conditionToBreaking(Bus bus, double nodeDistance, double avaibleTime) {
		double bestBreaking = bestBreaking(bus, nodeDistance);
		
		if(bestBreaking < ADEQUATE_BREAKING)
		{
			if(bus.getSpeed() < MAX_SPEED)
			{
				return 0;
			}
			
			return 4;
		}else
		{
			if(bestBreaking > ADEQUATE_BREAKING && bestBreaking < MAX_BREAKING)
			{
				return 5;
			}
		}
		
		return 6;
	}
	
	public static double bestBreaking(Bus bus, double nodeDistance) {
		double acceleration = (-1 * Math.pow(bus.getSpeed(), 2)) / (2 * nodeDistance);
		
		return acceleration;
	}

	public static int isBalanced(Bus bus) {
		pTC = PublicTransportCenter.getPublicTransportCenter();
		ArrayList<Bus> buses = pTC.getBuses();
		int index = buses.indexOf(bus);
		Bus nextBus = null;
		
		Collections.sort(buses);
		
		if((index != -1 && index < (buses.size() - 1)))
		{
			for(int i = index + 1 ; i < buses.size() ; i++)
			{
				if(buses.get(i).getState())
				{
					nextBus = buses.get(i);
				}
			}
			
			if(nextBus != null)
			{
				if((nextBus.getPosition() - bus.getPosition()) < IDEAL_DISTANCE - TOLERANCE_DISTANCE)
				{
					return 1;
				}else
				{
					if((nextBus.getPosition() - bus.getPosition()) > IDEAL_DISTANCE + TOLERANCE_DISTANCE)
					{
						return -1;
					}else
					{
						return 0;
					}
				}
			}
		}
		
		return 2;
	}
}
