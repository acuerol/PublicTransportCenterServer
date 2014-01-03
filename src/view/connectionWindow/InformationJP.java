package view.connectionWindow;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InformationJP extends JPanel {
	
	private JTextArea informationJTA;
	private JScrollPane informationJSP;
	
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
	
	public void setInformationText(String text)
	{
		informationJTA.setText(text);
	}
	
	public void addInformationText(String text)
	{
		informationJTA.setText(informationJTA.getText() + "\n" + text);
	}

}
