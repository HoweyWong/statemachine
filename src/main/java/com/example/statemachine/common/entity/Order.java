package com.example.statemachine.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 订单表(TbOrder)实体类
 *
 * @author huanghao
 * @since 2023-09-26 16:46:26
 */
@Data
@TableName("tb_order")
public class Order {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String orderCode;

    private Integer status;

    private String name;

    private Double price;

    private Integer deleteFlag;

    private Date createTime;

    private Date updateTime;

    private String createUserCode;

    private String updateUserCode;

    private Integer version;

    private String remark;

}

