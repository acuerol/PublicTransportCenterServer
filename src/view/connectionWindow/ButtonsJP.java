package view.connectionWindow;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * @author Alexis Cuero Losada
 * This class extends of JPanel is used for contain the JButtons for connect, start the system and close the program.  
 */
public class ButtonsJP extends JPanel {
	
	private static final long serialVersionUID = -3827738975832649076L;
	private JToggleButton startRequestListenerJTB;
	private JButton showBusesJB;
	
	/**
	 * Constructor that set elements into the this JPanel.
	 */
	public ButtonsJP() {
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		setElements();
	}
	
	private void setElements()
	{
		startRequestListenerJTB = new JToggleButton("On Request Listener");
		startRequestListenerJTB.setActionCommand("Off Request Listener");
		showBusesJB = new JButton("Show Buses");
		
		add(startRequestListenerJTB);
		add(showBusesJB);
	}
	
	/**
	 * Returns the JToggleButton for star and stop the initial values listen.
	 * @return the JToggleButton instance.
	 */
	public JToggleButton getStartRequestListenerJTB()
	{
		return startRequestListenerJTB;
	}
	
	/**
	 * Adds a MouseListener to the JButtons.
	 * @param mouseListener the MouseListener for connectJB.
	 */
	public void setJButtonsMouseListener(MouseListener mouseListener)
	{
		startRequestListenerJTB.addMouseListener(mouseListener);
		showBusesJB.addMouseListener(mouseListener);
	}

	/**
	 * @return the JButton showBusesJB
	 */
	public Object getShowBusesJB()
	{
		return showBusesJB;
	}
}
