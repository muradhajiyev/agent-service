package com.example.agentservice.services;

import com.example.agentservice.entity.Poll;
import com.example.agentservice.model.RedisSession;
import com.example.agentservice.repositories.AnswerRepository;
import com.example.agentservice.repositories.PollRepository;
import com.example.agentservice.repositories.redis.RedisSessionRepository;
import com.example.agentservice.responseentity.PollDetailsEntity;
import com.example.agentservice.responseentity.PollEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PollServiceImpl implements PollService {

    private RedisSessionRepository sessionRepository;
    private PollRepository pollRepository;
    private AnswerRepository answerRepository;

    @Autowired
    public PollServiceImpl(RedisSessionRepository sessionRepository, PollRepository agentPollRepository, AnswerRepository answerRepository) {
        this.sessionRepository = sessionRepository;
        this.pollRepository = agentPollRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public Optional<RedisSession> getSessionById(UUID sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Override
    public List<PollEntity> getPolls() {
        Iterable<Poll> sessions = pollRepository.findAll();

        List<PollEntity> pollEntities = new ArrayList<>();

        sessions.forEach(session -> {
            PollEntity pollEntity = PollEntity.builder()
                    .id(session.getId())
                    .clientId(session.getClientId())
                    .sessionId(session.getSessionId())
                    .build();
            pollEntities.add(pollEntity);
        });

        return pollEntities;
    }

    @Override
    public List<PollDetailsEntity> getPollDetails(Integer pollId) {
        List<PollDetailsEntity> pollDetailsEntities = new ArrayList<>();
        answerRepository.findAllByPollId(pollId).forEach(session -> {
            PollDetailsEntity pollDetailsEntity = PollDetailsEntity.builder()
                    .id(session.getId())
                    .clientId(session.getPoll().getClientId())
                    .questionId(session.getQuestionId())
                    .answer(session.getAnswer())
                    .build();

            pollDetailsEntities.add(pollDetailsEntity);
        });
        return pollDetailsEntities;
    }


}
