package view.connectionWindow;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

import controller.connectionWindow.ConnectionWindowJButtonsML;

/**
 * @author Alexis Cuero Losada
 * This class extends of JFrame and is the GUI for show the connection options and state. 
 */
public class ConnectionWindowJF extends JFrame {
	
	private static final long serialVersionUID = 6051612785450216929L;
	
	private ButtonsJP buttonsJP;
	private InformationJP informationJP;
	
	/**
	 * Create a MainWindowJF instance, instance and add elements to the JFrame.
	 */
	public ConnectionWindowJF() {
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
		setBackground(Color.WHITE);
		buttonsJP = new ButtonsJP();
		informationJP = new InformationJP();
		
		add(informationJP, BorderLayout.CENTER);
		add(buttonsJP, BorderLayout.SOUTH);
	}
	
	/**
	 * Returns the {@link ButtonsJP} instance creates in this class.
	 * @return the {@link ButtonsJP} instance
	 */
	public ButtonsJP getButtonsJP() {
		return buttonsJP;
	}
	
	/**
	 * Sets the MouseListener to the buttons in {@link ButtonsJP}.
	 */
	public void setJButtonsMouseListeners()
	{
		ConnectionWindowJButtonsML mouseListener = new ConnectionWindowJButtonsML();
		buttonsJP.setJButtonsMouseListener(mouseListener);
//		mouseListener.start();
	}

	/**
	 * @return The {@link InformationJP} instance.
	 */
	public InformationJP getInformationJP() {
		return informationJP;
	}

	/**
	 * Adds text to the JTextArea in {@link InformationJP}.
	 * @param string text to add
	 */
	public void addInformationText(String string) {
		String backText = informationJP.getInformationJTA().getText() + "\n";
		informationJP.getInformationJTA().setText(backText + string);
	}
}
