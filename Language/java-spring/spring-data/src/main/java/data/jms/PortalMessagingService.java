package data.jms;

import java.util.List;

import data.entity.Client;

/**
 * 
 * @author yunjo
 *
 */
public interface PortalMessagingService {

	public void sendClients(List<Client> clients);

	public void sendClient(Client client);
}
