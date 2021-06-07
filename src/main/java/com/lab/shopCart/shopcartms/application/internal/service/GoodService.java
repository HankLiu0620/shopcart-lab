package com.lab.shopCart.shopcartms.application.internal.service;

import com.lab.shopCart.exception.LABException;
import com.lab.shopCart.shopcartms.domain.enums.StatusCode;
import com.lab.shopCart.shopcartms.domain.model.GoodEntity;
import com.lab.shopCart.shopcartms.domain.model.ShopCartEntity;
import com.lab.shopCart.shopcartms.infrastructure.repository.GoodRepository;
import com.lab.shopCart.shopcartms.infrastructure.repository.ShopCartRepository;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.goods.GoodReqDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.goods.GoodResDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.order.InventoryDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.order.OrderReqDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.order.OrderResDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.shopcart.ShopCartReqDto;
import com.lab.shopCart.shopcartms.interfaces.rest.dto.shopcart.ShopCartResDto;
import com.lab.shopCart.shopcartms.interfaces.rest.vo.goods.GoodVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class GoodService {

    @Autowired
    GoodRepository goodRepository;

    @Autowired
    ShopCartService shopCartService;

    @Value("${good.service.url}")
    private String goodUrl;

    @Value("${order.service.url}")
    private String orderUrl;


    /** 新增商品
     *
     * @param goodReqDto
     * @return
     */
    public GoodResDto addGood(GoodReqDto goodReqDto) {

        Integer count = goodReqDto.getCount();

        GoodEntity goodEntity = goodRepository.getById(goodReqDto.getId());

        RestTemplate restTemplate = new RestTemplate();

        final String path = goodUrl + "/goods/" + goodReqDto.getId();

        GoodVo goodVo = restTemplate.getForObject(path,GoodVo.class);

        log.info("result: {}",goodVo);


        if(null == goodEntity){
            goodEntity = new GoodEntity(goodVo.getGoodsId(),goodVo.getGoodsName()
                    ,goodVo.getUnitPrice(),goodReqDto.getCount(),goodReqDto.getCartId());

            goodRepository.save(goodEntity);
        }else {
            count += goodEntity.getCount();
            goodEntity.setCount(count);
            log.info("The count of Good id {} is {}",goodReqDto.getId(),count);
            goodRepository.saveAndFlush(goodEntity);
        }

        shopCartService.updateTotalAmount(goodReqDto.getCartId());

        return  GoodResDto.converVo(goodVo,goodReqDto,count);
    }

    /** 修改商品數量
     *
     * @param goodReqDto
     * @return
     */
    @Transactional()
    public GoodResDto updateGoodsAmount(GoodReqDto goodReqDto) {

        GoodResDto goodResDto = new GoodResDto();
        GoodEntity goodEntity = goodRepository.getById(goodReqDto.getId());

        if(goodReqDto.getCount().equals(0)){
            goodRepository.delete(goodEntity);

        }else{
            goodEntity.setCount(goodReqDto.getCount());

            goodRepository.saveAndFlush(goodEntity);
            goodResDto= new GoodResDto(goodEntity.getCount(),goodEntity.getId(),
                    goodEntity.getName(),goodEntity.getUnitPrice());
        }

        shopCartService.updateTotalAmount(goodReqDto.getCartId());

        return goodResDto;
    }

    /** 刪除商品
     *
     * @param goodId 商品編號
     * @return
     */
    public void deleteGood(String goodId) {

        GoodEntity goodEntity = goodRepository.getById(goodId);

        if(null != goodEntity){
            Integer cartId = goodEntity.getCartId();
            log.info("delte goodId {} from cartId{}",goodId, goodEntity.getCartId());

            goodRepository.delete(goodEntity);

            shopCartService.updateTotalAmount(cartId);
        }else{
            throw new LABException(StatusCode.E001);
        }
    }

    /** 取的購物車商品資訊
     *
     * @param goodId 商品編號
     * @return
     */
    public GoodResDto getGoodEntityById(String goodId) {
        GoodEntity goodEntity = goodRepository.getById(goodId);
        GoodResDto goodResDto = new GoodResDto();

        if(null != goodEntity){
            goodResDto = new GoodResDto(
                    goodEntity.getCount(),goodId, goodEntity.getName(), goodEntity.getUnitPrice());
        }
        return goodResDto;
    }


    /** 建立訂單
     *
     * @param shopCartEntity
     * @return
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public OrderResDto createOrder(ShopCartEntity shopCartEntity) throws Exception{

        Integer cartId = shopCartEntity.getId();

        List<GoodEntity> goodEntities = goodRepository.findByCartId(cartId);

        List<GoodResDto> goods = new ArrayList<>();

        goodEntities.forEach(goodEntity -> {
            GoodResDto goodResDto = new GoodResDto(
                    goodEntity.getCount(),goodEntity.getId(),goodEntity.getName(),goodEntity.getUnitPrice());

            goods.add(goodResDto);
        });

        OrderReqDto orderReqDto = new OrderReqDto(
                cartId.toString(),shopCartEntity.getCustomerName(),goods,shopCartEntity.getTotalAmount());

        RestTemplate restTemplate = new RestTemplate();

        final String path = orderUrl + "/orders";

        log.info("url:{},orderReqDto:{}",path,orderReqDto.toString());

        OrderResDto orderResDto = restTemplate.postForObject(path, orderReqDto, OrderResDto.class);

        return orderResDto;
    }

    /** 更新庫存
     *
     * @param goodId 商品編號
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public GoodVo updateInventory(String goodId) throws Exception{
        Integer inventory = Integer.valueOf(getInventory(goodId));

        GoodEntity goodEntity = goodRepository.getById(goodId);
        InventoryDto inventoryDto = new InventoryDto();
        inventoryDto.setInventory(inventory-goodEntity.getCount());

        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(5000);
        httpRequestFactory.setReadTimeout(5000);
        restTemplate.setRequestFactory(httpRequestFactory);
        final String path = goodUrl + "/goods/" + goodId + "/inventory";
        GoodVo goodVo = restTemplate.patchForObject(path,inventoryDto,GoodVo.class);

        return goodVo;
    }

    /** 取得庫存數量
     *
     * @param goodId 商品編號
     * @return
     */
    public String getInventory(String goodId){
        RestTemplate restTemplate = new RestTemplate();

        final String path = goodUrl + "/goods/" + goodId;

        GoodVo goodVo = restTemplate.getForObject(path,GoodVo.class);

        return goodVo.getInventory();
    }
}
