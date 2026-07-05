package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;

public interface ProductService {

    ProductResponse addProduct(ProductRequest request);

    ProductResponse getProductById(Long id);
}
