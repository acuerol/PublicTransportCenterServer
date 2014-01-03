package model;

import java.io.Serializable;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class Driver implements Serializable {

	/**
	 * The Driver serialVersionUID
	 */
	private static final long serialVersionUID = 198394087244398692L;
	private String id;
	private String lastName;
	private String name;
	
	/**
	 * Creates a Drvier with all parameters-
	 * @param id the driver identification document.
	 * @param name the driver name
	 * @param lastName the driver last name.
	 */
	public Driver(String id, String name, String lastName) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
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
		
		Driver other = (Driver) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		
		return true;
	}

	/**
	 * Returns the driver ID. 
	 * @return the driver ID.
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Returns the driver last name.
	 * @return the driver last name.
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Returns the driver name.
	 * @return the driver name.
	 */
	public String getName()
	{
		return name;
	}
	
	@Override
	public int hashCode()
	{
		return (id + name + lastName).hashCode();
	}

	@Override
	public String toString()
	{
		return "Driver [id = " + id + ", name = " + name + ", lastName = " + lastName
				+ "]";
	}
}
