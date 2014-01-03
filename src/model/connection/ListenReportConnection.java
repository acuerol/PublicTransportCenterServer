package model.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.Bus;
import model.PublicTransportCenter;
import controller.CentralSystem;

public class ListenReportConnection {

	private CentralSystem centralSystem;
	public static final int PORT = 5001;
	private Socket connection;
	private ServerSocket serverSocket;
	private PublicTransportCenter pTC;
	
	public ListenReportConnection() {
		centralSystem = CentralSystem.getCentralSystem();
		pTC = PublicTransportCenter.getPublicTransportCenter();
		
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.err.println("IOException ServerConnectionCenter.");
			e.printStackTrace();
		}
	}
	
	public PublicTransportCenter readReportSystem()
	{
		PublicTransportCenter pTC;
		try 
		{
			connection = serverSocket.accept();
			
			ObjectOutputStream sendData = new ObjectOutputStream(connection.getOutputStream());
			ObjectInputStream clientData = new ObjectInputStream(connection.getInputStream());
			
			pTC = (PublicTransportCenter) clientData.readObject();
			
			if(pTC != null)
			{
				//System.out.println("***Report receive succefuly...");
				this.pTC = pTC;
				this.pTC.setPublicTransportCenter(pTC);
//				System.out.println("Before generate System: " + pTC.getBuses().size());
				
				sendData.writeObject("true");

				this.pTC = centralSystem.generateNewSystem(); 
				
//				System.out.println("After generate System: " + this.pTC.getBuses().size());
				
				sendData.writeObject(this.pTC);
			}
			else
			{
				sendData.writeObject("false");
			}
		} 
		catch (IOException e)
		{
			System.err.println("Error I/O ServerSocket.");
			e.printStackTrace();
			return null;
		} 
		catch (ClassNotFoundException e) {
			System.err.println("Answer isn't recognized.");
			e.printStackTrace();
			return null;
		}
		
		return pTC;
	}
}
