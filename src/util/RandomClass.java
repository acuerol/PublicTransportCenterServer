package util;

/**
 * @author Alexis Cuero Losada
 *
 */
public class RandomClass {

	/**
	 * Save the upperCase alphabet in a char array. 
	 */
	public static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	/**
	 * Returns a pseudo random double in the interval [min,max].
	 * @param min the minimum value for the random double.
	 * @param max the maximum value for the random double.
	 * @return a random double in the interval [min,max].
	 */
	public static double getRandomDouble(int min, int max)
	{
		return (Math.random()*(max - min + 1) + min);
	}
	
	/**
	 * Returns a pseudo random int in the interval [min,max].
	 * @param min the minimum value for the random int.
	 * @param max the maximum value for the random int.
	 * @return a random int in the interval [min,max].
	 */
	public static int getRandomInt(int min, int max)
	{
		return (int)(Math.random()*(max - min + 1) + min);
	}
	
	/**
	 * Returns a random plate with this format LLL-###. 
	 * @return a random plate.
	 */
	public static String getRandomPlate()
	{
		String plate = getRandomString(3);
		
		plate += "-";
		plate += getRandomInt(0, 9);
		plate += getRandomInt(0, 9);
		plate += getRandomInt(0, 9);
		
		return plate;
	}
	
	/**
	 * Returns a random string with a size of length and random char in each position. 
	 * @param length the size of string.
	 * @return a random string with a length specified.
	 */
	public static String getRandomString(int length)
	{
		String str = "";
		
		for (int i = 0; i < length; i++)
		{
			str += ALPHABET[getRandomInt(0, ALPHABET.length)]; 
		}
		
		return str;
	}
}
