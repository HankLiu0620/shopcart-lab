package com.lab.shopCart.shopcartms.interfaces.rest.dto.goods;

import com.lab.shopCart.shopcartms.interfaces.rest.vo.goods.GoodVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodResDto {

    private Integer count ;

    private String goodsId;

    private String goodsName;

    private Integer unitPrice;



    public static GoodResDto converVo(GoodVo goodVo, GoodReqDto goodReqDto,Integer count){
        GoodResDto goodResDto = new GoodResDto(count,goodReqDto.getId(),goodVo.getGoodsName(),goodVo.getUnitPrice());

        return goodResDto;
    }

}
