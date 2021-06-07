package com.lab.shopCart.exception;

import com.lab.shopCart.shopcartms.domain.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LABException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    StatusCode statusCode;

}
