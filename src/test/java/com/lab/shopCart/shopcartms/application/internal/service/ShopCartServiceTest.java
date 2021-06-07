package com.lab.shopCart.shopcartms.application.internal.service;

import com.lab.shopCart.shopcartms.interfaces.rest.dto.order.OrderResDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.shopcart.ShopCartReqDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.shopcart.ShopCartResDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@Testcontainers
public class ShopCartServiceTest {

    @Autowired
    ShopCartService shopCartService;

//    @Container
//    private static final PostgreSQLContainer database =
//            (PostgreSQLContainer) new PostgreSQLContainer("postgres:9.6")
//                    .withDatabaseName("shopcart")
//                    .withUsername("postgres")
//                    .withPassword("1qaz1qaz")
//                    .withInitScript("init_postgres.sql");

    @Test
    public void createShopCartTest(){
        //Arrange
        ShopCartReqDto shopCartReqDto = new ShopCartReqDto("Hank");
        ShopCartResDto expectShopCartResDto = new ShopCartResDto(0,"Hank");

        //Act
        ShopCartResDto actalShopCartResDto = shopCartService.createShopCart(shopCartReqDto);

        //Assert
        Assertions.assertEquals(expectShopCartResDto,actalShopCartResDto);
    }

    @Test
    public void createOrderTest(){
        //Arrange
        Integer cartId = 1;
        String expectCustomerName = "Hank";

        //Act
        OrderResDto actaulOrder = shopCartService.createOrder(cartId);

        //Assert
        Assertions.assertEquals(expectCustomerName,actaulOrder.getCustomerName());
    }
}
