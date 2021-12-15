package com.example.simpleframework.servlet.common.entity.dto;

import com.example.simpleframework.servlet.common.entity.bo.HeadLine;
import com.example.simpleframework.servlet.common.entity.bo.ShopCategory;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 *
 */
@Data
@Accessors(chain = true)
public class MainPageInfoDTO {

    private List<HeadLine> headLineList;

    private List<ShopCategory> shopCategoryList;
}
