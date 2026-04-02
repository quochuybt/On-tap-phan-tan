package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Patient extends Person{
    private Gender gender;
    private String dateOfBirth;
    private String address;

    public Patient(String id, String name, String phone, String address, String dateOfBirth, Gender gender) {
        super(id, name, phone);
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }
}
