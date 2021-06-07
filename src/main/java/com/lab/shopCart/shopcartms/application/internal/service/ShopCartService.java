package com.lab.shopCart.shopcartms.application.internal.service;

import com.lab.shopCart.shopcartms.domain.model.GoodEntity;
import com.lab.shopCart.shopcartms.domain.model.ShopCartEntity;
import com.lab.shopCart.shopcartms.infrastructure.repository.GoodRepository;
import com.lab.shopCart.shopcartms.infrastructure.repository.ShopCartRepository;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.goods.GoodResDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.order.OrderReqDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.order.OrderResDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.shopcart.ShopCartReqDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.shopcart.ShopCartResDto;
import com.lab.shopCart.shopcartms.interfaces.rest.vo.goods.GoodVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ShopCartService {

    @Autowired
    ShopCartRepository shopCartRepository;

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    GoodService goodService;

    public ShopCartResDto createShopCart(ShopCartReqDto shopCartReqDto) {
        ShopCartEntity shopCartEntity = new ShopCartEntity();

        shopCartEntity.setCustomerName(shopCartReqDto.getCustomerName());

        shopCartRepository.save(shopCartEntity);

        return ShopCartResDto.addByRes(shopCartReqDto);
    }

    public void updateTotalAmount(Integer cartId){
        ShopCartEntity shopCartEntity = shopCartRepository.getById(cartId);
        List<GoodEntity> goodEntities = goodRepository.findByCartId(cartId);

        Integer totalAmount = 0;

        Integer increaseAmount = goodEntities.stream().mapToInt(x -> x.getUnitPrice() * x.getCount()).sum();
        log.info("Goods amount is : {} in this time",increaseAmount);

        totalAmount+=increaseAmount;
        log.info("Total Amount is {}",totalAmount);

        shopCartEntity.setTotalAmount(totalAmount);
        shopCartRepository.saveAndFlush(shopCartEntity);
    }

    /** 取得購物車
     *
     * @param cartId 購物車編號
     * @return
     */
    public ShopCartEntity getShopCartEntityByCartId(Integer cartId){
        return shopCartRepository.getById(cartId);
    }

    /** 建立訂單
     *
     * @param cartId 購物車編號
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public OrderResDto createOrder(Integer cartId) {
        ShopCartEntity shopCartEntity = shopCartRepository.getById(cartId);

        OrderResDto orderResDto = null;

        try {
            orderResDto = goodService.createOrder(shopCartEntity);
            List<GoodVo> goods = orderResDto.getGoods();
            goods.stream().forEach(good ->{
                String goodId = good.getGoodsId();
                try {
                    goodService.updateInventory(goodId);
                    goodService.deleteGood(goodId);
                    updateTotalAmount(cartId);
                } catch (Exception e) {
                    log.error("update inventory error...{}",e.getMessage());
                }
            });
        } catch (Exception e) {
            log.error("create order error...{}",e.getMessage());
        }

        return orderResDto;
    }
}
