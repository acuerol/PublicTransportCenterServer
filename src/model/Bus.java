package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import util.UtilCalc;

/**
 * @author Alexis Cuero Losada
 * Abstracts the real world buses with attributes as the id, the driver, plate, position, speed...
 */
public class Bus implements Serializable, Comparable<Bus> {

	private static final long serialVersionUID = 6817328659194233831L;
	
	private Driver driver;
	private String id;
	private Station nextStopStation;
	private Calendar nextStopTime;
	private Object nextNode;
	private String plate;
	private double position;
	private Route route;
	private double speed;
	private Calendar startTime;
	private boolean state;
	private int movementState;
	private double acceleration;
	private int stopTime;

	/**
	 * Creates a Bus with all parameters.
	 * 
	 * @param id
	 *            an identifier for the Bus instance
	 * @param driver
	 *            a Driver instance associated
	 * @param plate
	 *            the plate of the Bus instance
	 * @param route
	 *            the actual route of the Bus instance
	 * @param speed
	 *            the magnitude of speed associated to the Bus instance
	 * @param position
	 *            the actual point in the plane Bus associated
	 * @param state
	 *            if the Bus is nice or it is damaged
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
		startTime = new GregorianCalendar();
		nextStopTime = new GregorianCalendar();
		acceleration = 0;
		movementState = 99;
		stopTime = 0;
	}

	/**
	 * @return the stopTime
	 */
	public int getStopTime() {
		return stopTime;
	}

	/**
	 * @param stopTime
	 *            the stopTime to set
	 */
	public void setStopTime(int stopTime) {
		this.stopTime = stopTime;
	}

	/**
	 * @return the acceleration
	 */
	public double getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration
	 *            the acceleration to set
	 */
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	/**
	 * Sets the start time.
	 * @param calendar the start time.
	 */
	public void setStartTime(Calendar calendar) {
		this.startTime = calendar;
	}

	/**
	 * Returns the movement state of the bus.
	 * @return the movement state of the bus.
	 */
	public int getMovementState() {
		return movementState;
	}

	/**
	 * Sets the movement state of the bus.
	 * @param movementState the new movement state of the bus.
	 */
	public void setMovementState(int movementState) {
		this.movementState = movementState;
	}

	/**
	 * Returns a Driver instance bus associated.
	 * 
	 * @return the Driver instance
	 */
	public Driver getDriver() {
		return driver;
	}

	/**
	 * Returns the id of the Bus instance.
	 * 
	 * @return the id of the Bus instance.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Return a Station instance that represent the next stop station.
	 * 
	 * @return the next stop station.
	 */
	public Station getNextStopStation() {
		return nextStopStation;
	}

	/**
	 * Returns a Date instance that contains the date until the next stop
	 * station.
	 * 
	 * @return the date until the next stop.
	 */
	public Calendar getNextStopTime() {
		return nextStopTime;
	}

	/**
	 * Returns the plate of the Bus instance.
	 * 
	 * @return the plate of the Bus instance.
	 */
	public String getPlate() {
		return plate;
	}

	/**
	 * Returns the position asociated to the Bus instance.
	 * 
	 * @return the double that represent the position in the route.
	 */
	public double getPosition() {
		return position;
	}

	/**
	 * Returns a Route instance associated to the Bus instance.
	 * 
	 * @return the Route instance
	 */
	public Route getRoute() {
		return route;
	}

	/**
	 * Returns a int that represent the speed value.
	 * 
	 * @return the value of speed.
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Returns the Bus start date in the system.
	 * 
	 * @return the Bus start date.
	 */
	public Calendar getStartTime() {
		return startTime;
	}

	/**
	 * Return the Bus state, if it is nice or it is damaged
	 * 
	 * @return the state of Bus instance.
	 */
	public boolean getState() {
		return state;
	}

	/**
	 * Sets a Driver instance to a Bus instance.
	 * 
	 * @param driver
	 *            the Driver instance to be associated to the Bus instance
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	/**
	 * Sets a Station instance as next stop station.
	 * 
	 * @param nextStopStation
	 *            the next stop station
	 */
	public void setNextStopStation(Station nextStopStation) {
		this.nextStopStation = nextStopStation;
	}

	/**
	 * Sets a Date instance that contains the date util the next stop station.
	 * 
	 * @param nextStopTime
	 *            the date until the next stop station.
	 */
	public void setNextStopTime(Calendar nextStopTime) {
		this.nextStopTime = nextStopTime;
	}

	/**
	 * Set a position to the Bus instance.
	 * 
	 * @param position
	 */
	public void setPosition(double position) {
		this.position = position;
	}

	/**
	 * Sets a Route instance to a Bus instance.
	 * 
	 * @param route
	 *            the Route instance to be associated to the Bus instance.
	 */
	public void setRoute(Route route) {
		this.route = route;
	}

	/**
	 * Sets a double as the speed value of Bus instance.
	 * 
	 * @param speed
	 *            set a double as speed value.
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Sets the Bus state, if it is nice or it is damaged
	 * 
	 * @param state
	 *            the state of Bus instance.
	 */
	public void setState(boolean state) {
		this.state = state;
	}

	/**
	 * Returns a Object[] representation of the bus with the main attributes.
	 * @return Object[] representation of the bus
	 */
	public Object[] toArray() {
		if (nextStopStation != null && driver != null) {
			Object[] array = {id, driver.getName() + driver.getLastName(),
					plate, route.getName(), nextStopStation.getName(), state,
					UtilCalc.round(speed * 3.6, 2),
					UtilCalc.round(position, 2), nextNode, movementState,
					UtilCalc.round(acceleration, 2)};
			return array;
		} else {
			if (driver != null) {
				Object[] array = {id, driver.getName() + driver.getLastName(),
						plate, route.getName(), "Unknowed", state,
						UtilCalc.round(speed * 3.6, 2),
						UtilCalc.round(position, 2), nextNode, movementState,
						UtilCalc.round(acceleration, 2)};
				return array;
			} else {
				if(nextStopStation != null) {
					Object[] array = {id, "Not asigned", plate, route.getName(),
							nextStopStation.getName(), state,
							UtilCalc.round(speed * 3.6, 2),
							UtilCalc.round(position, 2), nextNode, movementState,
							UtilCalc.round(acceleration, 2)};
					return array;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the bus nextNode at the road. 
	 * @return the bus nextNode
	 */
	public Object getNextNode() {
		return nextNode;
	}

	/**
	 * Sets the bus nextNode at the road. 
	 * @param nextNode the bus nextNode.
	 */
	public void setNextNode(Object nextNode) {
		this.nextNode = nextNode;
	}
	
	@Override
	public int hashCode() {
		return (id + ";" + plate).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Bus other = (Bus) obj;

		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (plate == null) {
			if (other.plate != null)
				return false;
		} else if (!plate.equals(other.plate))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "id = " + id + ", position = " + position;
		/*
		 * return "Bus [id = " + id + ", driver = " + driver + ", plate = " +
		 * plate + ", route = " + route + ", speed = " + speed + ", position = "
		 * + position + ", startTime = " + startTime + ", nextStopTime = " +
		 * nextStopTime + ", state = " + state + ", nextStopStation = " +
		 * nextStopStation + "]";
		 */
	}

	@Override
	public int compareTo(Bus other) {
		if (this.getPosition() < other.getPosition()) {
			return -1;
		}

		if (this.getPosition() > other.getPosition()) {
			return 1;
		}

		return 0;
	}
}
