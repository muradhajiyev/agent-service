package com.example.agentservice.listener;

import com.example.agentservice.config.RabbitConfiguration;
import com.example.agentservice.model.RedisSession;
import com.example.agentservice.services.AgentService;
import com.example.agentservice.services.PollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class SessionListener {

    private PollService sessionService;
    private AgentService agentService;

    @Autowired
    public SessionListener(PollService sessionService, AgentService agentService) {
        this.sessionService = sessionService;
        this.agentService = agentService;
    }

    @RabbitListener(queues = RabbitConfiguration.SESSION_QUEUE)
    public void listen(UUID sessionId) {
        log.info("Session with id: {}", sessionId);

        Optional<RedisSession> session = sessionService.getSessionById(sessionId);
        if (session.isPresent()) {
            log.info("Session found: {}", session.get());

            agentService.processSession(session.get());
        } else {
            log.warn("Session not found");
        }
    }

}
