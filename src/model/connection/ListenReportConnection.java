package model.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import model.PublicTransportCenter;
import controller.CentralSystem;

/**
  * @author Alexis Cuero Losada
  * Class for receive a report from the other client.
  */
public class ListenReportConnection {

	/**
	 * The port for listen the report connections.
	 */
	public static final int PORT = 5001;
	private Socket connection;
	private ServerSocket serverSocket;
	
	/**
	 * Constructor that initialize the server for listen reports.
	 */
	public ListenReportConnection() {
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.err.println("IOException ServerConnectionCenter.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads the report receive from the client and generate a new system.
	 * Returns the new system. 
	 * @return the new system
	 */
	public PublicTransportCenter readReportSystem()
	{
		CentralSystem centralSystem = CentralSystem.getCentralSystem();
		PublicTransportCenter pTC = PublicTransportCenter.getPublicTransportCenter();
		PublicTransportCenter tempPTC;
		try 
		{
			connection = serverSocket.accept();
			
			ObjectOutputStream sendData = new ObjectOutputStream(connection.getOutputStream());
			ObjectInputStream clientData = new ObjectInputStream(connection.getInputStream());
			
			tempPTC = (PublicTransportCenter) clientData.readObject();
			
			if(tempPTC != null)
			{
				pTC = tempPTC;
				PublicTransportCenter.refreshBusesFromClient(tempPTC);

				pTC = centralSystem.generateNewSystem();
				
				sendData.writeObject("true");
				
				sendData.writeObject(pTC);
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
