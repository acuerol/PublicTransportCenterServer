package view.windowParameters;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

public class DivisionJSP extends JSplitPane {
	
	public static final int SPLIT_POSITION = 200;
	
	private MainJP mainJP;
	private JLabel imageJL;
	
	public DivisionJSP() {
		setOrientation(JSplitPane.VERTICAL_SPLIT);
		setDividerLocation(SPLIT_POSITION);
		setElements();
	}
	
	private void setElements()
	{
		imageJL = new JLabel("Image...");
		mainJP = new MainJP();
		
		setRightComponent(mainJP);
		setLeftComponent(imageJL);
	}
	
	public void setMouseListener(MouseListener mouseListener)
	{
		mainJP.setJTextFieldMouseListener(mouseListener);
	}
	
	public MainJP getMainJP()
	{
		return mainJP;
	}

}
