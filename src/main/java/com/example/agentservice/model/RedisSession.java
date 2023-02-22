package com.example.agentservice.model;

import com.example.agentservice.enums.LanguageCode;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@RedisHash("Session")
@Data
@Builder
public class RedisSession implements Serializable {
    // clientId will be used as a key in redis
    // so, we can find sessions by clientId easily to determine if user has an active session or not
    @Id
    private UUID uuid;
    @Indexed
    private Long clientId;
    private Long chatId;
    private LanguageCode languageCode;
    private Integer currentQuestionId;
    private Map<Integer, String> answers;
}
