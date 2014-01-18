package util;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import model.Bus;
import model.PublicTransportCenter;
import model.Semaphore;
import model.Station;

/**
 * @author Alexis Cuero Losada
 * This abstract class offer different methods for update the buses movement state, acceleration, next node and 
 * next stop station.
 */
public class UpdateBuses {

	/**
	 * The station for the buses out of the roads.
	 */
	public static final Station UNKNOWED = new Station("Unknowed", new Point2D.Double(), false); 
	
	/**
	 * Returns the next node (semaphore or station) in the bus route.
	 * @param bus the bus for search the next node
	 * @return the bus next node
	 */
	public static Object getNextNode(Bus bus) {	
		double busDistance = bus.getPosition();
		ArrayList<Double> distances = bus.getRoute().getWay().getDistances();
		ArrayList<Object> nodes = bus.getRoute().getWay().getNodes();
		
		for (int i = 0 ; i < distances.size() - 1 ; i++)
		{
			if(distances.get(i) <= busDistance && distances.get(i+1) > busDistance)
			{				
				return nodes.get(i+1);
			}
		}
		
		return UNKNOWED;
	}
	
	/**
	 * Returns the next node (semaphore or station) in the bus route based in a distance.
	 * This method allow get the next node in any distance
	 * @param bus the bus for search the next node
	 * @param lastNodeDistance the distance of last node
	 * @return the bus next node
	 */
	public static Object getNextNode(Bus bus, double lastNodeDistance) {
		ArrayList<Double> distances = bus.getRoute().getWay().getDistances();
		ArrayList<Object> nodes = bus.getRoute().getWay().getNodes();
		
		for (int i = 0 ; i < distances.size() - 1 ; i++)
		{
			if(distances.get(i) <= lastNodeDistance && distances.get(i+1) > lastNodeDistance)
			{
				return nodes.get(i+1);
			}
		}
		
		return UNKNOWED;
	}
	
	/**
	 * @param bus the bus to search the next stop station.
	 * @return Returns the next stop station in the bus route.
	 */
	public static Station getNextStopStation(Bus bus) {
		double busDistance = bus.getPosition();
		ArrayList<Double> distances = bus.getRoute().getWay().getDistances();
		ArrayList<Object> nodes = bus.getRoute().getWay().getNodes();
		
		boolean validRange = false;
		
		for (int i = 0 ; i < distances.size() - 1 ; i++)
		{
			if(distances.get(i) <= busDistance && distances.get(i+1) > busDistance)
			{
				validRange = true;
				continue;
			}
			
			if(validRange)
			{
				if(nodes.get(i) instanceof Station)
				{
					return (Station) nodes.get(i);
				}
			}
		}
		
		return UNKNOWED;
	}
	
	/**
	 * Returns the List of next four nodes of a bus.
	 * @param bus The bus for search the next four nodes
	 * @return The next four nodes
	 */
	public static ArrayList<Object> getNextFourNodes(Bus bus) {
		ArrayList<Object> nextFourNodes = new ArrayList<Object>(4);
		
		for (int i = 0; i < 4; i++) 
		{
			if(i > 0)
			{
				if(nextFourNodes.get(i - 1) != UNKNOWED)
				{
					nextFourNodes.add(getNextNode(bus, getNodeDistance(bus)));
				}
				else
				{
					nextFourNodes.add(UNKNOWED);
				}
			}
		}
		
		return nextFourNodes;
	}
	
	private static double getNodeDistance(Bus bus) {
		double distance = bus.getPosition();
		int index = 0;
		ArrayList<Double> distances = bus.getRoute().getWay().getDistances();
		ArrayList<Object> nodes = bus.getRoute().getWay().getNodes();
		
		if(bus.getNextNode() != UNKNOWED)
		{
			index = nodes.indexOf(bus.getNextNode());
			
			if(index > -1)
			{
				return distances.get(index) - distance;
			}
		}
		
		return Double.NaN;
	}

