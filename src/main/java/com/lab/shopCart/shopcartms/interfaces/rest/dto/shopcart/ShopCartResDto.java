package com.lab.shopCart.shopcartms.interfaces.rest.dto.shopcart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCartResDto {

    private Integer totalAmount = 0;

    private String customerName ;

    public static ShopCartResDto addByRes(ShopCartReqDto shopCartReqDto){
        ShopCartResDto shopCartResDto = new ShopCartResDto(0,shopCartReqDto.getCustomerName());

        return shopCartResDto;
    }
}
