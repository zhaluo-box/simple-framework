package com.example.simpleframework.servlet.common.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 *
 */
@Data
@Accessors(chain = true)
public class ShopCategory {

    /**
     * 主键Id
     */
    private Long shopCategoryId;

    /**
     * 类别名称
     */
    private String shopCategoryNam;

    /**
     * 类别描述
     */
    private String shopCategoryDesc;

    /**
     * 权重
     */
    private Integer priority;

    /**
     * 最后修改时间
     */
    private Date createTime;

    /**
     * 父类别
     */
    private ShopCategory parent;
}
