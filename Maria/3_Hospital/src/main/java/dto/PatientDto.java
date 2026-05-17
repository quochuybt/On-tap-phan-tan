package dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class PatientDto extends PersonDto implements Serializable {
    private String address;
    private Set<String> phones = new HashSet<>();


}
