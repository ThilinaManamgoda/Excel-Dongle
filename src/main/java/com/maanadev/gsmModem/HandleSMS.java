package com.maanadev.gsmModem;

import org.smslib.AGateway;
import org.smslib.IInboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.Message.MessageTypes;

import com.maanadev.configurations.Configurations;
import com.maanadev.excel.XlsxWrite;

public class HandleSMS implements IInboundMessageNotification {
	private Configurations configurations;

	public HandleSMS(Configurations configurations) {
		this.configurations = configurations;
	}
	//This method is invoked when a message arrive
	public void process(AGateway arg0, MessageTypes arg1, InboundMessage arg2) {

		String msg = arg2.getText();
		String data[] = msg.split(" ");
		System.out.println(msg);
		//writing  data 
		XlsxWrite xlsxWrite = new XlsxWrite(configurations);
		xlsxWrite.write(data[0], Double.parseDouble(data[1]));

	}

}
