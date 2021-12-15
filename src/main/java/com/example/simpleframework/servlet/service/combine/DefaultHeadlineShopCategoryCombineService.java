package com.example.simpleframework.servlet.service.combine;

import com.example.simpleframework.servlet.common.entity.bo.HeadLine;
import com.example.simpleframework.servlet.common.entity.bo.ShopCategory;
import com.example.simpleframework.servlet.common.entity.dto.MainPageInfoDTO;
import com.example.simpleframework.servlet.common.entity.dto.Result;
import com.example.simpleframework.servlet.common.service.HeadLineService;
import com.example.simpleframework.servlet.common.service.HeadlineShopCategoryCombineService;
import com.example.simpleframework.servlet.common.service.ShopCategoryService;
import org.simpleframework.core.annontation.Service;

import java.util.List;

/**
 *
 */
@Service
public class DefaultHeadlineShopCategoryCombineService implements HeadlineShopCategoryCombineService {

    private HeadLineService headLineService;

    private ShopCategoryService shopCategoryService;

    @Override
    public Result<MainPageInfoDTO> getMainPageInfo() {
        //1. 获取头条列表
        var headLineCondition = new HeadLine().setEnableStatus(1);
        var headlines = headLineService.queryHeadLine(headLineCondition, 0, 100);
        //2. 获取类别列表
        var shopCategory = new ShopCategory();
        var shopCategories = shopCategoryService.queryShopCategory(shopCategory, 0, 100);
        //3. 组装
        return mergeResult(headlines, shopCategories);
    }

    private Result<MainPageInfoDTO> mergeResult(Result<List<HeadLine>> headlines, Result<List<ShopCategory>> shopCategories) {
        var mainPageInfoDTO = new MainPageInfoDTO().setShopCategoryList(shopCategories.getData()).setHeadLineList(headlines.getData());
        return new Result<MainPageInfoDTO>().setData(mainPageInfoDTO);
    }
}
