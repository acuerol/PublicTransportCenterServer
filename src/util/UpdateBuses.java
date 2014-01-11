package util;

import java.awt.geom.Point2D;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import javax.swing.text.html.MinimalHTMLWriter;

import controller.CentralSystem;
import model.Bus;
import model.PublicTransportCenter;
import model.Semaphore;
import model.Station;

public class UpdateBuses {

	public static final Station UNKNOWED = new Station("Unknowed", new Point2D.Double(), false); 
	public static final double STOP_TOLERANCE = 2e-2;
	private static final double SPEED_DELTA = 1.5;
	
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
	
	public static Object getNextNode(Bus bus, double nodeDistance) {
		ArrayList<Double> distances = bus.getRoute().getWay().getDistances();
		ArrayList<Object> nodes = bus.getRoute().getWay().getNodes();
		
		for (int i = 0 ; i < distances.size() - 1 ; i++)
		{
			if(distances.get(i) <= nodeDistance && distances.get(i+1) > nodeDistance)
			{
				return nodes.get(i+1);
			}
		}
		
		return UNKNOWED;
	}
	
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

	public static double getOptimalAcceleration(Bus bus) {		
		PublicTransportCenter pTC = PublicTransportCenter.getPublicTransportCenter();
		
		if(bus.getState() && bus.getMovementState() != 0)
		{
			if(bus.getMovementState() != 0)
			{
				System.out.println("true");
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
					int avaibleTime = PhysicalCalculations.getTimeAvaible(semaphore);
					
					if(semaphore.getState())
					{
						System.out.println("The Next Node is the Semaphore ID: " + semaphore.getId() + " --> Node distance: " + UtilCalc.round(nodeDistance * 1000, 1) + " --> Green --> Time until red: " + (semaphore.getTimeGreen() - semaphore.getTime()));
					} else
					{
						System.out.println("The Next Node is the Semaphore ID: " + semaphore.getId() + " --> Node distance: " + UtilCalc.round(nodeDistance * 1000, 1) + " --> Red --> Time until green: " + (semaphore.getTimeRed() - semaphore.getTime()));
					}
					
					if(bus.getMovementState() != -2 && bus.getMovementState() != -1)
					{
						if(avaibleTime == 0)
						{
//							System.out.println("The semaphore is red.");
							
							switch (PhysicalCalculations.conditionToBreaking(bus, nodeDistance)) 
							{
							case 1:
								bus.setMovementState(1);
								
								if(bus.getSpeed() < PhysicalCalculations.ADEQUATE_SPEED)
								{
//									System.out.println("Acceleration very slow for breaking, waiting...");
									return PhysicalCalculations.ADEQUATE_ACCELERATION;
								}
								
//								System.out.println("Very fast.");
								return 0;
							case 3:
								if(bus.getMovementState() != 99 && bus.getMovementState() != 5)
								{
//									System.out.println("Red breaking.");
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
//									System.out.println("Pass in red.");
									return PhysicalCalculations.MAX_ACCELERATION;
								}
								
//								System.out.println("Very fast.");
								return 0;
							}
							
						} else
						{
//							System.out.println("The Semaphore is green.");
							
							switch (PhysicalCalculations.conditionToPass(bus, nodeDistance, avaibleTime))
							{
							case 1:
								bus.setMovementState(1);
								
								if(bus.getSpeed() < PhysicalCalculations.ADEQUATE_SPEED)
								{
//									System.out.println("Green accelerating.");
									return PhysicalCalculations.ADEQUATE_ACCELERATION;
								}
								
//								System.out.println("Very fast.");
								return 0;
							case 2:
								bus.setMovementState(2);
								
								if(bus.getSpeed() < PhysicalCalculations.ADEQUATE_SPEED)
								{
//									System.out.println("Green accelerating for pass.");
									return PhysicalCalculations.bestAcceleration(bus, nodeDistance, avaibleTime);
								}
//								System.out.println("Very fast.");
								
								return 0;
							case 3:
								bus.setMovementState(3);
//								System.out.println("Green but the time not enough.");
								return PhysicalCalculations.bestBreaking(bus, nodeDistance);
							}
						}
						
						System.err.println("Error evaluating system.");
						return Double.NaN;
					}
					
					return 0;
				} else
				{
					int index = pTC.getStations().indexOf(nextNode);
					nextNode = pTC.getStations().get(index);
					
					System.out.println("The Next Node is the Station: " + (((Station)(nextNode)).getName()) + " --> Node distance: " + UtilCalc.round(nodeDistance * 1000, 1) + " Stop time: " + bus.getStopTime());
					
					if(!nextNode.equals(UNKNOWED))
					{
						switch (PhysicalCalculations.conditionToBreakingStation(bus, nodeDistance))
						{
						case 1:
							
							switch (PhysicalCalculations.isBalanced(bus))
							{
							case -1:
								bus.setMovementState(6);
								if(bus.getSpeed() > PhysicalCalculations.MIN_SPEED)
								{
									return PhysicalCalculations.MIN_BREAKING;
								}
								
								return 0;
							case 0:
								return 0;
							case 1:
								bus.setMovementState(6);
								if(bus.getSpeed() < PhysicalCalculations.MAX_SPEED)
								{
									return PhysicalCalculations.MIN_ACCELERATION;
								}
								
								return 0;
							case 2:
								return PhysicalCalculations.ADEQUATE_ACCELERATION;
							}
							
							bus.setMovementState(1);
							return 0;
							
//							bus.setMovementState(1);
//							if(bus.getSpeed() < PhysicalCalculations.MAX_SPEED)
//							{
//								return PhysicalCalculations.MIN_ACCELERATION;
//							}
//							
//							return 0;
						case 3:
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
							
							return PhysicalCalculations.MIN_ACCELERATION;
						}
					}
				}
			}
			
			return PhysicalCalculations.ADEQUATE_BREAKING;
		}
		
		return 0;
	}

	public static Object getStopNode(Bus bus) {
		
		if(bus.getSpeed() < 1)
		{
			if(getNodeDistance(bus) < STOP_TOLERANCE)
			{
				return bus.getNextNode();
			}
		}
		
		return "Is running";
	}
}
