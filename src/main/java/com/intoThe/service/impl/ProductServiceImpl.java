package com.intoThe.service.impl;

import com.intoThe.dto.ProductDto;
import com.intoThe.mapper.ProductModelMapper;
import com.intoThe.repository.ProductRepository;
import com.intoThe.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        return List.of();
    }

    @Override
    public ProductDto getProductById(int productId) {
        return null;
    }

    @Override
    public String addProduct(ProductDto productDto) {
        try{
            productRepository.save(ProductModelMapper.mapProductDtoToProduct(productDto));
        }catch (Exception e){
            System.out.println("{ProductServiceImpl}-[addProduct]:- " + e.getMessage());
        }
        return "";
    }

    @Override
    public boolean updateProduct(ProductDto productDto) {
        return false;
    }

    @Override
    public boolean deleteProduct(int productId) {
        return false;
    }
}
