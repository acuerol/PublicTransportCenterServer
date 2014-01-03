package controller.parametersWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

import controller.CentralSystem;
import view.windowParameters.MainJP;

public class ParametersWindowJTextFieldML implements MouseListener {
	private ParametersWindowController parametersWindowController;
	private MainJP mainJP;

	@Override
	public void mouseClicked(MouseEvent event) {
		parametersWindowController = CentralSystem.getCentralSystem().getWindowParametersController();
		mainJP = parametersWindowController.getWindowParameters().getDivisionJSP().getMainJP();
		JTextField source = (JTextField) event.getSource();
		String pathNames = "";
		if(source.equals(mainJP.getRoadsPathJTF()))
		{
			pathNames = parametersWindowController.openFileChooser("KML Files", "kml", false);
			
			if(pathNames != "")
			{
				mainJP.setRoadsPathText(pathNames);
			}
		}
		else
		{
			if(source.equals(mainJP.getStationsPathJTF()))
			{
				pathNames = parametersWindowController.openFileChooser("KML Files", "kml", false);
				if(pathNames != "")
				{
					mainJP.setStationsPathText(pathNames);
				}
			}
			else
			{
				if(source.equals(mainJP.getSemaphoresPathJTF()))
				{
					pathNames = parametersWindowController.openFileChooser("KML Files", "kml", false);
					if(pathNames != "")
					{
						mainJP.setSemaphoresPathText(pathNames);
					}
				}
				else
				{
					if(source.equals(mainJP.getRoutesPathsJTF()))
					{
						pathNames = parametersWindowController.openFileChooser("TXT Files", "txt", true);
						if(pathNames != "")
						{
							mainJP.setRoutesPathText(pathNames);
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
