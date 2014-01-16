package view.windowParameters;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainJP extends JPanel {

//	public static final String ROADS_PATH = "Clic here to add roads path";
//	public static final String STATIONS_PATH = "Clic here to add stations path";
//	public static final String SEMAPHORES_PATH = "Clic here to add semaphores path";
//	public static final String ROUTES_PATHS = "Clic here to add routes paths";
	
	public static final String ROADS_PATH = "inputData/Roads.kml";
	public static final String STATIONS_PATH = "inputData/Stations.kml";
	public static final String SEMAPHORES_PATH = "inputData/Semaphores.kml";
	public static final String ROUTES_PATHS = "inputData/E21.txt,inputData/E27.txt,inputData/E31.txt,inputData/E37.txt,inputData/T31.txt,inputData/T40.txt,inputData/T42.txt,inputData/T47A.txt,inputData/T47B.txt,inputData/T50.txt,inputData/T57A.txt";
	
	private JLabel roadsPathJL;
	private JLabel stationsPathJL;
	private JLabel sempahoresPathJL;
	private JLabel routesPathsJL;
	
	private JTextField roadsPathJTF;
	private JTextField stationsPathJTF;
	private JTextField semaphoresPathJTF;
	private JTextField routesPathsJTF;
	
	private JFileChooser roadsJFC;
	
	public MainJP() {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(4, 2, 8, 8));
		setElements();
	}
	
	private void setElements()
	{
		setBorder(BorderFactory.createTitledBorder("Parameters"));
		
		roadsPathJL = new JLabel("Roads path: ");
		stationsPathJL = new JLabel("Stations path: ");
		sempahoresPathJL = new JLabel("Semaphores path: ");
		routesPathsJL  = new JLabel("Routes paths: ");
		
		roadsPathJTF = new JTextField(ROADS_PATH);
		roadsPathJTF.setForeground(Color.GRAY);
		roadsPathJTF.setEditable(false);
		
		stationsPathJTF = new JTextField(STATIONS_PATH);
		stationsPathJTF.setForeground(Color.GRAY);
		stationsPathJTF.setEditable(false);
		
		semaphoresPathJTF = new JTextField(SEMAPHORES_PATH);
		semaphoresPathJTF.setForeground(Color.GRAY);
		semaphoresPathJTF.setEditable(false);
		
		routesPathsJTF = new JTextField(ROUTES_PATHS);
		routesPathsJTF.setForeground(Color.GRAY);
		routesPathsJTF.setEditable(false);
		
		add(roadsPathJL);
		add(roadsPathJTF);
		add(stationsPathJL);
		add(stationsPathJTF);
		add(sempahoresPathJL);
		add(semaphoresPathJTF);
		add(routesPathsJL);
		add(routesPathsJTF);
	}

	public JTextField getRoadsPathJTF() {
		return roadsPathJTF;
	}

	public void setRoadsPathText(String text) {
		roadsPathJTF.setText(text);
	}
	
	public String getRoadsPathText()
	{
		return roadsPathJTF.getText();
	}

	public JTextField getStationsPathJTF() {
		return stationsPathJTF;
	}

	public void setStationsPathText(String text) {
		stationsPathJTF.setText(text);
	}
	
	public String getStationsPathText()
	{
		return stationsPathJTF.getText();
	}

	public JTextField getSemaphoresPathJTF() {
		return semaphoresPathJTF;
	}

	public void setSemaphoresPathText(String text) {
		semaphoresPathJTF.setText(text);
	}

	public String getSemaphoresPathText()
	{
		return semaphoresPathJTF.getText();
	}
	
	public JTextField getRoutesPathsJTF() {
		return routesPathsJTF;
	}

	public void setRoutesPathText(String text) {
		routesPathsJTF.setText(text);
	}
	
	public String getRoutesPathsText()
	{
		return routesPathsJTF.getText();
	}
	
	public void setJTextFieldMouseListener(MouseListener mouseListener)
	{
		roadsPathJTF.addMouseListener(mouseListener);
		stationsPathJTF.addMouseListener(mouseListener);
		semaphoresPathJTF.addMouseListener(mouseListener);
		routesPathsJTF.addMouseListener(mouseListener);
	}
}
