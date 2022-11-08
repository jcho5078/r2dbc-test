package com.example.testnio.vo;

import com.example.testnio.entity.tbOrder;
import com.example.testnio.entity.tbUser;
import lombok.Data;

import java.util.List;

@Data
public class RequestVO {

    private tbUser user;
    private tbOrder tbOrderEntity;
    private List<tbOrder> tbOrders;
}
