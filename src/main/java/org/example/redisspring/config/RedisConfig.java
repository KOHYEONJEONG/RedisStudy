package org.example.redisspring.config;

import org.springframework.beans.factory.annotation.Value; // 어노테이션 붙은걸로 import
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
/**
 * Redis 서버와 연결(Connection)을 맺는 공장(Factory) Bean
 * */
@Configuration // 이 클래스가 스프링 설정 클래스를 의미함.
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String host; //ip

    @Value("${spring.data.redis.port}")
    private int port; //port

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // Lettuce라는 라이브러리를 활용해 Redis 연결을 관리하는 객체를 생성
        // redis 서버에 대한 정보(host, port)를 설정

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
        // LettuceConnectionFactory : Redis와의 커넥션(연결)을 관리하는 역할.
        // RedisStandaloneConfiguration : 단일 노드 Redis 서버(클러스터/센티넬이 아닌 일반 서버)에 연결하기 위한 설정.
    }
}
