package com.intoThe.mapper;

import com.intoThe.dto.ProductDto;
import com.intoThe.entities.Products;

public class ProductModelMapper {
    public static Products mapProductDtoToProduct(ProductDto productDto){
        Products products = new Products();
        products.setProductName(productDto.getProductName());
        products.setProductPrice(productDto.getProductPrice());
        productDto.setProductPrice(productDto.getProductPrice());
        products.setSupplierId(productDto.getSupplierId());
        products.setProductImagePath(productDto.isProductImagePath());
        return products;
    }

    public ProductDto mapProductToProductDto(Products product){
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setProductImagePath(product.isProductImagePath());
        productDto.setActive(product.isActive());
        productDto.setProductAvailabilityCount(product.getProductAvailabilityCount());
        productDto.setSupplierId(product.getSupplierId());
        return productDto;
    }
}
