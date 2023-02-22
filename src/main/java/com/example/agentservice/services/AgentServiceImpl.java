package com.example.agentservice.services;

import com.example.agentservice.entity.Answer;
import com.example.agentservice.entity.Poll;
import com.example.agentservice.model.RedisSession;
import com.example.agentservice.repositories.AnswerRepository;
import com.example.agentservice.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements AgentService {

    private PollRepository pollRepository;
    private AnswerRepository answerRepository;

    @Autowired
    public AgentServiceImpl(PollRepository sessionRepository, AnswerRepository answerRepository) {
        this.pollRepository = sessionRepository;
        this.answerRepository = answerRepository;
    }


    @Override
    public boolean processSession(RedisSession session) {

        Poll newPoll = Poll.builder()
                .clientId(session.getClientId())
                .sessionId(session.getUuid())
                .build();

        pollRepository.save(newPoll);


        session.getAnswers().forEach((key, value) -> {
            Answer newAnswer = Answer.builder()
                    .poll(newPoll)
                    .questionId(key)
                    .answer(value)
                    .build();

            answerRepository.save(newAnswer);
        });

        return true;
    }
}
