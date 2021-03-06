package zn.zyh.back_code.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.pool.max-wait}")
    private int maxWait;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;

    @Bean
    public KeyGenerator wiselyKeyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * ?????????????????????
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //???????????????
        jedisPoolConfig.setMaxTotal(maxActive);
        //?????????????????????
        jedisPoolConfig.setMinIdle(minIdle);
        //?????????????????????
        jedisPoolConfig.setMaxIdle(maxIdle);
        //??????????????????????????????????????????????????????
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        return jedisPoolConfig;
    }

    /**
     * jedis????????????
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        //?????????jedis
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration();
        //??????redis????????????host??????ip??????
        redisStandaloneConfiguration.setHostName(host);
        //??????????????????????????????
        redisStandaloneConfiguration.setDatabase(0);
        //????????????
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        //??????redis?????????????????????
        redisStandaloneConfiguration.setPort(port);
        //?????????????????????????????????
        //??????jedisPoolConifig????????????????????????????????????
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
                JedisClientConfiguration.builder().usePooling().poolConfig(jedisPoolConfig);
        //????????????????????????jedis???????????????
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        //???????????? + ??????????????? = jedis????????????
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration =
                RedisCacheConfiguration
                        .defaultCacheConfig()
                        // ?????????????????? 6 ??????
                        .entryTtl(Duration.ofHours(6L))
                        // ???????????? null ???
                        .disableCachingNullValues()
                        // ?????? key ?????????
                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                        // ?????? value ?????????
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        RedisCacheConfiguration redisCacheConfiguration_verification =
                RedisCacheConfiguration
                        .defaultCacheConfig()
                        // ?????????????????? 17 minutes
                        .entryTtl(Duration.ofMinutes(17L))
                        // ???????????? null ???
                        .disableCachingNullValues()
                        // ?????? key ?????????
                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                        // ?????? value ?????????
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        RedisCacheConfiguration redisCacheConfiguration_chatDto =
                RedisCacheConfiguration
                        .defaultCacheConfig()
                        // ?????????????????? 4h
                        .entryTtl(Duration.ofHours(4L))
                        // ???????????? null ???
                        .disableCachingNullValues()
                        // ?????? key ?????????
                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                        // ?????? value ?????????
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.RedisCacheManagerBuilder
                // Redis ????????????
                .fromConnectionFactory(redisConnectionFactory)
                // ????????????
                .cacheDefaults(redisCacheConfiguration)
                .withCacheConfiguration("verification", redisCacheConfiguration_verification)
                .withCacheConfiguration("chatDto", redisCacheConfiguration_chatDto)
                // ??????????????????????????? put/evict
                .transactionAware()
                .build();
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        setSerializer(template); //??????????????????????????????ReportBean???????????????Serializable??????
        template.afterPropertiesSet();
        return template;
    }

    private void setSerializer(RedisTemplate<String, Object> redisTemplate) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    }
}

