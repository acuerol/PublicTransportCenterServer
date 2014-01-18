package util;

//References: Distance http://www.juanjosemartinez.com.mx/GIS/files/Calculo_dist_azimuth_planas_new.pdf

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

/**
 * @author Alexis Cuero Losada
 * This class offer several methods for convert the information of files to information easily for manage.
 */
public class UtilCalc {
	
	private static final double EPS = 1e-12;
	private static final double RADIUS = 6371.5;
	
	/**
	 * Returns true if the difference is minor to 1e-12.
	 * @param pointA a point
	 * @param pointB a point
	 * @return true if pointA equal to pointB
	 */
	public static boolean comparePoints(Double pointA, Double pointB)
	{
		if(Math.abs(pointA.getX() - pointB.getX()) < EPS)
		{
			if(Math.abs(pointA.getY() - pointB.getY()) < EPS)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns the addition of distance between all point int the array.
	 * @param points the array with all point in the segment.
	 * @return the addition of distance between all point int the array.
	 */
	public static double distanceArrayGeoPoint(String[] points)
	{
		String[] coordinates;
		Point2D.Double pointA = new Point2D.Double();
		Point2D.Double pointB = new Point2D.Double();
		double distance = 0.0;

		for (int i = 0; i < points.length - 1; i++) {
			coordinates = points[i].split(",");

			pointA.x = java.lang.Double.parseDouble(coordinates[0]);
			pointA.y = java.lang.Double.parseDouble(coordinates[1]);

			coordinates = points[i+1].split(",");
			
			pointB.x = java.lang.Double.parseDouble(coordinates[0]);
			pointB.y = java.lang.Double.parseDouble(coordinates[1]);

			distance += distanceTwoGeoPoint(pointA, pointB);
		}
		
		return distance;
	}
	
	/**
	 * Returns the calculation of the geographical distance between two points.
	 * @param pointA the point from start the segment. 
	 * @param pointB the point from ends the segment.
	 * @return the distance between two points.
	 */
	public static double distanceTwoGeoPoint(Double pointA, Double pointB)
	{
		double latA = Math.toRadians(pointA.getX());
		double latB = Math.toRadians(pointB.getX());
		double dLon = Math.abs(Math.toRadians(pointA.getY()) - Math.toRadians(pointB.getY()));
		return (RADIUS * (Math.acos( Math.sin( latA ) * Math.sin( latB ) + Math.cos( latA ) * Math.cos( latB ) * Math.cos( dLon )))); 
	}
	
	/**
	 * Returns a double with the number of digits.
	 * @param number the number to round.
	 * @param digits the number of decimal.
	 * @return the number round.
	 */
	public static double round(double number, int digits)
	{
		int digitsNum = (int) Math.pow(10, digits);
		number = Math.rint(number * digitsNum) / digitsNum;
		
		return number;
	}
	
	/**
	 * Returns true if the numbers are equal.
	 * @param a the first double for compare.
	 * @param b the second double for compare.
	 * @return if the numbers are equal.
	 */
	public static boolean compareDouble(double a, double b)
	{
		final double EPSILON = 1E-2;
		
		if(Math.abs(a - b) < EPSILON)
		{
			return true;
		}
		
		return false;
	}
}