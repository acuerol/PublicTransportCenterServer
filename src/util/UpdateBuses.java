package util;

import java.awt.geom.Point2D;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;

import javax.swing.text.html.MinimalHTMLWriter;

import controller.CentralSystem;
import model.Bus;
import model.Semaphore;
import model.Station;

public class UpdateBuses {

	public static final Station UNKNOWED = new Station("Unknowed", new Point2D.Double(), false); 
	public static final double STOP_TOLERANCE = 2e-2;
	
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
				return distances.get(index) + distance;
			}
		}
		
		return Double.NaN;
	}

	public static double getOptimalAcceleration(Bus bus) {
		Object nextNode = bus.getNextNode(); 
		double nodeDistance = getNodeDistance(bus);
		
		if(nextNode instanceof Semaphore)
		{
			double avaibleTime = PhysicalCalculations.getTimeAvaible(((Semaphore)(nextNode)));
			
			switch (PhysicalCalculations.conditionToPass(bus, nodeDistance, avaibleTime))
			{
			case 1:
				bus.setMovementState(1);
				return PhysicalCalculations.ADEQUATE_ACCELERATION;
			case 2:
				bus.setMovementState(2);
				return 0;
			case 3:
				bus.setMovementState(3);
				return PhysicalCalculations.bestAcceleration(bus, nodeDistance, avaibleTime);
			case 4:
				bus.setMovementState(0);
				return PhysicalCalculations.ADEQUATE_ACCELERATION;
			}
			
			switch (PhysicalCalculations.conditionToBreaking(bus, nodeDistance, avaibleTime)) 
			{
			case 0:
				bus.setMovementState(0);
				return PhysicalCalculations.ADEQUATE_ACCELERATION;
			case 4:
				bus.setMovementState(5);
				return 0;
			case 5:
				bus.setMovementState(6);
				return PhysicalCalculations.bestBreaking(bus, nodeDistance);
			case 6:
				bus.setMovementState(7);
				return 0;
			}
			
			return PhysicalCalculations.ADEQUATE_ACCELERATION;
		}
//		else
//		{
//			if(!nextNode.equals(UNKNOWED))
//			{
//				double bestBreaking = PhysicalCalculations.bestBreaking(bus, nodeDistance);
//				
//				if(bestBreaking < PhysicalCalculations.MIN_BREAKING)
//				{
//					int codeBalance = PhysicalCalculations.isBalanced(bus);
//					
//					switch (codeBalance)
//					{
//					case -1:
//						return PhysicalCalculations.MIN_BREAKING;
//					case 0:
//						return 0;
//					case 1:
//						return PhysicalCalculations.ADEQUATE_ACCELERATION;
//					case 2:
//						return 0;
//					}
//				}
//				
//				return bestBreaking;
//			}
//		}
		
		return Double.NaN;
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
