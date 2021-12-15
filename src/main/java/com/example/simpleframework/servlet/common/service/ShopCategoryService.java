package com.example.simpleframework.servlet.common.service;

import com.example.simpleframework.servlet.common.entity.bo.ShopCategory;
import com.example.simpleframework.servlet.common.entity.dto.Result;

import java.util.List;

/**
 *
 */
public interface ShopCategoryService {

    Result<Boolean> addShopCategory(ShopCategory shopCategory);

    Result<Boolean> removeShopCategory(int shopCategoryId);

    Result<Boolean> modifyShopCategory(ShopCategory shopCategory);

    Result<Boolean> queryShopCategoryById(int shopCategoryId);

    Result<List<ShopCategory>> queryShopCategory(ShopCategory shopCategoryCondition, int pageNum, int pageSize);
}
