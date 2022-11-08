package com.example.testnio.controller;

import com.example.testnio.entity.tbOrder;
import com.example.testnio.repository.OrderRepository;
import com.example.testnio.repository.UserRepository;
import com.example.testnio.vo.RequestVO;
import io.r2dbc.spi.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    R2dbcEntityTemplate r2dbcEntityTemplate;

    @Autowired
    DatabaseClient databaseClient;

    @RequestMapping(value = "setOrder")
    public Flux<tbOrder> test(@RequestBody RequestVO requestVO) throws Exception{

        /* use repository */
        //if set id on r2dbc, run the Insert query. Conversely, if there is no setting id, run the update query
        Flux<tbOrder> flux = orderRepository.saveAll(requestVO.getTbOrders()).map(arg -> { // map become to blocking
            return arg;
        });

        Flux<tbOrder> flux2 = orderRepository.saveAll(requestVO.getTbOrders()).flatMap(arg -> Flux.just(arg));

        return flux;
    }

    @RequestMapping(value = "setOrder2")
    public Flux<Map<String, Object>> test2(@RequestBody RequestVO requestVO) throws Exception{
        /* use databaseClient */

        //execute one sql insert
        Flux<Map<String, Object>> flux = databaseClient.sql("INSERT INTO tb_order(id, order_name) VALUES(:id, :order_name)")
                .filter((statement, next) -> statement.returnGeneratedValues("id").execute())
                .bind("order_name", requestVO.getTbOrders().get(0).getOrderName())
                .fetch().all();

        //execute batch sql
        Flux flux2 = databaseClient.inConnectionMany(connection -> {
            Statement statement = connection.createStatement("INSERT INTO tb_order(id, order_name) VALUES(:id, :order_name)")
                    .returnGeneratedValues("id");
            for(int i=0; i<requestVO.getTbOrders().size(); i++){
                statement.bind("order_name", requestVO.getTbOrders().get(i).getOrderName());
            }
            return Flux.from(statement.execute()).flatMap(result -> result.map((row, rowMetadata) -> row.get("id", UUID.class)));
        });

        return flux;
    }

    @RequestMapping(value = "mono")
    public Flux<tbOrder> test(){

        Flux<tbOrder> flux = orderRepository.findAll();

        return flux;
    }

    @RequestMapping(value = "getExistOrderInfoFetchJoin")
    public Flux<tbOrder> getExistOrderInfoFetchJoin(String userName){

        Flux<tbOrder> flux = orderRepository.getExistOrderInfoFetchJoin(userName);

        return flux;
    }

    @RequestMapping(value = "delete")
    public Mono<tbOrder> delete(){

        Mono mono = orderRepository.deleteAll();

        return mono;
    }
}
