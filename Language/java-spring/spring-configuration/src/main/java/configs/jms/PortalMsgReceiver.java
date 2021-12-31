package configs.jms;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

@Component
public class PortalMsgReceiver {

	private JmsTemplate jms;
	private MessageConverter converter;
	
	@Autowired
	public PortalMsgReceiver(JmsTemplate jms, MessageConverter converter) {
		this.jms = jms;
		this.converter = converter;
	}
	
	public Client receiveClient() {

		Message msg = jms.receive("mydata.portal.queue");
		try {
			Client client = (Client) converter.fromMessage(msg);
			System.out.println("receive client: " + client);
			
			return client;
		} catch (MessageConversionException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@JmsListener(destination = "mydata.portal.queue")
	public Client listenClient(Client client) {
		System.out.println(("listen client: " + client));
		
		return client;
	}
}
