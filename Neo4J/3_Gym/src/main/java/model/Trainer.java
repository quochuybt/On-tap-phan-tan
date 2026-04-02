package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@ToString(callSuper = true)
public class Trainer extends Person{
    private String speciality;

    public Trainer(String id, String name, String phone, String speciality) {
        super(id, name, phone);
        this.speciality = speciality;
    }
}
