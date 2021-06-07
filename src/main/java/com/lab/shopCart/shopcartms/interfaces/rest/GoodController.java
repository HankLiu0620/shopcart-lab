package com.lab.shopCart.shopcartms.interfaces.rest;

import com.lab.shopCart.shopcartms.application.internal.service.GoodService;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.goods.GoodReqDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.goods.GoodResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/goods")
public class GoodController {

    @Autowired
    GoodService goodService;

    @PostMapping("/add")
    private GoodResDto addGood(@RequestBody GoodReqDto goodReqDto){

        GoodResDto goodResDto = goodService.addGood(goodReqDto);

        return goodResDto;
    }

    @PutMapping("/update")
    private GoodResDto updateGoodsAmount(@RequestBody GoodReqDto goodReqDto){

        GoodResDto goodResDto = goodService.updateGoodsAmount(goodReqDto);

        return goodResDto;
    }

    @DeleteMapping("/delete/{goodId}")
    private void deleteGoods(@PathVariable("goodId") String goodId){
        goodService.deleteGood(goodId);
    }
}
