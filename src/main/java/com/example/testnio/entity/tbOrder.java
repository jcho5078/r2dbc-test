package com.example.testnio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Table(name = "tb_order")
@Data
@AllArgsConstructor
/*@ToString(exclude = "user")*/
public class tbOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private String orderName;

    @Transient
    private tbUser user;
}
