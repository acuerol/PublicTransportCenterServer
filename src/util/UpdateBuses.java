package util;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import model.Bus;
import model.Semaphore;
import model.Station;

public class UpdateBuses {

	public static final Station UNKNOWED = new Station("Unknowed", new Point2D.Double(), false); 
	
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
		double acceleration = 0;
		
		if(nextNode instanceof Semaphore)
		{
			double avaibleTime = 0.0;
			Semaphore semaphore = ((Semaphore)(nextNode));
			avaibleTime = PhysicalCalculations.getTimeAvaible(semaphore);
			
			if(semaphore.getState())
			{
				if(PhysicalCalculations.isPossiblePassGreen(bus, getNodeDistance(bus), avaibleTime))
				{
					
				}
			}
			else
			{
				if(PhysicalCalculations.isPossiblePassRed(bus, getNodeDistance(bus), avaibleTime))
				{
					
				}
			}
		}
		
		return 0;
	}
}
