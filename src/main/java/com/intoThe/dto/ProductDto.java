package com.intoThe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    int productId;
    @NotBlank(message = "Product can't be blank!...")
    int productName;
    @NotBlank(message = "Product Availability Count can't be blank!...")
    int productAvailabilityCount;
    @NotBlank(message = "Product Price Can't be blank!...")
    double productPrice;
    boolean isActive;
    boolean productImagePath;
    boolean isApplicableForDiscount;
    int discountPercentage;
    int supplierId;
}