	/**
	 * Returns the optimal acceleration of a bus.
	 * It is based on {@link PhysicalCalculations} class and decide the best option supported 
	 * by the estimates obtained thus keeps the system balanced.
	 * @param bus
	 * @return Returns the optimal acceleration of a bus.
	 */
	public static double getOptimalAcceleration(Bus bus) {		
		PublicTransportCenter pTC = PublicTransportCenter.getPublicTransportCenter();
		
		if(bus.getState() && bus.getMovementState() != 0)
		{
			if(bus.getMovementState() != 0)
			{
				Object nextNode = bus.getNextNode(); 
				double nodeDistance = getNodeDistance(bus);
				
				System.out.println();
				System.out.println("Bus: " + bus.getId());
				System.out.println("Movement State: " + bus.getMovementState());
				
				if(nextNode instanceof Semaphore)
				{
					int index = pTC.getSemaphores().indexOf(nextNode);
					nextNode = pTC.getSemaphores().get(index);
					Semaphore semaphore = ((Semaphore)(nextNode));
					int availableTime = PhysicalCalculations.getTimeAvailable(semaphore);
					
					if(semaphore.getState())
					{
						System.out.println("The Next Node is the Semaphore ID: " + semaphore.getId() + " --> Node distance: " + UtilCalc.round(nodeDistance * 1000, 1) + " --> Green --> Time until red: " + (semaphore.getTimeGreen() - semaphore.getTime()));
					} else
					{
						System.out.println("The Next Node is the Semaphore ID: " + semaphore.getId() + " --> Node distance: " + UtilCalc.round(nodeDistance * 1000, 1) + " --> Red --> Time until green: " + (semaphore.getTimeRed() - semaphore.getTime()));
					}
					
					if(bus.getMovementState() != -2 && bus.getMovementState() != -1 && bus.getMovementState() != 0)
					{
						if(availableTime > 0)
						{
//							System.out.println("The Semaphore is green.");
							
							switch (PhysicalCalculations.conditionToPassInGreen(bus, nodeDistance, availableTime))
							{
							case 1:
//								bus.setMovementState(1);
								
								return balanceForGreenLigth(bus);
								
//								if(bus.getSpeed() < PhysicalCalculations.ADEQUATE_SPEED)
//								{
////									System.out.println("Green accelerating.");
//									return PhysicalCalculations.ADEQUATE_ACCELERATION;
//								}
//								
////								System.out.println("Very fast.");
//								return 0;
							case 2:
								bus.setMovementState(2);
								
								if(bus.getSpeed() < PhysicalCalculations.MAX_SPEED)
								{
//									System.out.println("Green accelerating for pass.");
									return PhysicalCalculations.bestAcceleration(bus, nodeDistance, availableTime);
								}
//								System.out.println("Very fast.");
								
								return 0;
							case 3:
								bus.setMovementState(3);
//								System.out.println("Green but the time not enough.");
								return PhysicalCalculations.bestBreaking(bus, nodeDistance);
							}
						} else 
						{
							System.out.println("The semaphore is red.");
							availableTime *= -1;

							if(bus.getMovementState() != 0 && bus.getMovementState() != -1)
							{
								switch (PhysicalCalculations.conditionToBreaking(bus, nodeDistance)) 
								{
								case 1:
									return balance(bus);
									
//									bus.setMovementState(1);
//									if(bus.getSpeed() < PhysicalCalculations.ADEQUATE_SPEED)
//									{
////										System.out.println("Acceleration very slow for breaking, waiting...");
//										return PhysicalCalculations.ADEQUATE_ACCELERATION;
//									}
//									
////									System.out.println("Very fast.");
//									return 0;
								case 3:
									if(bus.getMovementState() != 99 && bus.getMovementState() != 5)
									{
//										System.out.println("Red breaking.");
										if(bus.getSpeed() > 0)
										{
											bus.setMovementState(3);
											return PhysicalCalculations.bestBreaking(bus, nodeDistance);
										}
										
										bus.setMovementState(0);
										return 0;
									}
									
									return PhysicalCalculations.MIN_ACCELERATION;
								case 4:
									bus.setMovementState(4);
									
									if(bus.getSpeed() < PhysicalCalculations.ADEQUATE_SPEED)
									{
//										System.out.println("Pass in red.");
										return PhysicalCalculations.MAX_ACCELERATION;
									}
									
//									System.out.println("Very fast.");
									return 0;
								}
							}
							
						}
						
						System.err.println("Error evaluating system.");
						return 0;
					}
					
					return 0;
				} else
				{
					System.out.println("The Next Node is the Station: " + (((Station)(nextNode)).getName()) + " --> Node distance: " + UtilCalc.round(nodeDistance * 1000, 1) + " Stop time: " + bus.getStopTime());

					if(!nextNode.equals(UNKNOWED) && bus.getMovementState() != -2 && bus.getMovementState() != -1 && bus.getMovementState() != 0)
					{				
						
						switch (PhysicalCalculations.conditionToBreakingStation(bus, nodeDistance))
						{
						case 1:
							System.out.println("Case 1");
							return balance(bus);
							
						case 3:
							System.out.println("Case 3");
							if (bus.getMovementState() != 99 && bus.getMovementState() != 5)
							{
								bus.setMovementState(3);
								
								if(bus.getSpeed() > 0)
								{
									return PhysicalCalculations.bestBreaking(bus, nodeDistance);
								}
								
								bus.setMovementState(0);
								return 0;
							} 
							
							bus.setMovementState(1);
							return PhysicalCalculations.ADEQUATE_ACCELERATION;
						}
						
						
					}
				}
			}
		}
		
		return 0;
	}
	
