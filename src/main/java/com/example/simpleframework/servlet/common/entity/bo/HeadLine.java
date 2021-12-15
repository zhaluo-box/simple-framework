package com.example.simpleframework.servlet.common.entity.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 头条
 */
@Data
@Accessors(chain = true)
public class HeadLine {

    /**
     * 头条Id
     */
    private Long lineId;

    /**
     * 头条名字
     */
    private String lineName;

    /**
     * 头条连接, 点击头条会对应进入响应连接中
     */
    private String lineLink;

    /**
     * 权重, 越大越排前显示
     */
    private String lingImg;

    /**
     * 0 不可用, 1 可用
     */
    private Integer priority;

    /**
     * 状态
     */
    private Integer enableStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次更新时间
     */
    private Date lastEditTime;

}
