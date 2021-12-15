package com.example.simpleframework.servlet.common.service;

import com.example.simpleframework.servlet.common.entity.bo.HeadLine;
import com.example.simpleframework.servlet.common.entity.dto.Result;

import java.util.List;

/**
 *
 */
public interface HeadLineService {

    Result<Boolean> addHeadLine(HeadLine HeadLine);

    Result<Boolean> removeHeadLine(int HeadLineId);

    Result<Boolean> modifyHeadLine(HeadLine HeadLine);

    Result<Boolean> queryHeadLineById(int HeadLineId);

    Result<List<HeadLine>> queryHeadLine(HeadLine HeadLineCondition, int pageNum, int pageSize);

}



