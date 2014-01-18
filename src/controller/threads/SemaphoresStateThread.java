package controller.threads;

import model.PublicTransportCenter;
import model.Semaphore;

/**
 * @author Alexis Cuero Losada
 * This class extends of Thread and is use for refresh the semaphores state. 
 */
public class SemaphoresStateThread extends Thread {
	
	@Override
	public void run() {
		System.out.println("SemaphoresStateThreadRunning...");
		refreshState();
	}

	private void refreshState() {
		int semaphoreTime = 0;
		PublicTransportCenter pTC;
		
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
