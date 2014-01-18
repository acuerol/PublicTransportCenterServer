package controller.threads;

import model.PublicTransportCenter;
import model.connection.ListenReportConnection;

/**
 * @author Alexis Cuero Losada
 * This class extends of Thread and is use for handle the report sent from clients for update the system
 * and return the new system.
 */
public class ListenReportConnectionThread extends Thread {

	private ListenReportConnection listenReportConnection;
	private boolean isReporting;
	
	/**
	 * Creates a {@link ListenReportConnection} instance for receive the report request.
	 */
	public ListenReportConnectionThread() {
		isReporting = false;
		listenReportConnection = new ListenReportConnection();
	}
	
	/**
	 * Starts to report the system.
	 */
	public void startToReport()
	{
		isReporting = true;
	}
	
	/**
	 * Stops of report the system.
	 */
	public void stopOfReport()
	{
		isReporting = false;
	}
	
	@Override
	public void run() {
		while(true)
		{
			if(isReporting)
			{
				PublicTransportCenter.refreshBusesFromClient(listenReportConnection.readReportSystem());
			}
		}
	}
}
