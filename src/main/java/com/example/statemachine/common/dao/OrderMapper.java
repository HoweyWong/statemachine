package com.example.statemachine.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.statemachine.common.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表(TbOrder)表数据库访问层
 *
 * @author huanghao
 * @since 2023-09-26 16:46:23
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}

