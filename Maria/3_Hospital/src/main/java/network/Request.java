package network;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Request implements Serializable {
    private CommandType commandType;
    private Object object;
}
