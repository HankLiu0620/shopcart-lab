package com.lab.shopCart.shopcartms.interfaces.rest.vo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodVo {

    private String goodsId;

    private String goodsName;

    private Integer unitPrice;

    private String inventory;
}
