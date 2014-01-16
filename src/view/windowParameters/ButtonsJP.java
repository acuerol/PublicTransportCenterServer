package view.windowParameters;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsJP extends JPanel {
	public static final String LOAD_PARAMETERS= "Load parameters";
	public static final String CLEAN = "Clean fields";
	public static final String RUN_SYSTEM= "Run system";
	
	private JButton loadParametersJB;
	private JButton runSystemJB;
	
	public ButtonsJP() {
		setBackground(Color.WHITE);
		setLayout(new FlowLayout());
		setElements();
	}
	
	private void setElements()
	{
		loadParametersJB = new JButton(LOAD_PARAMETERS);
		
		runSystemJB = new JButton(RUN_SYSTEM);
		
		add(loadParametersJB);
		add(runSystemJB);
	}
	
	public void setButtonsMouseListener(MouseListener mouseListener)
	{
		loadParametersJB.addMouseListener(mouseListener);
		runSystemJB.addMouseListener(mouseListener);
	}

	public JButton getLoadParametersJB() {
		return loadParametersJB;
	}

	public JButton getRunSystemJB() {
		return runSystemJB;
	}

	
	
}
