package com.example.nutricao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {
	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory cf = null;
		System.out.println("Entra ake?1");
		try {		System.out.println("Entra ake?2");

			cf = new JedisConnectionFactory();
			cf.setHostName("localhost");
			cf.setPort(6379);
			return cf;
		} catch (Exception e) {
			System.out.println("Erro");
		}
		System.out.println("Entra ake?3");

		return cf;
	}
}
