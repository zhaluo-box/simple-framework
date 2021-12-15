package com.example.simpleframework.servlet.common.service;

import com.example.simpleframework.servlet.common.entity.dto.MainPageInfoDTO;
import com.example.simpleframework.servlet.common.entity.dto.Result;

/**
 *
 */
public interface HeadlineShopCategoryCombineService {

    Result<MainPageInfoDTO> getMainPageInfo();
}
