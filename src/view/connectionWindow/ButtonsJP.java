package view.connectionWindow;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ButtonsJP extends JPanel {
	public static final String ON_REQUEST_LISTENER = "on Request Listener";
	public static final String OFF_REQUEST_LISTENER = "off Request Listener";
	
	private JToggleButton startRequestListenerJTB;
	private JButton showBusesJB;
	
	public ButtonsJP() {
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		setElements();
	}
	
	private void setElements()
	{
		startRequestListenerJTB = new JToggleButton(ON_REQUEST_LISTENER);
		startRequestListenerJTB.setActionCommand(ON_REQUEST_LISTENER);
		showBusesJB = new JButton("Show Buses");
		
		add(startRequestListenerJTB);
		add(showBusesJB);
	}
	
	public void changeActionCommandRequestListener()
	{
		if(startRequestListenerJTB.getActionCommand().equals(OFF_REQUEST_LISTENER))
		{
			startRequestListenerJTB.setActionCommand(ON_REQUEST_LISTENER);
		}
		else
		{
			startRequestListenerJTB.setActionCommand(OFF_REQUEST_LISTENER);
		}
	}
	
	public JToggleButton getStartRequestListenerJTB()
	{
		return startRequestListenerJTB;
	}
	
	public void setJButtonsMouseListener(MouseListener mouseListener)
	{
		startRequestListenerJTB.addMouseListener(mouseListener);
		showBusesJB.addMouseListener(mouseListener);
	}

	public Object getShowBusesJB()
	{
		return showBusesJB;
	}
}
