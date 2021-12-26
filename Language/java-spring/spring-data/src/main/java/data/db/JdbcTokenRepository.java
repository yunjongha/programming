package data.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import data.entity.Token;

/**
 * 
 * @author yunjo
 *
 */
@Repository
public class JdbcTokenRepository implements TokenRepository {

	private JdbcTemplate jdbc;
	
	public JdbcTokenRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Iterable<Token> findAll() {
		return jdbc.query("select * from Token", this::mapToToken);
	}

	@Override
	public Token findByUserId(String userId) {
		return jdbc.queryForObject("select * from Token where userId = ?", this::mapToToken, userId);
	}

	@Override
	public Token findByClientId(String clientId) {
		return jdbc.queryForObject("select * from Token where clientId = ?", this::mapToToken, clientId);
	}

	@Override
	public Token save(Token token) {
		jdbc.update("insert into Token (userId, clientId, accessToken, refreshToken, scopes) values (?, ?, ?, ?, ?)",
				token.getUserId(), token.getClientId(), token.getAccessToken(), token.getRefreshToken(), token.getScopes());
		return token;
	}
	
	private Token mapToToken(ResultSet rs, int rowNum) throws SQLException {
		return new Token(rs.getString("userId"), rs.getString("clientId"), rs.getString("accessToken"),
				rs.getString("refreshToken"), rs.getString("scopes"));
	}
}
