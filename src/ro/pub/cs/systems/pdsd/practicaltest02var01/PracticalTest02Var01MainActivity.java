package ro.pub.cs.systems.pdsd.practicaltest02var01;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest02Var01MainActivity extends Activity {

	private EditText     serverPortEditText       = null;
	private Button       connectButton            = null;
	private EditText     clientAddressEditText    = null;
	private EditText     clientPortEditText       = null;
	private EditText     cityEditText             = null;
	private Button       clientConnectButton 			  = null;
	private TextView     weatherForecastTextView  = null;
	private ServerThread serverThread             = null;
	private ClientThread clientThread             = null;
	private Button tempButton 					  = null;
	private Button humButton 					  = null;
	private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
	
	private class ConnectButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			String serverPort = serverPortEditText.getText().toString();
		
			serverThread = new ServerThread(Integer.parseInt(serverPort));
			if (serverThread.getServerSocket() != null) {
				serverThread.start();
			} else {
				Log.e(Constants.TAG, "[MAIN ACTIVITY] Could not creat server thread!");
			}
			
		}
	}
	
	private GetWeatherForecastButtonClickListener clientConnectButtonClickListener = new GetWeatherForecastButtonClickListener();
	private class GetWeatherForecastButtonClickListener implements Button.OnClickListener {
		
		@Override
		public void onClick(View view) {
			String clientAddress = clientAddressEditText.getText().toString();
			String clientPort    = clientPortEditText.getText().toString();
			
			String city = cityEditText.getText().toString();
			String informationType = tempButton.getText().toString();
			informationType.concat(" "+ humButton.getText().toString());
			//de implementat pentru all
			weatherForecastTextView.setText("");
			
			clientThread = new ClientThread(
					clientAddress,
					Integer.parseInt(clientPort),
					city,
					informationType,
					weatherForecastTextView);
			clientThread.start();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test02_var01_main);
		//server
		serverPortEditText = (EditText)findViewById(R.id.server_port_edit_text);
		clientAddressEditText = (EditText)findViewById(R.id.client_address_edit_text);
		clientPortEditText = (EditText)findViewById(R.id.client_port_edit_text);
		connectButton = (Button)findViewById(R.id.connect_button);
		connectButton.setOnClickListener(connectButtonClickListener);
		
		
		//client
		tempButton = (Button)findViewById(R.id.Client_temp_button);
		humButton = (Button)findViewById(R.id.client_hum_button);
		weatherForecastTextView = (TextView)findViewById(R.id.weather_forecast_text_view);
		
		clientConnectButton = (Button)findViewById(R.id.client_connect);
		clientConnectButton.setOnClickListener(clientConnectButtonClickListener);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test02_var01_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
