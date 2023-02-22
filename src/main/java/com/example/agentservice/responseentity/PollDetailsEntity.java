package com.example.agentservice.responseentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PollDetailsEntity {
    private Integer id;
    private Integer questionId;
    private String answer;
    private Long clientId;
}
