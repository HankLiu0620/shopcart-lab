package com.lab.shopCart.shopcartms.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "shop_cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCartEntity {

    @Id
    @Column(name = "id" , nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount = 0;

    @Column(name = "customer_name", nullable = false)
    private String customerName;
}
