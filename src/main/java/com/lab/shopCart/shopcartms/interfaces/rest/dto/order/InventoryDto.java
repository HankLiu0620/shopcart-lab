package com.lab.shopCart.shopcartms.interfaces.rest.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private Integer inventory;

    private List<LinkDto> links;
}
