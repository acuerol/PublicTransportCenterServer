package view.windowParameters;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

import view.connectionWindow.ConnectionWindowJF;

/**
 * @author Alexis Cuero Losada
 * This class extends of JSplitPanel and is use for adjust the element to the {@link ConnectionWindowJF}.
 */
public class DivisionJSP extends JSplitPane {
	
	private static final long serialVersionUID = 8097389888719751189L;

	/**
	 * The split position.
	 */
	public static final int SPLIT_POSITION = 200;
	
	private MainJP mainJP;
	private JLabel imageJL;
	
	/**
	 * Instance the elements in this JSplitPanel. 
	 */
	public DivisionJSP() {
		setBackground(Color.WHITE);
		setOrientation(JSplitPane.VERTICAL_SPLIT);
		setDividerLocation(SPLIT_POSITION);
		setElements();
	}
	
	private void setElements()
	{
		imageJL = new JLabel();
		mainJP = new MainJP();
		
		imageJL.setIcon(new ImageIcon("images/parameters.png"));
		
		setRightComponent(mainJP);
		setLeftComponent(imageJL);
	}
	
	/**
	 * @param mouseListener Sets the JButtons MouseListener for handle the mouse events.
	 */
	public void setMouseListener(MouseListener mouseListener)
	{
		mainJP.setJTextFieldMouseListener(mouseListener);
	}
	
	/**
	 * Returns the {@link MainJP} instance create in this class.
	 * @return the {@link MainJP} instance
	 */
	public MainJP getMainJP()
	{
		return mainJP;
	}
}
