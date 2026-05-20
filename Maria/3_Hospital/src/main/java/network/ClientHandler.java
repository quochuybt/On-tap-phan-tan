package network;

import dto.AppointmentDTO;
import dto.DoctorWorkloadDTO;
import service.AppointmentService;
import service.DoctorService;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable{
    private Socket socket;
    private AppointmentService appointmentService;
    private DoctorService doctorService;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        this.appointmentService = new AppointmentService();
        this.doctorService = new DoctorService();
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
                    case ADD_APPOINTMENT -> {
                        AppointmentDTO appointmentDTO = (AppointmentDTO) request.getObject();
                        boolean res = appointmentService.addAppointment(appointmentDTO);
                        response.setSuccess(res);
                        response.setData(appointmentDTO);
                        response.setMessage(res?"add success":"add error");
                    }
                    case GET_APPOINTMENT_DETAILS -> {
                        List<AppointmentDTO> list = appointmentService.getAppointmentDetails();
                        response.setSuccess(true);
                        response.setData(list);
                        response.setMessage("get success");
                    }
                    case GET_DOCTOR_WORKLOAD -> {
                        List<DoctorWorkloadDTO> list = doctorService.getDoctorWorkload();
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
