package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Supplier {
    private String supplier_id;
    private String contact_name;
    private String country;
    private String company_name;
}
