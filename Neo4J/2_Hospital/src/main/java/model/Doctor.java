package model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@ToString(callSuper = true)
public class Doctor extends Person{
    private String speciality;

    public Doctor(String id, String name, String phone, String speciality) {
        super(id, name, phone);
        this.speciality = speciality;
    }
}
