package view.connectionWindow;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import controller.connectionWindow.ConnectionWindowJButtonsML;
import model.PublicTransportCenter;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class ConnectionWindowJF extends JFrame {
	
	private PublicTransportCenter pTC;
	private ButtonsJP buttonsJP;
	private InformationJP informationJP;
	
	public ConnectionWindowJF() {
		pTC = PublicTransportCenter.getPublicTransportCenter();
		setTitle("Connection Window");
		setLayout(new BorderLayout());
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setApareance();
		setVisible(true);
	}
	
	private void setApareance()
	{
		buttonsJP = new ButtonsJP();
		informationJP = new InformationJP();
		
		add(informationJP, BorderLayout.CENTER);
		add(buttonsJP, BorderLayout.SOUTH);
	}
	
	public ButtonsJP getButtonsJP() {
		return buttonsJP;
	}
	
	public void setJButtonsMouseListeners()
	{
		ConnectionWindowJButtonsML mouseListener = new ConnectionWindowJButtonsML();
		buttonsJP.setJButtonsMouseListener(mouseListener);
		mouseListener.start();
	}
	
	public void changeActionCommand()
	{
		buttonsJP.changeActionCommandRequestListener();
	}
	
	public void setInformationText(String text)
	{
		informationJP.setInformationText(text);
	}
	
	public void addInformationText(String text)
	{
		informationJP.addInformationText(text);
	}
}
