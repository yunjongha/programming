package data.jms;

import java.util.List;

import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import data.entity.Client;

@Service
public class JmsPortalMessagingService implements PortalMessagingService {

	private JmsTemplate jmsTemp;
	private Destination portalQueue;
	
	@Autowired
	public JmsPortalMessagingService(JmsTemplate jmsTemp, Destination portalQueue) {
		this.jmsTemp = jmsTemp;
		this.portalQueue = portalQueue;

	}

	@Override
	public void sendClients(List<Client> clients) {

		for(Client client : clients) {
			jmsTemp.send(x -> x.createObjectMessage(client));
		}
	}

	@Override
	public void sendClient(Client client) {
		jmsTemp.send(x -> x.createObjectMessage(client));
	}

}
