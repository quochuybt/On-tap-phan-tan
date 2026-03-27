package iuh.fit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Passenger {
    private String name;
    private int age;
    private String passport;
    private String seat;

    private Baggage baggage;
}
