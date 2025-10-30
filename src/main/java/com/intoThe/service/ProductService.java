package com.intoThe.service;

import com.intoThe.dto.ProductDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getProducts();
    public ProductDto getProductById(int productId);
    public String addProduct(ProductDto productDto);
    public boolean updateProduct(ProductDto productDto);
    public  boolean deleteProduct(int productId);
}
