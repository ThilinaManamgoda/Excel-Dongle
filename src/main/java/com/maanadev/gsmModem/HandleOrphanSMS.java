package com.maanadev.gsmModem;

import org.smslib.AGateway;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.InboundMessage;

public class HandleOrphanSMS implements IOrphanedMessageNotification {

	public boolean process(AGateway arg0, InboundMessage arg1) {
		System.out.println(arg1.getText());
		return false;
	}

}
