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

//		System.out.println("----------------------");
//		System.out.println("---- " + distance + " ----");
//		
//		for (Route route : PublicTransportCenter.getPublicTransportCenter().getRoutes()) {
//			if(route.getWay().equals(this))
//			{
//				System.out.println(route.getName());
//			}
//		}
//		
//		for (Object node : nodes) {
//			if(node instanceof Station)
//			{
//				System.out.println(nodes.indexOf(node));
//				System.out.println(((Station)(node)).getName() + " - " + distances.get(nodes.indexOf(node)));
//			}
//			else
//			{
//				System.out.println(nodes.indexOf(node));
//				System.out.println(((Semaphore)(node)).getID() + " - " + distances.get(nodes.indexOf(node)));
//			}
//		}
//		System.out.println("----------------------");
		
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
		
		Object nextNode = bus.getNextNode(); 
		
		double nodeDistance = getNodeDistance(bus);
		System.out.println("Movement State: " + bus.getMovementState());
		
		if(bus.getMovementState() != 0)
		{
			if(nextNode instanceof Semaphore)
			{
				int index = pTC.getSemaphores().indexOf(nextNode);
				nextNode = pTC.getSemaphores().get(index);
				
				Semaphore semaphore = ((Semaphore)(nextNode));
				
				System.out.println();
				
				if(semaphore.getState())
				{
					System.out.println("The Next Node is a Semaphore --> Node distance: " + UtilCalc.round(nodeDistance * 1000, 1) + " --> Time ultil red: " + (semaphore.getTimeGreen() - semaphore.getTime()));
				} else
				{
					System.out.println("The Next Node is a Semaphore --> Node distance: " + UtilCalc.round(nodeDistance * 1000, 1) + " --> Time ultil green: " + (semaphore.getTimeRed() - semaphore.getTime()));
				}
				
				int avaibleTime = PhysicalCalculations.getTimeAvaible(semaphore);
				
				if(bus.getMovementState() != -2)
				{
					if(avaibleTime == 0)
					{
						System.out.println("The Semaphore is red.");
						
						switch (PhysicalCalculations.conditionToBreaking(bus, nodeDistance, avaibleTime)) 
						{
						case 1:
							bus.setMovementState(1);
							
							if(bus.getSpeed() < PhysicalCalculations.MAX_SPEED && bus.getMovementState() != -2)
							{
								System.out.println("Acceleration very slow for breaking, waiting.");
								return PhysicalCalculations.ADEQUATE_ACCELERATION;
							}
							
							System.out.println("Very fast.");
							return 0;
							
						case 3:
							bus.setMovementState(3);
							
							System.out.println("RED Breaking.");
							if(bus.getSpeed() > 0)
							{
								return PhysicalCalculations.bestBreaking(bus, nodeDistance);
							}
							
							return 0;
						case 4:
							bus.setMovementState(4);
							
							if(bus.getSpeed() < PhysicalCalculations.MAX_SPEED)
							{
								System.out.println("Pass in RED.");
								return PhysicalCalculations.MAX_ACCELERATION;
							}
							System.out.println("Very fast.");
							return 0;
						}
						
					} else
					{
						System.out.println("The Semaphore is green.");
						
						switch (PhysicalCalculations.conditionToPass(bus, nodeDistance, avaibleTime))
						{
						case 1:
							bus.setMovementState(1);
							
							if(bus.getSpeed() < PhysicalCalculations.MAX_SPEED)
							{
								System.out.println("GREEN accelerating.");
								return PhysicalCalculations.ADEQUATE_ACCELERATION;
							}
							
							System.out.println("Very fast.");
							return 0;
						case 2:
							bus.setMovementState(2);
							
							if(bus.getSpeed() < PhysicalCalculations.MAX_SPEED)
							{
								System.out.println("GREEN accelerating to best acceleration.");
								return PhysicalCalculations.bestAcceleration(bus, nodeDistance, avaibleTime);
							}
							System.out.println("Very fast.");
							return 0;
							
						case 3:
							bus.setMovementState(3);
							System.out.println("GREEN but the time not enough.");
							return PhysicalCalculations.bestBreaking(bus, nodeDistance);
						case 4:
							bus.setMovementState(0);
							System.out.println("Stopped...");
							return 0;
						}
					}
					
					return 0;
				}
			} else
			{
				if(bus.getMovementState() != -1)
				{
					int index = pTC.getStations().indexOf(nextNode);
					nextNode = pTC.getStations().get(index);
					
					System.out.println();
					System.out.println("The Next Node is a Station --> Node distance: " + UtilCalc.round(nodeDistance * 1000, 1) + " name: " + (((Station)(nextNode)).getName()));
					if(!nextNode.equals(UNKNOWED))
					{
						double bestBreaking = PhysicalCalculations.bestBreaking(bus, nodeDistance);
						
						if(bus.getMovementState() == 2)
						{
							return PhysicalCalculations.ADEQUATE_ACCELERATION;
						}
						
						
						if(bestBreaking < PhysicalCalculations.MIN_BREAKING && bestBreaking > PhysicalCalculations.MAX_BREAKING)
						{
							System.out.println("Breaking: " + bestBreaking);
							if(bus.getMovementState() != -1)
							{
								bus.setMovementState(3);
							}
							
//							int codeBalance = PhysicalCalculations.isBalanced(bus);
//							System.out.println("balance.");
//							switch (codeBalance)
//							{
//							case -1:
//								return PhysicalCalculations.MIN_BREAKING;
//							case 0:
//								return 0;
//							case 1:
//								if(bus.getSpeed() < PhysicalCalculations.MAX_SPEED)
//								{
//									return PhysicalCalculations.ADEQUATE_ACCELERATION;
//								}
//								System.out.println("Very fast.");
//								return 0;
//								
//							case 2:
//								return 0;
//							}
							
							return bestBreaking;
						}
						
						return 0;
					}
				}
			}
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
