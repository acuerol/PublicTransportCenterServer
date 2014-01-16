package controller.threads;

import java.io.IOException;
import java.net.ServerSocket;

import controller.CentralSystem;
import model.PublicTransportCenter;
import model.Semaphore;
import model.connection.ListenReportConnection;

public class ListenReportConnectionThread extends Thread {

	private ListenReportConnection reportConnection;
	private boolean isReporting;
	
	public ListenReportConnectionThread() {
		isReporting = false;
		reportConnection = new ListenReportConnection();
	}
	
	public void startToReport()
	{
		isReporting = true;
	}
	
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
				PublicTransportCenter.refreshBusesFromClient(reportConnection.readReportSystem());
			}
		}
	}
	
}
