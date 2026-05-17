package network;

import java.io.Serializable;

public enum CommandType implements Serializable {
    ADD_APPOINTMENT,
    GET_APPOINTMENT_DETAILS,
    GET_DOCTOR_WORKLOADS
}
