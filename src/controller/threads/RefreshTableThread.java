package controller.threads;


import view.busesWindow.TableJP;
import controller.CentralSystem;

/**
 * @author Alexis Cuero Lsoada
 * This class extends of Thread and is used for update the {@link TableJP} JTable.  
 */
public class RefreshTableThread extends Thread {

	private boolean interrupt;
		
	@Override
	public void run() {
		System.out.println("RefreshTableThread running...");
		refreshTable();
	}
	
	private void refreshTable()
	{
		CentralSystem centralSystem;
		int seletedRow = 0;
		interrupt = false;
		
		while (true)
		{
			centralSystem = CentralSystem.getCentralSystem();
			seletedRow = centralSystem.getBusesWindowController().getBusesWindow().getTableJP().getBusesJT().getSelectedRow();
			centralSystem.getBusesWindowController().refreshTable(seletedRow);
			try {
				
				if(!isInterrupted())
				{
					sleep(1000);
					if(interrupt)
					{
						interrupt();
					}
				}
				else
				{
					break;
				}
				
			} catch (InterruptedException e) {
				System.err.println("Interrupted Exception");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Interrupts of update the table in {@link TableJP}.
	 */
	public void setInterrupt()
	{
		interrupt = true;
	}
}
