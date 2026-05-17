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
public class PersonDto implements Serializable {
    private String id;
    private String fullName;
    private String email;

}
