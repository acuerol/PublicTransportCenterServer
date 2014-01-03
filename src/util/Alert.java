package util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class Alert {

	/**
	 * Launches a JOptionPane as Error.
	 * @param message the show message.
	 * @param parent the JFrame that call.
	 */
	public static void launchErrorMessage(String message, JFrame parent)
	{
		JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Launches a JOptionPane as Information.
	 * @param message the show message.
	 * @param parent the JFrame that call.
	 */
	public static void launchInfoMessage(String message, JFrame parent)
	{
		JOptionPane.showMessageDialog(parent, message, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
}
