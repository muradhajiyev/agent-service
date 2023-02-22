package com.example.agentservice.services;

import com.example.agentservice.model.RedisSession;

public interface AgentService {

    boolean processSession(RedisSession session);
}
