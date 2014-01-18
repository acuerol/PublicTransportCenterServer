package view.windowParameters;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

/**
 * @author Alexis Cuero Losada
 * This class extends of JFrame and contain all parameters for start the system.
 */
public class ParametersWindowJF extends JFrame {

	private static final long serialVersionUID = -3664329424761920409L;
	
	private DivisionJSP divisionJSP;
	private ButtonsJP buttonsJP;
	
	/**
	 * Constructor for instance and add all elements.
	 */
	public ParametersWindowJF() {
		setTitle("Window Parameters");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setApareance();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	private void setApareance()
	{
		setBackground(Color.WHITE);
		divisionJSP = new DivisionJSP();
		buttonsJP = new ButtonsJP();
		
		add(divisionJSP, BorderLayout.CENTER);
		add(buttonsJP, BorderLayout.SOUTH);
	}
	
	/**
	 * Sets the MouseListener for handle the mouse events on the JTextFields.
	 * @param mouseListener the class that handle the mouse events
	 */
	public void setJTextFieldMouseListener(MouseListener mouseListener)
	{
		divisionJSP.setMouseListener(mouseListener);
	}
	
	/**
	 * Sets the MouseListener for handle the mouse events.
	 * @param mouseListener the class that handle the mouse events
	 */
	public void setButtonsMouseListener(MouseListener mouseListener)
	{
		buttonsJP.setButtonsMouseListener(mouseListener);
	}
	
	/**
	 * @return the {@link ButtonsJP} instance.
	 */
	public ButtonsJP getButtonsJP()
	{
		return buttonsJP;
	}
	
	/**
	 * @return the {@link DivisionJSP} instance.
	 */
	public DivisionJSP getDivisionJSP()
	{
		return divisionJSP;
	}
}
