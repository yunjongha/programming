package data.mem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author yunjo
 *
 * @param <T>
 */
@Repository
public class MemoryRepository {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	private ObjectMapper objMapper;
	
	@Autowired
	public void setMapper(ObjectMapper mapper) {
		objMapper = mapper;
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public <T> void put(String key, T value) {
		ValueOperations<String, String> vop = redisTemplate.opsForValue();
		try {
			vop.set(key, objMapper.writeValueAsString(value));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public <T> T get(String key, Class<T> type) {
		ValueOperations<String, String> vop = redisTemplate.opsForValue();
		String value = vop.get(key);

		try {
			return objMapper.readValue(value, type);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
