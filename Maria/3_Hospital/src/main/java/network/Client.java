package network;

import dto.AppointmentDto;
import entity.Status;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){
        try (
                Socket socket = new Socket(InetAddress.getLocalHost().getHostName(),9090);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)
        ) {
            int choice = 0;
            while(true) {
                System.out.println("=====Main menu=====");
                System.out.println("1: add appointment");
                System.out.println("2: get appointment details");
                System.out.println("3: get doctor workloads");

                choice = scanner.nextInt();
                scanner.nextLine();
                Request request = new Request();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Nhập doctorId: ");
                        String doctorId = scanner.nextLine();
                        System.out.println("Nhập patientId: ");
                        String patientId = scanner.nextLine();
                        LocalDateTime appointmentTime = LocalDateTime.now();
                        Status status = Status.PENDING;
                        AppointmentDto appointmentDto = AppointmentDto.builder()
                                .status(status)
                                .doctorId(doctorId)
                                .patientId(patientId)
                                .appointmentTime(appointmentTime)
                                .build();
                        request.setCommandType(CommandType.ADD_APPOINTMENT);
                        request.setObject(appointmentDto);
                    }
                    case 2 -> request.setCommandType(CommandType.GET_APPOINTMENT_DETAILS);
                    case 3 -> request.setCommandType(CommandType.GET_DOCTOR_WORKLOADS);
                }
                objectOutputStream.writeObject(request);
                objectOutputStream.flush();

                Response response = (Response) objectInputStream.readObject();
                System.out.println(response.isSuccess());
                System.out.println(response.getMessage());
                System.out.println(response.getData());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
