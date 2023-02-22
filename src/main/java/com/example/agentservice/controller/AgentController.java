package com.example.agentservice.controller;

import com.example.agentservice.responseentity.PollDetailsEntity;
import com.example.agentservice.responseentity.PollEntity;
import com.example.agentservice.services.PollService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AgentController {

    private PollService sessionService;

    public AgentController(PollService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/polls")
    public ResponseEntity<List<PollEntity>> getAllPolls() {
        List<PollEntity> polls = sessionService.getPolls();
        return ResponseEntity.ok(polls);
    }

    @GetMapping("/polls/{id}")
    public ResponseEntity<List<PollDetailsEntity>> getPollDetails(@PathVariable Integer id) {
        List<PollDetailsEntity> pollDetails = sessionService.getPollDetails(id);
        return ResponseEntity.ok(pollDetails);
    }

}
