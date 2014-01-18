package controller.parametersWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

import controller.CentralSystem;
import view.windowParameters.MainJP;

/**
 * @author Alexis Cuero Losada
 * This class implements {@link MouseListener} for handle the Mouse events in {@link MainJP}.
 */
public class ParametersWindowJTextFieldML implements MouseListener {
	
	@Override
	public void mouseClicked(MouseEvent event) {
		ParametersWindowController parametersWindowController = CentralSystem.getCentralSystem().getWindowParametersController();
		MainJP mainJP = parametersWindowController.getWindowParameters().getDivisionJSP().getMainJP();
		JTextField source = (JTextField) event.getSource();
		String pathNames = "";
		if(source.equals(mainJP.getRoadsPathJTF()))
		{
			pathNames = parametersWindowController.openFileChooser("KML Files", "kml", false);
			
			if(pathNames != "")
			{
				mainJP.getRoadsPathJTF().setText(pathNames);
			}
		}
		else
		{
			if(source.equals(mainJP.getStationsPathJTF()))
			{
				pathNames = parametersWindowController.openFileChooser("KML Files", "kml", false);
				if(pathNames != "")
				{
					mainJP.getStationsPathJTF().setText(pathNames);
				}
			}
			else
			{
				if(source.equals(mainJP.getSemaphoresPathJTF()))
				{
					pathNames = parametersWindowController.openFileChooser("KML Files", "kml", false);
					if(pathNames != "")
					{
						mainJP.getSemaphoresPathJTF().setText(pathNames);
					}
				}
				else
				{
					if(source.equals(mainJP.getRoutesPathsJTF()))
					{
						pathNames = parametersWindowController.openFileChooser("TXT Files", "txt", true);
						if(pathNames != "")
						{
							mainJP.getRoutesPathsJTF().setText(pathNames);
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
