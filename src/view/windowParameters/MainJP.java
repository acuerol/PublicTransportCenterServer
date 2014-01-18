package view.windowParameters;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Alexis Cuero Losada
 * This class extends of JPanel and contain the principal elemtns for get the files information for load th system
 */
public class MainJP extends JPanel {

//	public static final String ROADS_PATH = "Clic here to add roads path";
//	public static final String STATIONS_PATH = "Clic here to add stations path";
//	public static final String SEMAPHORES_PATH = "Clic here to add semaphores path";
//	public static final String ROUTES_PATHS = "Clic here to add routes paths";

	private static final long serialVersionUID = 4410717008673234069L;
	/**
	 * Text in the roadsPathJTF.
	 */
	public static final String ROADS_TEXT = "inputData/Roads.kml";
	/**
	 * Text in the stationsPathJTF.
	 */
	public static final String STATIONS_TEXT = "inputData/Stations.kml";
	/**
	 * Text in the semaphoresPathJTF.
	 */
	public static final String SEMAPHORES_TEXT = "inputData/Semaphores.kml";
	/**
	 * Text in the routesPathJTF.
	 */
	public static final String ROUTES_TEXT = "inputData/E21.txt,inputData/E27.txt,inputData/E31.txt,inputData/E37.txt,inputData/T31.txt,inputData/T40.txt,inputData/T42.txt,inputData/T47A.txt,inputData/T47B.txt,inputData/T50.txt,inputData/T57A.txt";
	
	private JLabel roadsPathJL;
	private JLabel stationsPathJL;
	private JLabel sempahoresPathJL;
	private JLabel routesPathsJL;
	
	private JTextField roadsPathJTF;
	private JTextField stationsPathJTF;
	private JTextField semaphoresPathJTF;
	private JTextField routesPathsJTF;
		
	/**
	 * Instances all elements in this JPanel.
	 */
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
		
		roadsPathJTF = new JTextField(ROADS_TEXT);
		roadsPathJTF.setForeground(Color.GRAY);
		roadsPathJTF.setEditable(false);
		
		stationsPathJTF = new JTextField(STATIONS_TEXT);
		stationsPathJTF.setForeground(Color.GRAY);
		stationsPathJTF.setEditable(false);
		
		semaphoresPathJTF = new JTextField(SEMAPHORES_TEXT);
		semaphoresPathJTF.setForeground(Color.GRAY);
		semaphoresPathJTF.setEditable(false);
		
		routesPathsJTF = new JTextField(ROUTES_TEXT);
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

	/**
	 * @return the JTextField that stores the roads path.
	 */
	public JTextField getRoadsPathJTF() {
		return roadsPathJTF;
	}

	/**
	 * @return the JTextField that stores the stations path.
	 */
	public JTextField getStationsPathJTF() {
		return stationsPathJTF;
	}

	/**
	 * @return the JTextField that stores the semaphores path.
	 */
	public JTextField getSemaphoresPathJTF() {
		return semaphoresPathJTF;
	}
	
	/**
	 * @return the JTextField that stores the routes paths.
	 */
	public JTextField getRoutesPathsJTF() {
		return routesPathsJTF;
	}
	
	/**
	 * Adds a MouseListener to the JButtons.
	 * @param mouseListener the MouseListener for connectJB.
	 */
	public void setJTextFieldMouseListener(MouseListener mouseListener)
	{
		roadsPathJTF.addMouseListener(mouseListener);
		stationsPathJTF.addMouseListener(mouseListener);
		semaphoresPathJTF.addMouseListener(mouseListener);
		routesPathsJTF.addMouseListener(mouseListener);
	}
}
