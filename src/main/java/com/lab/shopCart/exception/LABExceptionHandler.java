package com.lab.shopCart.exception;

import com.lab.shopCart.exception.dto.ExceptionDto;
import com.lab.shopCart.shopcartms.domain.enums.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class LABExceptionHandler {

    @ExceptionHandler(LABException.class)
    public ResponseEntity<Object> exceptionHandler(LABException labException){
        log.error("LABException: {}", labException.toString());

        ExceptionDto response = new ExceptionDto(
                "-1",
                new ExceptionDto.Detail(StatusCode.E001.getCode(),StatusCode.E001.getMsg()),
                "API excute failed!"
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
