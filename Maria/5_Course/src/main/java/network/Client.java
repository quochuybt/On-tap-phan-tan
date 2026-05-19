package network;

import dto.EnrollmentDTO;
import entity.EnrollStatus;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDate;
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
                System.out.println("Menu");
                System.out.println("1. add enrollment");
                System.out.println("2. update enrollment");
                System.out.println("3. get open course by genreName");

                choice = scanner.nextInt();
                scanner.nextLine();

                Request request = new Request();

                switch (choice) {
                    case 1 -> {
                        System.out.println("Nhap student id");
                        String studentId = scanner.nextLine();
                        System.out.println("Nhap course id");
                        String courseId = scanner.nextLine();

                        LocalDate date = LocalDate.now();
                        EnrollStatus status = EnrollStatus.REGISTERED;

                        EnrollmentDTO enrollmentDTO = EnrollmentDTO.builder()
                                .studentId(studentId)
                                .courseId(courseId)
                                .enrollDate(date)
                                .status(status)
                                .build();

                        request.setObject(enrollmentDTO);
                        request.setCommandType(CommandType.ADD_ENROLLMENT);
                    }

                    case 2 -> {
                        System.out.println("Nhap student id");
                        String studentId = scanner.nextLine();
                        System.out.println("Nhap course id");
                        String courseId = scanner.nextLine();
                        System.out.println("Nhap Enrolldate");
                        LocalDate enrolldate =LocalDate.parse(scanner.nextLine());
                        System.out.println("Nhap score");
                        double score = scanner.nextDouble();


                        EnrollmentDTO enrollmentDTO = EnrollmentDTO.builder()
                                .studentId(studentId)
                                .courseId(courseId)
                                .enrollDate(enrolldate)
                                .score(score)
                                .build();

                        request.setObject(enrollmentDTO);
                        request.setCommandType(CommandType.UPDATE_SCORE);
                    }
                    case 3 ->{
                        System.out.println("Nhap ten the loai");
                        String genreName = scanner.nextLine();

                        request.setObject(genreName);
                        request.setCommandType(CommandType.GET_OPEN_COURSE_BY_GENRE);
                    }
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
