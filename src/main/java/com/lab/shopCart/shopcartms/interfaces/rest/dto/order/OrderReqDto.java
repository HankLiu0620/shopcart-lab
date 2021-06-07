package com.lab.shopCart.shopcartms.interfaces.rest.dto.order;

import com.lab.shopCart.shopcartms.interfaces.rest.dto.goods.GoodResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqDto {

    private String cartId;

    private String customerName;

    private List<GoodResDto> goods;

    private Integer totalAmount;

}
