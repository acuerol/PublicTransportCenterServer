package view.connectionWindow;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Alexis Cuero Losada
 * This class extends of JPanel is used for contain the JTextArea for report the initial values request state.  
 */
public class InformationJP extends JPanel {
	
	private static final long serialVersionUID = 7943850060131601381L;
	
	private JTextArea informationJTA;
	private JScrollPane informationJSP;
	
	/**
	 * Constructor that set elements into the this JPanel.
	 */
	public InformationJP() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setElements();
	}
	
	private void setElements()
	{
		setBorder(BorderFactory.createTitledBorder("Information"));
		
		
		informationJTA = new JTextArea("");
		informationJSP = new JScrollPane(informationJTA);
		
		informationJTA.setEditable(false);
		
		add(informationJSP, BorderLayout.CENTER);
	}
	
	/**
	 * @return the informationJTA instance.
	 */
	public JTextArea getInformationJTA()
	{
		return informationJTA;
	}
}
