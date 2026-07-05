package com.ecommerce.order_service.service.impl;

import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.dto.OrderResponse;
import com.ecommerce.order_service.dto.PaymentRequest;
import com.ecommerce.order_service.dto.PaymentResponse;
import com.ecommerce.order_service.dto.ProductResponse;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.repository.OrderRepository;
import com.ecommerce.order_service.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    public OrderServiceImpl(OrderRepository orderRepository,
                            RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public OrderResponse placeOrder(OrderRequest request) {

        // Call Product Service
        ProductResponse product = restTemplate.getForObject(
                "http://product-service:8082/products/{id}",
                ProductResponse.class,
                request.getProductId()
        );

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        Order order = new Order();

        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(product.getPrice());
        order.setStatus("PENDING_PAYMENT");
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderId(savedOrder.getId());
        paymentRequest.setAmount(savedOrder.getTotalPrice());
        paymentRequest.setPaymentMethod("UPI");

        PaymentResponse paymentResponse =
                restTemplate.postForObject(
                        "http://payment-service:8084/payments",
                        paymentRequest,
                        PaymentResponse.class
                );

        if (paymentResponse != null &&
                "SUCCESS".equals(paymentResponse.getPaymentStatus())) {

            savedOrder.setStatus("CONFIRMED");
        } else {
            savedOrder.setStatus("PAYMENT_FAILED");
        }

        savedOrder.setUpdatedAt(LocalDateTime.now());

        orderRepository.save(savedOrder);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getStatus(),
                savedOrder.getTotalPrice()
        );
    }
}
