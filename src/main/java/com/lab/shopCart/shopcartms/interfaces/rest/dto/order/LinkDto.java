package com.lab.shopCart.shopcartms.interfaces.rest.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDto {
    private String deprecation = "string";

    private String href = "string";

    private String hreflang = "string";

    private String media = "string";

    private String name = "string";

    private String profile = "string";

    private List<String> rel = new ArrayList<>();

    private String title = "string";

    private String type = "string";
}
