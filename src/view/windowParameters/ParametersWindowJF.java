package view.windowParameters;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class ParametersWindowJF extends JFrame {

	private DivisionJSP divisionJSP;
	private ButtonsJP buttonsJP;
	
	public ParametersWindowJF() {
		setTitle("Window Parameters");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setApareance();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void setApareance()
	{
		divisionJSP = new DivisionJSP();
		buttonsJP = new ButtonsJP();
		
		add(divisionJSP, BorderLayout.CENTER);
		add(buttonsJP, BorderLayout.SOUTH);
	}
	
	public void setJTextFieldMouseListener(MouseListener mouseListener)
	{
		divisionJSP.setMouseListener(mouseListener);
	}
	
	public void setButtonsMouseListener(MouseListener mouseListener)
	{
		buttonsJP.setButtonsMouseListener(mouseListener);
	}
	
	public ButtonsJP getButtonsJP()
	{
		return buttonsJP;
	}
	
	public DivisionJSP getDivisionJSP()
	{
		return divisionJSP;
	}
}
