package br.com.ggdio.delivery.cart.repository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import br.com.ggdio.delivery.cart.model.Cart;

@Repository
public class DefaultCartRepository implements CartRepository {

	@Autowired
	private RedisTemplate<String, Cart> redisTemplate;
	
	private static final String KEY = "delivery:cart";
	
	@Override
	public void save(Cart cart) {
		this.redisTemplate.opsForHash().put(KEY, cart.getId(), cart);
	}

	@Override
	public Cart get(String id) {
		return (Cart) redisTemplate.opsForHash().get(KEY, id);
	}

	@Override
	public void delete(String id) {
		redisTemplate.opsForHash().delete(KEY, id);
	}

	@Override
	public String generateId() {
		return UUID.randomUUID().toString().subSequence(0, 8).toString();
	}

}
