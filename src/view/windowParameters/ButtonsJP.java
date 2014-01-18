package view.windowParameters;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Alexis Cuero Losada
 * This class extends of JPanel is used for contain the JButtons for load fields and start the system.  
 */
public class ButtonsJP extends JPanel {
	
	private static final long serialVersionUID = 5484426364321354659L;
	
	private JButton loadParametersJB;
	private JButton runSystemJB;
	
	/**
	 * Constructor that instance the elements and add to the this JPanel.
	 */
	public ButtonsJP() {
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		setElements();
	}
	
	private void setElements()
	{
		loadParametersJB = new JButton("Load parameters");
		
		runSystemJB = new JButton("Run system");
		
		add(loadParametersJB);
		add(runSystemJB);
	}
	
	/**
	 * Sets the MouseListener for handle the mouseEvents.
	 * @param mouseListener the MouseListener for handle the mouseEvents
	 */
	public void setButtonsMouseListener(MouseListener mouseListener)
	{
		loadParametersJB.addMouseListener(mouseListener);
		runSystemJB.addMouseListener(mouseListener);
	}

	/**
	 * @return the LoadParametersJB instance
	 */
	public JButton getLoadParametersJB() {
		return loadParametersJB;
	}

	/**
	 * @return The RunSystemJB instance.
	 */
	public JButton getRunSystemJB() {
		return runSystemJB;
	}

	
	
}
