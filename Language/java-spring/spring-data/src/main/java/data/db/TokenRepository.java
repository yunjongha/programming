package data.db;

import data.entity.Token;

/**
 * 
 * @return
 */
public interface TokenRepository {

	Iterable<Token> findAll();
	
	Token findByUserId(String userId);

	Token findByClientId(String clientId);
	
	Token save(Token token);

}
