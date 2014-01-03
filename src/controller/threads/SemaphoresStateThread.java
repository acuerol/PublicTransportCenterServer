package controller.threads;

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
			for (Semaphore semaphore : pTC.getSemaphores()) 
			{
				semaphoreTime = semaphore.getTime();
				
				if(semaphoreTime == semaphore.getTimeGreen())
				{
					semaphore.setState(false);
					semaphore.setTime(0);
				}
				else
				{
					if(semaphoreTime == semaphore.getTimeRed())
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
			
			try {
				sleep(200);
			} catch (InterruptedException e) {
				System.err.println("Interrupted Exception refreshSemaphoresState.");
				e.printStackTrace();
			}
		}
	}
}
