package network;

import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Response implements Serializable {
    private boolean success;
    private String message;
    private Object data;
}
