package com.lab.shopCart.shopcartms.application.internal.service;

import com.lab.shopCart.shopcartms.interfaces.rest.dto.goods.GoodReqDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.goods.GoodResDto;
import com.lab.shopCart.shopcartms.interfaces.rest.vo.goods.GoodVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
//@Testcontainers
public class GoodServiceTest {

    @Autowired
    GoodService goodService;

//    @Container
//    private static final PostgreSQLContainer database =
//            (PostgreSQLContainer) new PostgreSQLContainer("postgres:9.6")
//            .withDatabaseName("shopcart")
//            .withUsername("postgres")
//            .withPassword("1qaz1qaz")
//            .withInitScript("init_postgres.sql");

    @Test
    public void addGoodTest(){
        //Arrange
        String id = "8559450";
        String goodName = "Python最強入門邁向頂尖高手之路：王者歸來（第二版）";
        Integer unitPrice = 752;
        Integer count = 2;
        Integer cartId = 1;
        GoodResDto expectGoodResDto = new GoodResDto(count,id,goodName,unitPrice);
        GoodReqDto goodReqDto = new GoodReqDto(id,count,cartId);

        //Act
        GoodResDto actaulGoodResDto = goodService.addGood(goodReqDto);

        //Assert
        Assertions.assertEquals(expectGoodResDto,actaulGoodResDto);
    }

    @Test
    public void addGoodTest_byExist(){
        //Arrange
        String id = "8559450";
        GoodResDto goodResDto = goodService.getGoodEntityById(id);
        String goodName = "Python最強入門邁向頂尖高手之路：王者歸來（第二版）";
        Integer unitPrice = 752;
        Integer count = 2;
        Integer cartId = 6;
        GoodResDto expectGoodResDto = new GoodResDto(count+goodResDto.getCount(),id,goodName,unitPrice);
        GoodReqDto goodReqDto = new GoodReqDto(id,count,cartId);

        //Act
        GoodResDto actaulGoodResDto = goodService.addGood(goodReqDto);

        //Assert
        Assertions.assertEquals(expectGoodResDto,actaulGoodResDto);
    }

    @Test
    public void updateGoodsAmountTest(){
        //Arrange
        GoodReqDto goodReqDto = new GoodReqDto("8559450",4,6);
        GoodResDto expectGoodResDto = new GoodResDto(
                4,"8559450","Python最強入門邁向頂尖高手之路：王者歸來（第二版）",752);

        //Act
        GoodResDto actalGoodResDto = goodService.updateGoodsAmount(goodReqDto);

        //Assert
        Assertions.assertEquals(expectGoodResDto,actalGoodResDto);
    }

    @Test
    public void updateGoodsAmountTest_btZero(){
        //Arrange
        GoodReqDto goodReqDto = new GoodReqDto("8559450",0,6);
        GoodResDto expectGoodResDto =
                new GoodResDto();

        //Act
        GoodResDto actualGoodResDto = goodService.updateGoodsAmount(goodReqDto);

        //Assert
        Assertions.assertEquals(expectGoodResDto,actualGoodResDto);
    }

    @Test
    public void deleteGoodTest(){
        //Arrange
        GoodResDto expectGoodResDto = new GoodResDto();
        String goodId = "8559450";
        //Act
        goodService.deleteGood(goodId);
        GoodResDto actualGoodResDto = goodService.getGoodEntityById(goodId);

        //Assert
        Assertions.assertEquals(expectGoodResDto,actualGoodResDto);
    }

    @Test
    public void updateInventoryTest(){
        //Arrange
        String goodsId = "8559450";
        String goodName = "Python最強入門邁向頂尖高手之路：王者歸來（第二版）";
        Integer unitPrice = 752;
        String inventory = goodService.getInventory(goodsId);
        Integer count = goodService.getGoodEntityById(goodsId).getCount();
        Integer inventoryResult = Integer.valueOf(inventory) - count;
        GoodVo expectGoodVo = new GoodVo(goodsId,goodName,unitPrice,inventoryResult.toString());

        //Act
        GoodVo actualGoodVo = null;
        try {
            actualGoodVo = goodService.updateInventory(goodsId);
        } catch (Exception e) {
            log.error("test error...{}",e.getMessage());
        }

        //Assert
        Assertions.assertEquals(expectGoodVo,actualGoodVo);
    }
}
