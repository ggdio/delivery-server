package br.com.ggdio.delivery.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import br.com.ggdio.delivery.cart.model.Cart;

@Configuration
public class JedisConfig {
	
	@Value("${database.redis.hostname}")
	private String hostname;
	
	@Value("${database.redis.port}")
	private int port;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory bean = new JedisConnectionFactory();
		bean.setHostName(hostname);
		bean.setPort(port);
		return bean;
	}
	
	@Bean
	@Autowired
	public RedisTemplate<String, Cart> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<String, Cart> bean = new RedisTemplate<>();
		bean.setConnectionFactory(jedisConnectionFactory);
		return bean;
	}
	
}
