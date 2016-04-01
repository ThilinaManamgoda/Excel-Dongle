package com.maanadev.gsmModem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.smslib.AGateway;
import org.smslib.AGateway.GatewayStatuses;
import org.smslib.GatewayException;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.Message.MessageTypes;
import org.smslib.modem.SerialModemGateway;
import com.maanadev.configurations.Configurations;
import com.maanadev.excel.XlsxWrite;

public class SmsReceiver {
	static Configurations configurations;
	private static final String CONFIG_FILE = "F:/config.txt";
	private static String NUMBER = "";
	static Logger log = null;

	public SmsReceiver() {
	}

	public void Initialize() throws Exception {
		// Initialize Logger
		log = Logger.getLogger(SmsReceiver.class.getName());

		// Configure Dongle
		log.debug("Setting up the Dongle");
		SerialModemGateway gateway = new SerialModemGateway(configurations.getGATEWAYNAME(), configurations.getPORT(),
				configurations.getFREQUENCY(), "", "E173");

		gateway.setInbound(true);


		log.debug("Setting up the Dongle is successful");
		// configure Service
		log.debug("configure Service");
		Service service = Service.getInstance();
		log.debug("Add the Gateway(Dongle) to service");
		service.addGateway(gateway);
		service.setInboundMessageNotification(new IInboundMessageNotification() {

			public void process(AGateway arg0, MessageTypes arg1, InboundMessage arg2) {
				System.out.println("Message arrived");
				if (arg2.getOriginator().equals(NUMBER)) {

				
					
					String msg [] = arg2.getText().split(" ");
					System.out.println(msg[0]+":"+msg[1]+":"+msg[2]+":"+msg[3]);
					XlsxWrite xlsxWrite = new XlsxWrite(configurations);
					xlsxWrite.write(msg);
					try {
						gateway.deleteMessage(arg2);
					} catch (TimeoutException e) {
						e.printStackTrace();
					} catch (GatewayException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});// add smslistener to

		service.startService();
		System.out.println("SERVER: Started!");

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
		// setting up configuration object for the application
		Configurations configurationstmp = new Configurations();
		FileReader fileReader = null;

		BufferedReader buffer = null;
		try {
			// reading the file
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

			String keyPlusData[] = line.split("%");
			if (keyPlusData[0].equals("#")) {
				String values[] = keyPlusData[1].split("=");

				if (values[0].equals("PORT"))
					configurationstmp.setPORT(values[1]);
				else if (values[0].equals("PATH"))
					configurationstmp.setPATH(values[1]);
				else if (values[0].equals("FREQUENCY"))
					configurationstmp.setFREQUENCY(Integer.parseInt(values[1]));
				else if (values[0].equals("NUMBER"))
					NUMBER = values[1];
				else if (values[0].equals("LOGFILE"))
					configurationstmp.setLOGFILE(values[1]);
			}

		}

		configurations = configurationstmp;

	}
}