package com.lab.shopCart.shopcartms.infrastructure.repository;

import com.lab.shopCart.shopcartms.domain.model.ShopCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopCartRepository extends JpaRepository<ShopCartEntity,Integer> {
}
