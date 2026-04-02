import dao.TrainerDAO;
import model.Trainer;

public class Main {
    public static void main(String[] args) {
        TrainerDAO trainerDAO = new TrainerDAO();
        trainerDAO.getNoOfTrainersBySpecialty("Cooley Ltd").forEach(
                (k,v) -> System.out.println(k + " "+ v)
        );
        Trainer trainer = new Trainer("TRN101","Hung","0123456789","Succac");
        System.out.println(trainerDAO.addTrainerToCenter(trainer,"Cooley Ltd"));
        trainerDAO.listTrainersBySpecialty("Succac").forEach(System.out::println);
        System.out.println(trainerDAO.updateSessionDuration("MEM005","TRN013",5.0f));
    }
}
