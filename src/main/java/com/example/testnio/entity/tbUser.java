package com.example.testnio.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
/*@ToString(exclude = "orders")*/
public class tbUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String userName;

    @Column
    private String userPw;

    @Transient
    private List<tbOrder> tbOrders = new ArrayList<>();
}
