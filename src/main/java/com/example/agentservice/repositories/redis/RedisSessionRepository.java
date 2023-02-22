package com.example.agentservice.repositories.redis;


import com.example.agentservice.model.RedisSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface RedisSessionRepository extends CrudRepository<RedisSession, UUID> { }
