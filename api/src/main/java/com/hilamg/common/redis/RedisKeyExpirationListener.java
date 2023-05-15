package com.hilamg.common.redis;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {



	public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
		super(listenerContainer);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void onMessage(Message message, byte[] pattern) {
		String redisKey = message.toString();
//		log.info("即将要失效的Key:" + redisKey);
		// 处理交易所买家到期未付款而造成的订单失效，按照买家取消订单处理
		if (redisKey.contains("register_")) {

		}

	}
}
