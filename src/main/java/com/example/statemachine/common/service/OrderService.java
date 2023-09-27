package com.example.statemachine.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.statemachine.common.entity.Order;

/**
 * 订单表(TbOrder)表服务接口
 *
 * @author huanghao
 * @since 2023-09-26 16:46:28
 */
public interface OrderService extends IService<Order> {
    Order create(Order order);

    Order pay(Long id);

    Order deliver(Long id);

    Order receive(Long id);
}

