package com.maanadev.gsmModem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;
import com.maanadev.configurations.Configurations;

public class SmsReceiver {
	static Configurations configurations;
	private static final String CONFIG_FILE = "config.txt";

	public SmsReceiver() {
	}

	public void Initialize() throws Exception {

		// Configure Dongle
		SerialModemGateway gateway = new SerialModemGateway(configurations.getGATEWAYNAME(), configurations.getPORT(),
				configurations.getFREQUENCY(), "", "");
		gateway.setInbound(true);
		gateway.setOutbound(true);

		HandleSMS handleSMS = new HandleSMS(configurations);
		
		// configure Service
		Service service = Service.getInstance();
		service.addGateway(gateway);
		service.setInboundMessageNotification(handleSMS);// add smslistener to
		// service
		
		service.startService();
		System.out.println("Service is started !!!");

	}

	public static void main(String args[]) {
		SmsReceiver app = new SmsReceiver();
		config();
		try {
			app.Initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void config() {
		//setting up configuration object for the application
		Configurations configurationstmp = new Configurations();
		FileReader fileReader = null;

		BufferedReader buffer = null;
		try {
			//reading the file
			fileReader = new FileReader(new File(CONFIG_FILE));
			buffer = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> data = new ArrayList<String>();
		try {
			String line;

			while ((line = buffer.readLine()) != null) {
				data.add(line);

			}
			buffer.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String line : data) {

			String keyPlusData[] = line.split("##");

			if (keyPlusData[0].equals("##")) {
				String values[] = keyPlusData[1].split("=");

				if (values[0].equals("PORT"))
					configurationstmp.setPORT(values[1]);
				else if (values[0].equals("PATH"))
					configurationstmp.setPATH(values[1]);
				else if (values[0].equals("FREQUENCY"))
					configurationstmp.setFREQUENCY(Integer.parseInt(values[1]));
			}

		}

		configurations = configurationstmp;

	}
}