	private static double balanceForGreenLigth(Bus bus)	{
		System.out.println(PhysicalCalculations.isBalanced(bus));
		switch (PhysicalCalculations.isBalanced(bus)) {
			case -2:
				bus.setMovementState(6);
				
				if(bus.getSpeed() < PhysicalCalculations.MIN_SPEED)	{
					return PhysicalCalculations.ADEQUATE_ACCELERATION;
				}
				
				if(bus.getSpeed() < 0)
				{
					System.out.println("Min distance.");
					bus.setSpeed(0);
					return 0;
				}
				
				return PhysicalCalculations.ADEQUATE_BREAKING;
			case -1:
				bus.setMovementState(1);
				if(bus.getSpeed() < PhysicalCalculations.MIN_SPEED)	{
					return PhysicalCalculations.MIN_ACCELERATION;
				}
				
				return 0;
			case 0:
				
				if(bus.getSpeed() < PhysicalCalculations.MIN_SPEED)	{
					bus.setMovementState(2);
					return PhysicalCalculations.ADEQUATE_ACCELERATION;
				}
				
				bus.setMovementState(1);
				return 0;
			case 1:
				bus.setMovementState(6);
				if(bus.getSpeed() < PhysicalCalculations.MAX_SPEED)
				{
					return PhysicalCalculations.ADEQUATE_ACCELERATION;
				}
				
				return 0;
			case 2:
				bus.setMovementState(2);
				if(bus.getSpeed() < PhysicalCalculations.ADEQUATE_SPEED)
				{
					return PhysicalCalculations.ADEQUATE_ACCELERATION;
				}
				
				return 0;
		}
		
		bus.setMovementState(1);
		return 0;
	}

	private static double balance(Bus bus) {
		switch (PhysicalCalculations.isBalanced(bus))
		{
		case -2:
			bus.setMovementState(6);
			if(bus.getSpeed() < 0)
			{
//				System.out.println("Min distance.");
				bus.setSpeed(0);
				return 0;
			}
			
			return PhysicalCalculations.ADEQUATE_BREAKING;	
		case -1:
			bus.setMovementState(6);
			if(bus.getSpeed() < PhysicalCalculations.MIN_SPEED - PhysicalCalculations.DELTA_SPEED)
			{
//				System.out.println("< Min Speed, accelerating.");
				return PhysicalCalculations.MIN_ACCELERATION;
			} else {
				if(bus.getSpeed() > PhysicalCalculations.MIN_SPEED + PhysicalCalculations.DELTA_SPEED) {
//					System.out.println("> Min speed.");
					return PhysicalCalculations.MIN_BREAKING;
				}
			}
			
//			System.out.println("Adequate speed.");
			return 0;
		case 0:
//			System.out.println("OK.");
			bus.setMovementState(1);
			return 0;
		case 1:
			
			bus.setMovementState(6);
			if(bus.getSpeed() < PhysicalCalculations.MAX_SPEED)
			{
//				System.out.println("< Max speed accelerating.");
				return PhysicalCalculations.ADEQUATE_ACCELERATION;
			}
			
			return 0;
		case 2:
			bus.setMovementState(2);
			if(bus.getSpeed() < PhysicalCalculations.MAX_SPEED)
			{
//				System.out.println("UNKNOWED");
				return PhysicalCalculations.ADEQUATE_ACCELERATION;
			}
			
			return 0;
		}
		
//		System.out.println("Last.");
		bus.setMovementState(1);
		return 0;
	}
}
