package dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class DoctorDto extends PersonDto implements Serializable {
    private String specialty;
    private String hospital;
}
