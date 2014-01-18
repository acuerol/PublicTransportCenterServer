package view.busesWindow;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Alexis Cuero Losada
 * This class extends of JPanel and allow separate the JButtons with the basics options.
 */
public class ButtonsJP extends JPanel {

	private static final long serialVersionUID = -6197055435512370121L;
	
	private JButton sendBusJB;
	private JButton stopBusJB;
	private JButton closeJB;
	
	/**
	 * Constructor that set the initial attributes.
	 */
	public ButtonsJP() {
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		setElements();
	}

	private void setElements() {
		sendBusJB = new JButton("Send bus");
		stopBusJB = new JButton("Stop bus");
		closeJB = new JButton("Close");
		
		//add(sendBusJB);
		//add(stopBusJB);
		add(closeJB);
	}
	
	/**
	 * Returns the JButton that send a bus.
	 * @return the JButton that send a bus
	 */
	public JButton getSendBusJB() {
		return sendBusJB;
	}

	/**
	 * Returns the JButton that stop a bus.
	 * @return the JButton that stop a bus
	 */
	public JButton getStopBusJB() {
		return stopBusJB;
	}

	/**
	 * Returns the JButton that close the buses window.
	 * @return the JButton that close the buses window
	 */
	public JButton getCloseJB() {
		return closeJB;
	}

	/**
	 * Sets the MouseListener to all JButtons in the panel.
	 * @param mouseListener a class that implements MouseListener for manage the Mouse events.
	 */
	public void setButtonsMouseListener(MouseListener mouseListener)
	{
		//sendBusJB.addMouseListener(mouseListener);
		//stopBusJB.addMouseListener(mouseListener);
		closeJB.addMouseListener(mouseListener);
	}

}
