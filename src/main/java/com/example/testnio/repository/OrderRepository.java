package com.example.testnio.repository;

import com.example.testnio.entity.tbOrder;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderRepository extends R2dbcRepository<tbOrder, Long>, ReactiveQueryByExampleExecutor<tbOrder> {

    @Query("select CASE WHEN (SELECT COUNT(*) FROM order_t) > 0 THEN MAX(id) ELSE 1 END from order_t")
    public Mono<Long> getTableMaxVal();

    /**
     * fetch join으로 조회
     * @param name
     * @return
     */
    @Query("select * from OrderEntity od join fetch od.user where od.user.userName = :name")
    public Flux<tbOrder> getExistOrderInfoFetchJoin(String name);
}
