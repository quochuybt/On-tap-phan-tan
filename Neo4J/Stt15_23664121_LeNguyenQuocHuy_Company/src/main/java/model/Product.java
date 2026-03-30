package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Product {
    private String product_id;
    private String product_name;
    private String unit;
    private double unit_price;
    private int units_in_stock;
    private Supplier supplier;

    public Product(String product_id, String product_name, String unit, double unit_price, int units_in_stock) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.unit = unit;
        this.unit_price = unit_price;
        this.units_in_stock = units_in_stock;
    }
}
