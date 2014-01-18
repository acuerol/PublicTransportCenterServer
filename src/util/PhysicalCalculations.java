package util;

import java.util.ArrayList;
import java.util.Collections;

import model.Bus;
import model.PublicTransportCenter;
import model.Route;
import model.Semaphore;

/**
 * @author Alexis Cuero Losada
 * This is a abstract class for calculate the conditions to everyone of situations in the system.
 */
public class PhysicalCalculations {

	/**
	 * The time of every report.
	 */
	public static final double TIME = 0.5; 
	/**
	 * The experimental minimum acceleration.
	 */
	public static final double MIN_ACCELERATION = 0.7;
	/**
	 * The adequate experimental acceleration.
	 */
	public static final double ADEQUATE_ACCELERATION = 1.18;
	/**
	 * The maximum experimental acceleration.
	 */
	public static final double MAX_ACCELERATION = 1.37;
	
	/**
	 * The experimental minimum breaking.
	 */
	public static final double MIN_BREAKING = -1.0;
	/**
	 * The adequate experimental acceleration.
	 */
	public static final double ADEQUATE_BREAKING = -1.84;
	/**
	 * The maximum experimental breaking.
	 */
	public static final double MAX_BREAKING = -4.0;

	/**
	 * The minimum speed.
	 */
	public static final double MIN_SPEED = 6.94;
	/**
	 * The adequate speed.
	 */
	public static final double ADEQUATE_SPEED = 11.11;
	/**
	 * The maximum speed.
	 */
	public static final double MAX_SPEED = 13.89;
	/**
	 * The uncertainty range for the speed.
	 */
	public static final double DELTA_SPEED = 0.5;
	
	/**
	 * The minimum distance between buses.
	 */
	public static final double MIN_DISTANCE = 0.3;
	/**
	 * The ideal distance between buses.
	 */
	public static final double IDEAL_DISTANCE = 1.0;
	/**
	 * The uncertainty range distance between buses..
	 */
	public static final double TOLERANCE_IDEAL_DISTANCE = 0.3;
	/**
	 * The tolerance distance between a station and a bus.
	 */
	public static final double TOLERANCE_DISTANCE = 5e-3;
	/**
	 * The tolerance distance between a semaphore and a bus.
	 */
	public static final double SEMAPHORE_TOLERANCE = 2e-2;
	
	/**
	 * Returns the available time until the semaphore change the state, if the semaphore is red, the avaibleTime is
	 * considerate negative. 
	 * @param semaphore the semaphore to check the available time.
	 * @return the time until the semaphore change the state
	 */
	public static int getTimeAvailable(Semaphore semaphore) {
		if(semaphore.getState())
		{
			return (semaphore.getTimeGreen() - semaphore.getTime());
		}

		return -(semaphore.getTimeRed() - semaphore.getTime());
	}
	
	//Revisar la 3
	/**
	 * Returns a code for determine the condition to pass.
	 * <br />
	 * Code 1: The actual speed is adequate for pass in green.
	 * <br />
	 * Code 2: The bus must accelerate.
	 * <br />
	 * Code 3: The acceleration is very low or high for pass.
	 * @param bus the bus to check the conditions
	 * @param nodeDistance the distance until arrive to the node
	 * @param availableTime the available time for arrive the node.
	 * @return a code for determine the condition to pass
	 */
	public static int conditionToPassInGreen(Bus bus, double nodeDistance, double availableTime) {
		double timeUniformSpeed = 0;
		
		if(bus.getSpeed() > 0)
		{
			timeUniformSpeed = (SEMAPHORE_TOLERANCE + nodeDistance) / bus.getSpeed();
		}
		
		if(timeUniformSpeed < availableTime)
		{
			return 1;
		} else
		{
			double bestAcceleration = bestAcceleration(bus, nodeDistance, availableTime);
			
			if(bestAcceleration > ADEQUATE_ACCELERATION && bestAcceleration < MAX_ACCELERATION)
			{
				return 2;
			} else
			{
				return 3;
			}
		}
	}
	
