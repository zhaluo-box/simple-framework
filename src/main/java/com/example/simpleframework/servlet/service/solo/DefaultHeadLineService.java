package com.example.simpleframework.servlet.service.solo;

import com.example.simpleframework.servlet.common.entity.bo.HeadLine;
import com.example.simpleframework.servlet.common.entity.dto.Result;
import com.example.simpleframework.servlet.common.service.HeadLineService;
import org.simpleframework.core.annontation.Service;

import java.util.List;

/**
 *
 */
@Service
public class DefaultHeadLineService implements HeadLineService {

    @Override
    public Result<Boolean> addHeadLine(HeadLine HeadLine) {
        return null;
    }

    @Override
    public Result<Boolean> removeHeadLine(int HeadLineId) {
        return null;
    }

    @Override
    public Result<Boolean> modifyHeadLine(HeadLine HeadLine) {
        return null;
    }

    @Override
    public Result<Boolean> queryHeadLineById(int HeadLineId) {
        return null;
    }

    @Override
    public Result<List<HeadLine>> queryHeadLine(HeadLine HeadLineCondition, int pageNum, int pageSize) {
        return null;
    }
}
