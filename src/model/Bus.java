package model;

import java.io.Serializable;
import java.util.Date;

import util.UtilCalc;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class Bus implements Serializable {

	/**
	 * The Bus serialVersionUID
	 */
	private static final long serialVersionUID = 6817328659194233831L;
	private Driver driver;
	private String id;
	private Station nextStopStation;
	private Date nextStopTime;
	private String plate;
	private double position;
	private Route route;
	private double speed;
	private Date startTime;
	private boolean state;
	private int movementState;
	private double idealSpeed;
	private Object nextNode;
	/**
	 * Creates a Bus with all parameters.
	 * @param id an identifier for the Bus instance
	 * @param driver a Driver instance asociated
	 * @param plate the plate of the Bus instance
	 * @param route the actual route of the Bus instance
	 * @param speed the magnitude of speed asoacited to the Bus instance
	 * @param position the actual point in the plane Bus asociated
	 * @param startTime the time sicen it was added to the simulation
	 * @param nextStopTime the time associated until the next stop
	 * @param state if the Bus is nice or it is damaged 
	 * @param nextStopStation the next node associated to the route
	 */
	public Bus(String id, Driver driver, String plate, Route route, int speed,
			double position, boolean state) {
		this.id = id;
		this.driver = driver;
		this.plate = plate;
		this.route = route;
		this.speed = speed;
		this.position = position;
		this.state = state;
		
		driver = new Driver("Not assigned", "Not assigned", "Not assigned");
		startTime = new Date();
		nextStopTime = new Date();
	}
	
	
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Bus other = (Bus) obj;

		if (plate == null) {
			if (other.id != null)
				return false;
		} else if (!plate.equals(other.id))
			return false;
		
		return true;
	}
	
	public double getIdealSpeed() {
		return idealSpeed;
	}

	public void setIdealSpeed(int idealSpeed) {
		this.idealSpeed = idealSpeed;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public double getMovementState() {
		return movementState;
	}

	public void setMovementState(int movementState) {
		this.movementState = movementState;
	}

	/**
	 * Returns a Driver instance bus asociated.
	 * @return the Driver instace
	 */
	public Driver getDriver()
	{
		return driver;
	}
	
	/**
	 * Returns the id of the Bus instance.
	 * @return the id of the Bus instance.
	 */
	public String getId()
	{
		return id;
	}
	
	/**
	 * Return a Station instance that represent the next stop station.
	 * @return the next stop station.
	 */
	public Station getNextStopStation()
	{
		return nextStopStation;
	}
	
	/**
	 * Returns a Date instance that contains the date until the next stop station.
	 * @return the date until the next stop.
	 */
	public Date getNextStopTime()
	{
		return nextStopTime;
	}
	
	/**
	 * Returns the plate of the Bus instance.
	 * @return the plate of the Bus instance.
	 */
	public String getPlate()
	{
		return plate;
	}
	
	/**
	 * Returns the position asociated to the Bus instance.
	 * @return the double that represent the position in the route. 
	 */
	public double getPosition()
	{
		return position;
	}
	
	/**
	 * Returns a Route instance associated to the Bus instance.
	 * @return the Route instance
	 */
	public Route getRoute()
	{
		return route;
	}
	
	/**
	 * Returns a int that represent the speed value. 
	 * @return the value of speed.
	 */
	public double getSpeed()
	{
		return speed;
	}
	
	/**
	 * Returns the Bus start date in the system.
	 * @return the Bus start date.
	 */
	public Date getStartTime()
	{
		return startTime;
	}
	
	/**
	 * Return the Bus state, if it is nice or it is damaged
	 * @return the state of Bus instance.
	 */
	public boolean getState()
	{
		return state;
	}
	
	@Override
	public int hashCode()
	{
		return (id + ";" + plate).hashCode();
	}
	
	/**
	 * Sets a Driver instance to a Bus instance.
	 * @param driver the Driver instance to be associated to the Bus instance
	 */
	public void setDriver(Driver driver)
	{
		this.driver = driver;
	}
	
	/**
	 * Sets a Station instance as next stop station.
	 * @param nextStopStation the next stop station
	 */
	public void setNextStopStation(Station nextStopStation)
	{
		this.nextStopStation = nextStopStation;
	}
	
	/**
	 * Sets a Date instance that contains the date util the next stop station.
	 * @param nextStopTime the date until the next stop station.
	 */
	public void setNextStopTime(Date nextStopTime)
	{
		this.nextStopTime = nextStopTime;
	}
	
	/**
	 * Set a position to the Bus instance.
	 * @param position
	 */
	public void setPosition(double position)
	{
		this.position = position;
	}
	
	/**
	 * Sets a Route instance to a Bus instance.
	 * @param route the Route instance to be associated to the Bus instance.
	 */
	public void setRoute(Route route)
	{
		this.route = route;
	}

	
	/**
	 * Sets a double as the speed value of Bus instance.
	 * @param speed set a double as speed value.
	 */
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}
	
	/**
	 * Sets the Bus state, if it is nice or it is damaged
	 * @param state the state of Bus instance.
	 */
	public void setState(boolean state)
	{
		this.state = state;
	}
	
	public Object[] toArray()
	{
		if(nextStopStation != null && driver != null)
		{
			Object[] array = {id, driver.getName() + driver.getLastName(), plate, route.getName(), nextStopStation.getName(), state, UtilCalc.round(speed, 2), UtilCalc.round(position, 1)};
			return array;
		}
		else
		{
			if (driver != null) {
				Object[] array = {id, driver.getName(), plate, route.getName(), "Unknowed", state, UtilCalc.round(speed, 2), UtilCalc.round(position, 1)};
				return array;
			}
			else
			{
				Object[] array = {id, "Not asigned", plate, route.getName(), "Unknowed", state, UtilCalc.round(speed, 2), UtilCalc.round(position, 1)};
				return array;
			}
		}
	}
	
	@Override
	public String toString()
	{
		return "id = " + id + ", position = " + position;
		/*
		return "Bus [id = " + id + ", driver = " + driver + ", plate = " + plate
				+ ", route = " + route + ", speed = " + speed + ", position = "
				+ position + ", startTime = " + startTime + ", nextStopTime = "
				+ nextStopTime + ", state = " + state + ", nextStopStation = "
				+ nextStopStation + "]";
				*/
	}

	public Object getNextNode() {
		return nextNode;
	}

	public void setNextNode(Object nextNode) {
		this.nextNode = nextNode;
	}
}