	/**
	 * Returns a code for determine the condition to pass after the semaphore change to red.
	 * Code 7: Can spend but must break.
	 * <br />
	 * Code 3: Can't spend.
	 * <br />
	 * @param bus the bus to check the conditions
	 * @param nodeDistance the distance until arrive to the node
	 * @param availableTime the available time for arrive the node.
	 * @return a code for determine the condition to pass
	 */
	public static int conditionToPassAfterRed(Bus bus, double nodeDistance, double availableTime) {
		double bestBreaking = bestBreakingRedLight(bus, nodeDistance, availableTime);
		
		if(bestBreaking != 100)
		{
			System.out.println("Pass after red ligth.");
			return 7;
		}
		
		return 3;
	}
	
	/**
	 * Returns the best breaking for pass when the semaphore is red.
	 * @param bus the bus to check the conditions
	 * @param nodeDistance the distance until arrive to the node
	 * @param availableTime the available time for arrive the node.
	 * @return the best breaking for pass when the semaphore is red
	 */
	public static double bestBreakingRedLight(Bus bus, double nodeDistance, double availableTime) {
		double time1 = 0.0;
		double distance1 = 0.0;
		double time2 = 0.0;
		double distance2 = 0.0;
		
		for (double a = MIN_BREAKING ; a > ADEQUATE_BREAKING ; a -= 1e-2)
		{
			time1 = ((MIN_SPEED - bus.getSpeed()) / a);
			distance1 = bus.getPosition() * 1000 + (Math.pow(MIN_SPEED, 2) - Math.pow(bus.getSpeed(), 2));
			distance2 = nodeDistance - SEMAPHORE_TOLERANCE * 1000 - distance1 ;
			time2 = distance2 / MIN_SPEED;
			
			if((time1 + time2) > availableTime)
			{
				System.out.println(a);
				return a;
			}
		}
		
		return 100;
	}
	
	/**
	 * Returns the best acceleration for pass when the semaphore is green, the acceleration is between MIN_ACCELERATION
	 * and MAX_ACCELERATION.
	 * @param bus the bus to check the conditions
	 * @param nodeDistance the distance until arrive to the node
	 * @param availableTime the available time for arrive the node.
	 * @return the best acceleration for pass when the semaphore is green.
	 */
	public static double bestAcceleration(Bus bus, double nodeDistance, double availableTime) {
		double time1 = 0.0;
		double distance1 = 0.0;
		double time2 = 0.0;
		double distance2 = 0.0;
		
		for (double a = MIN_ACCELERATION ; a < MAX_ACCELERATION ; a += 1e-2)
		{
			time1 = ((MAX_SPEED - bus.getSpeed()) / a);
			distance1 = bus.getPosition() * 1000 + (Math.pow(MAX_SPEED, 2) - Math.pow(bus.getSpeed(), 2));
			distance2 = nodeDistance + SEMAPHORE_TOLERANCE * 1000 - distance1 ;
			time2 = distance2 / MAX_SPEED;
			
			if((time1 + time2) < availableTime)
			{
				return a;
			}
		}
		
		return Double.NaN;
	}
	
	/**
	 * Returns a code for determine the condition to breaking in a semaphore.
	 * <br />
	 * Code 1: The breaking is very low.
	 * <br />
	 * Code 3: The breaking is adequate, the bus is breaking.
	 * <br />  
	 * Code 4: The breaking is very high pass.
	 * @param bus the bus to check the conditions
	 * @param nodeDistance the distance until arrive to the node
	 * @return the best breaking for breaking in a semaphore.
	 */
	public static int conditionToBreaking(Bus bus, double nodeDistance) {
		double bestBreaking = bestBreaking(bus, nodeDistance);
		
		if(bestBreaking < 0)
		{
			if(bestBreaking > MIN_BREAKING)
			{
				System.out.println(UtilCalc.round(bestBreaking, 2) + " > Min Breaking");
				return 1;
			}else
			{
				if(bestBreaking > MAX_BREAKING)
				{
					System.out.println(UtilCalc.round(bestBreaking, 2) + " < Min Breaking > Max Breaking");
					return 3;
				} else
				{
					System.out.println(bestBreaking + " < Max Breaking (Pass)");
					return 4;
				}
			}
		}
		
		return 3;
	}
	
