package view.busesWindow;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PublicTransportCenter;


/**
 * @author Alexis Cuero Losada
 * Class that extends of JPanel for contain the JComboBox for select the route to filter buses in the JTable.
 */
public class ToolsJP extends JPanel {

	private static final long serialVersionUID = 2057709326942459033L;
	private JLabel selectRoute;
	private JComboBox<String> routesJCB; 
	
	/**
	 * Constructor that set the elements into the this JPanel.  
	 */
	public ToolsJP() {
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		setElements();
	}
	
	private void setElements()
	{
		selectRoute = new JLabel("Select route: ");
		ArrayList<String> routesNames = new ArrayList<String>();
		routesNames.add("All routes");
		routesNames.addAll(PublicTransportCenter.getPublicTransportCenter().getRoutesName());
		
		routesJCB = new JComboBox<String>(routesNames.toArray(new String[0]));
		routesJCB.setSelectedItem("All routes");
		
		add(selectRoute);
		add(routesJCB);
	}
	
	/**
	 * Returns routesJCB that contains the name routes of the system.
	 * @return the routesJCB
	 */
	public JComboBox<String> getRoutesJCB() {
		return routesJCB;
	}

	/**
	 * Sets the ItemListener for refresh the busesJT in {@link TableJP}.
	 * @param itemListener the class for manage the item events.
	 */
	public void setJComboBoxItemListener(ItemListener itemListener)
	{
		routesJCB.addItemListener(itemListener);
	}
}
