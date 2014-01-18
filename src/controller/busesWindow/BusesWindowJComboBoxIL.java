package controller.busesWindow;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import controller.CentralSystem;

/**
 * @author Alexis Cuero Losada
 * This class implements ItemListener and handle the event of the JComboBox for filter
 * the buses shown in the BusesWindowJF.
 */
public class BusesWindowJComboBoxIL implements ItemListener {

	@Override
	public void itemStateChanged(ItemEvent e) {
		CentralSystem centralSystem = CentralSystem.getCentralSystem(); 
		centralSystem.getBusesWindowController().refreshTable(-1);
	}

}
