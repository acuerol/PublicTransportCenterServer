package view.busesWindow;

import java.awt.FlowLayout;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BusesWindowButtonsJP extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6197055435512370121L;
	private JButton sendBusJB;
	private JButton stopBusJB;
	private JButton closeJB;
	
	public BusesWindowButtonsJP() {
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
	
	public JButton getSendBusJB() {
		return sendBusJB;
	}

	public JButton getStopBusJB() {
		return stopBusJB;
	}

	public JButton getCloseJB() {
		return closeJB;
	}

	public void setButtonsMouseListener(MouseListener mouseListener)
	{
		//sendBusJB.addMouseListener(mouseListener);
		//stopBusJB.addMouseListener(mouseListener);
		closeJB.addMouseListener(mouseListener);
	}

}
