package mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dto.AppointmentDto;
import entity.Appointment;
import entity.Doctor;
import entity.Patient;


public class Mapper {
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <S, T> T map(S source, Class<T> targetClass) {
        return mapper.convertValue(source, targetClass);
    }
}
