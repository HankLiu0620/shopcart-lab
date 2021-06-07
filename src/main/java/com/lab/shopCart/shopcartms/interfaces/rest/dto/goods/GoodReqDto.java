package com.lab.shopCart.shopcartms.interfaces.rest.dto.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodReqDto {

    private String id ;

    private Integer count = 0;

    private Integer cartId;

}
