package com.affectionsboot.starter.cache;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.codec.JsonJacksonCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host:localhost}")
    private String redisHost;

    @Value("${spring.redis.port:6379}")
    private int redisPort;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 使用 JsonJacksonCodec 作为序列化器，确保数据正确编码
        config.setCodec(new JsonJacksonCodec());
        config.useSingleServer()
                .setAddress("redis://" + redisHost + ":" + redisPort);
        return Redisson.create(config);
    }
}
