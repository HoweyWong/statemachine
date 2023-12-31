package com.example.statemachine.common.service.impl;

import com.example.statemachine.common.annotation.LogResult;
import com.example.statemachine.common.constants.CommonConstants;
import com.example.statemachine.common.dao.OrderMapper;
import com.example.statemachine.common.entity.Order;
import com.example.statemachine.common.enums.OrderStatus;
import com.example.statemachine.common.enums.OrderStatusChangeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author huanghao
 * @version 1.0
 * company: ANSO
 * copyright: Copyright (c) 2022
 * modified by:
 * @date 2023/9/26 17:52
 */
@Component("orderStateListener")
@WithStateMachine(name = "orderStateMachine")
@Slf4j
public class OrderStateListenerImpl {
    @Resource
    private OrderMapper orderMapper;

    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    @Transactional(rollbackFor = Exception.class)
    @LogResult(key = CommonConstants.payTransition)
    public void payTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("支付，状态机反馈信息：{}", message.getHeaders());
        //更新订单
        order.setStatus(OrderStatus.WAIT_DELIVER.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
        //模拟异常
        if (Objects.equals(order.getName(), "A")) {
            throw new RuntimeException("执行业务异常");
        }
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    @LogResult(key = CommonConstants.deliverTransition)
    public void deliverTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("发货，状态机反馈信息：{}", message.getHeaders().toString());
        //更新订单
        order.setStatus(OrderStatus.WAIT_RECEIVE.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
    }

    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    @LogResult(key = CommonConstants.receiveTransition)
    public void receiveTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("确认收货，状态机反馈信息：{}", message.getHeaders().toString());
        //更新订单
        order.setStatus(OrderStatus.FINISH.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
    }
}
