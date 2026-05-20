import dto.AppointmentDTO;
import entity.Status;
import network.CommandType;
import network.Request;
import network.Response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket(InetAddress.getLocalHost().getHostName(),4121);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)
                ) {
            int choice = 0;

            while (true) {
                System.out.println("menu");
                System.out.println("1. add appointment");
                System.out.println("2. get appointment details");
                System.out.println("3. get doctor workload");

                choice = scanner.nextInt();
                scanner.nextLine();

                Request request = new Request();

                switch (choice) {
                    case 1 -> {
                        System.out.println("Nhap doctor id");
                        String doctorId = scanner.nextLine();
                        System.out.println("Nhap patient id");
                        String patientId = scanner.nextLine();

                        LocalDateTime time  = LocalDateTime.now();
                        Status status = Status.PENDING;

                        AppointmentDTO appointmentDTO = AppointmentDTO.builder()
                                .doctorId(doctorId)
                                .patientId(patientId)
                                .appointmentTime(time)
                                .status(status)
                                .build();

                        request.setObject(appointmentDTO);
                        request.setCommandType(CommandType.ADD_APPOINTMENT);
                    }
                    case 2 -> request.setCommandType(CommandType.GET_APPOINTMENT_DETAILS);
                    case 3 -> request.setCommandType(CommandType.GET_DOCTOR_WORKLOAD);
                }
                out.writeObject(request);
                out.flush();

                Response response = (Response) in.readObject();
                System.out.println(response);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
