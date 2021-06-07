package com.lab.shopCart.shopcartms.interfaces.rest;

import com.lab.shopCart.shopcartms.application.internal.service.ShopCartService;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.order.OrderResDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.shopcart.ShopCartReqDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.shopcart.ShopCartResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value =  "/shopcart")
public class ShopCartController {

    @Autowired
    ShopCartService shopCartService;

    /** 新增購物車
     *
     * @param shopCartReqDto
     * @return
     */
    @PostMapping(path = "/add")
    public ShopCartResDto createShopCart(@RequestBody ShopCartReqDto shopCartReqDto){
        ShopCartResDto shopCartResDto = new ShopCartResDto();

        shopCartResDto = shopCartService.createShopCart(shopCartReqDto);


        return shopCartResDto;
    }

    /** 建立訂單
     *
     * @param cartId 購物車編號
     * @return
     */
    @GetMapping("/createOrder/{cartId}")
    public OrderResDto createOrder(@PathVariable("cartId") Integer cartId){

        OrderResDto orderResDto = shopCartService.createOrder(cartId);

        return orderResDto;
    }
}
