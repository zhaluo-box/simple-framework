package com.example.simpleframework.servlet.common.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 */
@Data
@Accessors(chain = true)
public class Result<T> {

    private String message;

    private T data;

    private int code;

}
