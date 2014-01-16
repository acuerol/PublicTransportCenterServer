package controller.threads;

import java.util.ArrayList;

import model.PublicTransportCenter;
import model.Semaphore;

public class SemaphoresStateThread extends Thread {
	
	private PublicTransportCenter pTC;
	
	public SemaphoresStateThread() {
		pTC = PublicTransportCenter.getPublicTransportCenter();
		System.out.println("SemaphoresStateThreadRunning...");
	}
	
	@Override
	public void run() {
		refreshState();
	}

	private void refreshState() {
		int semaphoreTime = 0;
		
		while(true)
		{
			pTC = PublicTransportCenter.getPublicTransportCenter();

			for (Semaphore semaphore : pTC.getSemaphores()) 
			{
				semaphoreTime = semaphore.getTime();
				
				if(semaphoreTime == semaphore.getTimeGreen() && semaphore.getState())
				{
					semaphore.setState(false);
					semaphore.setTime(0);
				}
				else
				{
					if(semaphoreTime == semaphore.getTimeRed() && !semaphore.getState())
					{
						semaphore.setState(true);
						semaphore.setTime(0);
					}
					else
					{
						semaphore.setTime(semaphoreTime + 1);
					}
				}
			}
			
			PublicTransportCenter.refreshSemaphores(pTC);
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Interrupted Exception refreshSemaphoresState.");
				e.printStackTrace();
			}
		}
	}
}
