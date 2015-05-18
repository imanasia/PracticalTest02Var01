package ro.pub.cs.systems.pdsd.practicaltest02var01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;



public class CommunicationThread extends Thread {

	private ServerThread serverThread;
	private Socket       socket;
	
	public CommunicationThread(ServerThread serverThread, Socket socket) {
		this.serverThread = serverThread;
		this.socket       = socket;
	}
	
	@Override
	public void run()
	{
		if(socket != null)
		{
			try {
				BufferedReader bufferedReader = Utilities.getReader(socket);
				PrintWriter    printWriter    = Utilities.getWriter(socket);
				
				if (bufferedReader != null && printWriter != null) 
				{
					String city            = bufferedReader.readLine();
					String informationType = bufferedReader.readLine();
					HttpClient httpc = new DefaultHttpClient();
					HttpGet httpg = new HttpGet();
					ResponseHandler<String> rh = new BasicResponseHandler();
					String result = "";
					try {
						result = httpc.execute(httpg, rh);
					} catch (ClientProtocolException e) {
						
						e.printStackTrace();
					} catch (IOException e) {
					
						e.printStackTrace();
					}
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
