package network;

import dto.CourseStatDTO;
import dto.EnrollmentDTO;
import service.CourseService;
import service.EnrollmentService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable{
    private Socket socket;
    private EnrollmentService enrollmentService;
    private CourseService courseService;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        enrollmentService = new EnrollmentService();
        courseService = new CourseService();
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ) {
            while (true) {
                Request request = (Request) in.readObject();
                CommandType commandType = request.getCommandType();
                Response response = new Response();

                switch (commandType) {
                    case ADD_ENROLLMENT -> {
                        EnrollmentDTO enrollmentDTO = (EnrollmentDTO) request.getObject();
                        boolean res = enrollmentService.addEnrollment(enrollmentDTO);
                        response.setSuccess(res);
                        response.setData(enrollmentDTO);
                        response.setMessage(res?"add success":"add error");
                    }
                    case UPDATE_SCORE -> {
                        EnrollmentDTO enrollmentDTO = (EnrollmentDTO) request.getObject();
                        boolean res = enrollmentService.updateScore(enrollmentDTO);
                        response.setSuccess(res);
                        response.setData(enrollmentDTO);
                        response.setMessage(res?"update success":"update error");
                    }
                    case GET_OPEN_COURSE_BY_GENRE -> {
                        String genreName = (String) request.getObject();
                        List<CourseStatDTO> list = courseService.getOpenCoursesByGenre(genreName);
                        response.setSuccess(true);
                        response.setData(list);
                        response.setMessage("get success");
                    }
                }

                out.writeObject(response);
                out.flush();

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
