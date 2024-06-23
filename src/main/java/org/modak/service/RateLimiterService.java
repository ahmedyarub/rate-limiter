package org.modak.service;

import org.modak.persistence.repository.NotificationTypeConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import static java.util.Collections.singletonList;

@Service
public class RateLimiterService {
    private final RedisTemplate<String, String> redisTemplate;
    private final RedisScript<Boolean> script;
    private final NotificationTypeConfigRepository configurationRepository;

    @Autowired
    public RateLimiterService(RedisTemplate<String, String> redisTemplate, NotificationTypeConfigRepository configurationRepository) {
        this.redisTemplate = redisTemplate;
        this.configurationRepository = configurationRepository;

        Resource scriptSource = new ClassPathResource("META-INF/scripts/sliding_window.lua");
        this.script = RedisScript.of(scriptSource, Boolean.class);
    }

    public boolean isAllowed(String type) {
        var config = configurationRepository.findNotificationTypeConfigByType(type);

        return Boolean.TRUE.equals(
                redisTemplate.execute(
                        script,
                        singletonList(type),
                        String.valueOf(config.getMaxRequestsPerWindow()),
                        String.valueOf(config.getRateLimitingWindow())
                )
        );
    }
}
