package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {
    private String order_id;
    private LocalDate order_date;
    private String customer_name;
    private String employee_name;
    private String status;
}
