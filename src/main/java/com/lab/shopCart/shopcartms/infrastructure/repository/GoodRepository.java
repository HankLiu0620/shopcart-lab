package com.lab.shopCart.shopcartms.infrastructure.repository;

import com.lab.shopCart.shopcartms.domain.model.GoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodRepository extends JpaRepository<GoodEntity,Integer> {
    List<GoodEntity> findByCartId(Integer cartId);

    GoodEntity getById(String id);
}
