package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Product {
    private String productId;
    private String productName;
    private String unit;
    private double unitPrice;
    private int unitsInStock;
    private Supplier supplier;

    public Product(String productId, String productName, String unit, double unitPrice, int unitsInStock) {
        this.productId = productId;
        this.productName = productName;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
    }
}
