package controller.threads;


import controller.CentralSystem;
import model.PublicTransportCenter;

public class RefreshTableThread extends Thread{

	private CentralSystem centralSystem;
	private PublicTransportCenter pTC;
	private boolean interrupt;
	
	public RefreshTableThread() {
		centralSystem = CentralSystem.getCentralSystem();
		pTC = PublicTransportCenter.getPublicTransportCenter(); 
		System.out.println("RefreshTableThread running...");
	}
	
	@Override
	public void run() {
		refreshTable();
	}
	
	private void refreshTable()
	{
		int seletedRow = 0;
		interrupt = false;
		
		while (true)
		{
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
	
	public void setInterrupt()
	{
		interrupt = true;
	}
}
