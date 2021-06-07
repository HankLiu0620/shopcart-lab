package com.lab.shopCart.shopcartms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodEntity {

    @Id
    @Column(name = "id" , nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name ;

    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "cart_id", nullable = false)
    private Integer cartId;
}
