package com.intoThe.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "INTO_PRODUCT_DETAILS")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productId;
    int productName;
    int productAvailabilityCount;
    double productPrice;
    @ColumnDefault(value = "Y")
    boolean isActive;
    boolean productImagePath;
    @ColumnDefault(value = "N")
    boolean isApplicableForDiscount;
    @ColumnDefault(value = "0")
    int discountPercentage;
    int supplierId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductName() {
        return productName;
    }

    public void setProductName(int productName) {
        this.productName = productName;
    }

    public int getProductAvailabilityCount() {
        return productAvailabilityCount;
    }

    public void setProductAvailabilityCount(int productAvailabilityCount) {
        this.productAvailabilityCount = productAvailabilityCount;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isProductImagePath() {
        return productImagePath;
    }

    public void setProductImagePath(boolean productImagePath) {
        this.productImagePath = productImagePath;
    }

    public boolean isApplicableForDiscount() {
        return isApplicableForDiscount;
    }

    public void setApplicableForDiscount(boolean applicableForDiscount) {
        isApplicableForDiscount = applicableForDiscount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

}
