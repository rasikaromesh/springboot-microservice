package org.rrd.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.rrd.orderservice.dto.OrderLineItemDto;
import org.rrd.orderservice.dto.OrderRequest;
import org.rrd.orderservice.model.Order;
import org.rrd.orderservice.model.OrderLineItem;
import org.rrd.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItem> orderLineItemList =
                orderRequest.getOrderLineItemDtoList().stream().map(this::mapToEntity).toList();
        order.setOrderLineItemList(orderLineItemList);
        orderRepository.save(order);
    }

    private OrderLineItem mapToEntity(OrderLineItemDto orderLineItemDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setPrice(orderLineItemDto.getPrice());
        orderLineItem.setQuantity(orderLineItemDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItem.getSkuCode());
        return orderLineItem;
    }
}
