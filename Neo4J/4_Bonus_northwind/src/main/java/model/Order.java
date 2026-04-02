package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Order {
    private String orderId;
    private LocalDate orderDate;
    private String customerName;
    private String employeeName;
    private Status status;
}
