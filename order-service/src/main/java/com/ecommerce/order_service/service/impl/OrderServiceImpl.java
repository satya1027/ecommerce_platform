package com.ecommerce.order_service.service.impl;

import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.dto.OrderResponse;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.repository.OrderRepository;
import com.ecommerce.order_service.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = new Order();

        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());

        // Temporary value. Later we'll get the real price from Product Service.
        order.setTotalPrice(BigDecimal.valueOf(1000));

        order.setStatus("PLACED");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getStatus(),
                savedOrder.getTotalPrice()
        );
    }
}
