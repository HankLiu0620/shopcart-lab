package com.lab.shopCart.shopcartms.interfaces.rest.dto.order;

import com.lab.shopCart.shopcartms.domain.model.GoodEntity;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.goods.GoodReqDto;
import com.lab.shopCart.shopcartms.interfaces.rest.vo.goods.GoodVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResDto {

    private String orderId;

    private String customerName;

    private Integer totalAmount;

    private List<GoodVo> goods;

}
