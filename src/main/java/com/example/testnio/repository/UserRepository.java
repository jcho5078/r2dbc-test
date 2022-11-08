package com.example.testnio.repository;

import com.example.testnio.entity.tbUser;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<tbUser, Long>, ReactiveQueryByExampleExecutor<tbUser> {

}