	/**
	 * Returns a code for determine the condition to breaking in a station.
	 * <br />
	 * Code 1: The breaking is very low.
	 * <br />
	 * Code 3: The breaking is adequate, the bus is breaking.  
	 * @param bus the bus to check the conditions
	 * @param nodeDistance the distance until arrive to the node
	 * @return the best breaking for breaking in a station
	 */
	public static int conditionToBreakingStation(Bus bus, double nodeDistance) {
		double bestBreaking = bestBreaking(bus, nodeDistance);
		
		if(bestBreaking < 0)
		{
			if(bestBreaking > MIN_BREAKING)
			{
				System.out.println(UtilCalc.round(bestBreaking, 2) + " > Min Breaking");
				return 1;
			}else
			{
				if(bestBreaking > MAX_BREAKING)
				{
					System.out.println(UtilCalc.round(bestBreaking, 2) + " < Min Breaking > Max Breaking");
					return 3;
				}
			}
		}
		
		return 3;
	}
	
	/**
	 * Returns the best breaking.
	 * The best breaking is the negative acceleration for stop in a distance.
	 * @param bus the bus to check the conditions
	 * @param nodeDistance the distance until arrive to the node
	 * @return the best breaking for stop in a distance
	 */
	public static double bestBreaking(Bus bus, double nodeDistance) {
		double bestBreaking = (-1 * Math.pow(bus.getSpeed(), 2)) / (2 * nodeDistance * 1000 - TOLERANCE_DISTANCE * 1000);
		
		if(bestBreaking < 0)
		{
			return bestBreaking;
		}
		
		return 0;
	}

	/**
	 * Returns a ArrayList<Bus> with the buses separate by route.
	 * @param route the route to filter the buses.
	 * @return the buses separate by route.
	 */
	private static ArrayList<Bus> separateByRoute(Route route)
	{
		PublicTransportCenter pTC = PublicTransportCenter.getPublicTransportCenter();
		
		ArrayList<Bus> buses = new ArrayList<Bus>();
		
		for (Bus bus : pTC.getBuses()) {
			if(bus.getRoute().equals(route))
			{
				buses.add(bus);
			}
		}
		
		Collections.sort(buses);
		
		return buses;
	}
	
	/**
	 * Returns a code for determine if the system is balanced, based in two buses.
	 * <br /> 
	 * Code -2: The distance between buses is the minimal.
	 * <br />
	 * Code -1: The buses are very near, must break.
	 * <br />
	 * Code 0: The buses are OK.
	 * <br />
	 * Code 1: The buses is away, must accelerate.
	 * <br />
	 * Code 2: Can not be determined for the conditions.
	 * @param bus the bus for determine if is to adequate distance of the next bus.
	 * @return a code for determine if the system is balanced
	 */
	public static int isBalanced(Bus bus) {
		ArrayList<Bus> buses = separateByRoute(bus.getRoute());
				
		int index = buses.indexOf(bus);
		
		if((index != -1 && index < (buses.size() - 1)))
		{
			Bus nextBus = null;
			for (int i = index; i < buses.size() - 1; i++) {
				if(buses.get(++i).getState())
				{
					nextBus = buses.get(i); 
					break;
				}
			}
			
			if(nextBus != null)
			{
				if((nextBus.getPosition() - bus.getPosition()) > IDEAL_DISTANCE + TOLERANCE_IDEAL_DISTANCE)
				{
					return 1;
				}else
				{
					if((nextBus.getPosition() - bus.getPosition()) < IDEAL_DISTANCE - TOLERANCE_IDEAL_DISTANCE)
					{
						if((nextBus.getPosition() - bus.getPosition()) < MIN_DISTANCE) {
							return -2;
						}
						
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
