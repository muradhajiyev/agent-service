package com.example.agentservice.services;

import com.example.agentservice.model.RedisSession;
import com.example.agentservice.responseentity.PollDetailsEntity;
import com.example.agentservice.responseentity.PollEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PollService {
    Optional<RedisSession> getSessionById(UUID sessionId);

    List<PollEntity> getPolls();

    List<PollDetailsEntity> getPollDetails(Integer sessionId);
}
