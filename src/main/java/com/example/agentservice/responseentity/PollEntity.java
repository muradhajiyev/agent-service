package com.example.agentservice.responseentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PollEntity {

    private Integer id;
    private UUID sessionId;
    private Long clientId;
}
