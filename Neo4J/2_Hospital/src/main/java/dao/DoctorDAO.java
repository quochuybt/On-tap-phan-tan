package dao;

import model.Doctor;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;
import util.AppUtils;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

public class DoctorDAO {
    public static boolean addDoctor(Doctor doctor) {

        String query = """
                CREATE (d:Doctor {ID:$id,Name:$name,Phone:$phone,Speciality:$speciality})
                """;
        Map<String,Object> params = Map.of("id",doctor.getId(),"name",doctor.getName(),
                "phone",doctor.getPhone(),"speciality",doctor.getSpeciality());
        try(Session session = AppUtils.getSession()) {
            return session.executeWrite(tx -> {
                ResultSummary resultSummary = tx.run(query,params).consume();
                return resultSummary.counters().nodesCreated()>0;
            });
        }
    }

//    CREATE FULLTEXT INDEX Doctor_Speciality FOR (d:Doctor) ON EACH [d.Speciality]

    public static List<Doctor> listDoctorsBySpeciality(String keyword) {
        String query = """
                CALL db.index.fulltext.queryNodes("Doctor_Speciality", $keyword) YIELD node
                RETURN node
                """;
        Map<String, Object> params = Map.of("keyword",keyword);
        try (Session session = AppUtils.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query,params);
                return result.stream().map(record -> {
                    Node node = record.get("node").asNode();
                    return new Doctor(
                            node.get("ID").asString(),
                            node.get("Name").asString(),
                            node.get("Phone").asString(),
                            node.get("Speciality").asString()
                    );
                }).toList();
            });
        }

    }
    public static boolean  updateDiagnosis(String patientId, String doctorId,String diagnosis) {
        String query = """
                MATCH (p:Patient {ID:$patientID})-[r:Treatment]->(d:Doctor{ID:$doctorID})
                WHERE r.EndDate is NULL
                SET r.Diagnosis = $diagnosis
                """;
        Map<String, Object> params = Map.of("patientID",patientId,"doctorID",doctorId,"diagnosis",diagnosis);
        try (Session session = AppUtils.getSession()) {
            return session.executeWrite(tx -> {
                ResultSummary resultSummary = tx.run(query,params).consume();
                return resultSummary.counters().propertiesSet()>0;
            });
        }
    }

    public static void main(String[] args) {
//        Doctor doctor = new Doctor("DR.100","Hung","0123456789","Nhan Khoa");
//        System.out.println(addDoctor(doctor));
//        List<Doctor> doctors = listDoctorsBySpeciality("Internal");
//        doctors.forEach(System.out::println);
        System.out.println(updateDiagnosis("PT005","DR.011", "Lalalala"));
    }

}
