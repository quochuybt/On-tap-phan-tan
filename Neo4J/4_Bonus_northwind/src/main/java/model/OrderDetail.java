package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetail {
    private Order order;
    private Product product;

    private int quantity;
    private double unitPrice;
    private double discount;
}
