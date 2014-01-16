package model.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import controller.CentralSystem;
import model.PublicTransportCenter;
import view.connectionWindow.ConnectionWindowJF;

/**
 * @author Alexis Cuero Losada
 *
 */
public class InitialValuesConnectionCenter {
	public static final int PORT = 5000;
	
	private ConnectionWindowJF connectionWindow;
	private Socket connection;
	private ServerSocket serverSocket;
	private boolean listenInitialValuesRequest;
	
	public InitialValuesConnectionCenter() 
	{
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.err.println("IOException ServerConnectionCenter.");
			e.printStackTrace();
		}
	}
	
	public void listenInitialValuesRequest()
	{
		try 
		{
			connectionWindow = CentralSystem.getCentralSystem().getControllerConnectionWindow().getConnectionWindow();
			
			System.out.println("-----------------------------");
			System.out.println("Waiting initial values request...");
			
			connectionWindow.addInformationText("-----------------------------");
			connectionWindow.addInformationText("Waiting initial values request...");
			
			connection = serverSocket.accept();
			System.out.println("Request acepted.");
			connectionWindow.addInformationText("Request acepted.");
			
			ObjectOutputStream sendData = new ObjectOutputStream(connection.getOutputStream());
			ObjectInputStream clientRequest = new ObjectInputStream(connection.getInputStream());
			
			sendData.writeObject(PublicTransportCenter.getPublicTransportCenter());
			
			if(clientRequest.readObject().equals("true"))
			{
				System.out.println("Data could sent successfully.");
				connectionWindow.addInformationText("Data could sent successfully.");
			}
			else
			{
				System.err.println("Error sending Data, false answer.");
				connectionWindow.addInformationText("Error sending Data, false answer.");
			}
		} 
		catch (IOException e)
		{
			System.err.println("Error I/O ServerSocket.");
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			System.err.println("Answer isn't recognized.");
			e.printStackTrace();
		}
	}
	
	public void startServer()
	{
		while(true)
		{
			System.out.println("Listen");
			if(listenInitialValuesRequest)
			{
				
				listenInitialValuesRequest();
			}
		}
	}
	
	public void turnOffListenInitialValuesRequest()
	{
		listenInitialValuesRequest = false;
	}
	
	public void turnOnListenInitialValuesRequest()
	{
		listenInitialValuesRequest = true;
	}
}
