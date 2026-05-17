package network;

import dto.AppointmentDto;
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
        try(
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                ){
            while(true) {
                Request request = (Request) in.readObject();
                CommandType type = request.getCommandType();
                Response response = new Response();
                switch (type) {
                    case ADD_APPOINTMENT -> {
                        try {
                            AppointmentDto appointmentDto = (AppointmentDto) request.getObject();
                            System.out.println(appointmentDto);
                            boolean result = appointmentService.addAppointment(appointmentDto);
                            response.setSuccess(true);
                            response.setData(appointmentDto);
                            response.setMessage(result?"add success":"add error");
                        }catch (Throwable e) {
                            response.setSuccess(false);
                            response.setMessage(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    case GET_APPOINTMENT_DETAILS -> {
                        List<AppointmentDto> list = appointmentService.getAppointmentDetails();
                        response.setSuccess(true);
                        response.setData(list);
                        response.setMessage("Get: "+ list.size() + " of appointment");
                    }
                    case GET_DOCTOR_WORKLOADS ->  {
                        List<DoctorWorkloadDTO> list = doctorService.getDoctorWorkload();
                        response.setSuccess(true);
                        response.setData(list);
                        response.setMessage("Get: "+ list.size() + " of doctor workload");
                    }
                }
                out.writeObject(response);
                out.flush();
            }

        }catch (Throwable e) {
            System.out.println("Server error");
            e.printStackTrace();
        }
    }
}
