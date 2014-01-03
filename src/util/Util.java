package util;

import java.io.File;

/**
 * @author Alexis Cuero Losada
 *
 */
public class Util {

	/**
	 * Returns true if a string is a integer.
	 * @param string a possible integer. 
	 * @return true if the string is a integer.
	 */
	public static boolean isInteger(String string)
	{
		char[] characters = string.toCharArray();
		
		for (char ch : characters) {
			if(!Character.isDigit(ch))
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Returns true if the plate (String plate) is valid LLL-###. 
	 * @param plate the plate must be validate.
	 * @return true if is a plate else false.
	 */
	public static boolean isPlate(String plate) {
		
		if(plate.length() == 7)
		{
			char[] charPlate = plate.toCharArray();
			
			for (int i = 0; i < charPlate.length; i++) {
				if(i < 3)
				{
					if(!Character.isLetter(charPlate[i]))
					{
						return false;
					}
				}
				else
				{
					if(i == 3)
					{
						if(charPlate[i] != '-')
						{
							return false;
						}
					}
					else
					{
						if(!Character.isDigit(charPlate[i]))
						{
							return false;
						}
					}
				}
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}
	
	public static String getAbsolutePaths(File[] files)
	{
		String pathNames = "";
		
		for (int i = 0 ; i < files.length ; i++) {
			
			pathNames += files[i].getAbsolutePath();
			
			if(i < files.length - 1)
			{
				pathNames += ",";
			}
			
		}
		
		return pathNames;
	}
}
