package network;

import lombok.*;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Response implements Serializable {
    private boolean success;
    private String message;
    private Object data;
}
