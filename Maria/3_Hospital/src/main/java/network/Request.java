package network;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Request implements Serializable {
    private CommandType commandType;
    private Object object;
}
