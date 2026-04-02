package dao;

import model.Trainer;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;
import util.AppUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrainerDAO {
    public static Map<String, Long> getNoOfTrainersBySpecialty (String centerName) {
        String query = """
                MATCH (t:Trainer)-[r:BELONGS_TO]->(c:Center)
                WHERE c.name = $centerName
                RETURN t.specialty as speciality, count(t) as total
                """;
        Map<String,Object> params = Map.of("centerName",centerName);
        try(Session session = AppUtils.getSession()) {
            return session.executeRead(tx-> {
                Result result = tx.run(query,params);
                return result.stream().collect(
                        Collectors.toMap(
                                r -> r.get("speciality").asString(),
                                r -> r.get("total").asLong()
                        )
                );
            });
        }
    }
    public static boolean addTrainerToCenter (Trainer trainer, String centerName) {
        String query = """
                CREATE (t:Trainer {id:$id,name:$name,phone:$phone,specialty:$speciality})
                WITH t
                MATCH (c:Center {name:$centerName})\s
                MERGE (t)-[:BELONGS_TO]->(c)
                """;
        Map<String,Object> params = Map.of("centerName",centerName,"id",trainer.getId(),"name",trainer.getName(),
                "phone",trainer.getPhone(),"speciality",trainer.getSpeciality());
        try(Session session = AppUtils.getSession()) {
            return session.executeWrite(tx-> {
                ResultSummary resultSummary = tx.run(query,params).consume();
                return resultSummary.counters().nodesCreated()>0;
            });
        }
    }

//    CREATE FULLTEXT INDEX Trainer_Speciality FOR (t:Trainer) ON EACH [t.specialty]

    public static List<Trainer> listTrainersBySpecialty (String keyword) {
        String query = """
                CALL db.index.fulltext.queryNodes("Trainer_Speciality", $keyword) YIELD node
                RETURN node
                """;
        Map<String,Object> params = Map.of("keyword",keyword);
        try(Session session = AppUtils.getSession()) {
            return session.executeRead(tx-> {
                Result result = tx.run(query,params);
                return result.stream().map(record -> {
                    Node node = record.get("node").asNode();
                    return new Trainer(
                            node.get("id").asString(),
                            node.get("name").asString(),
                            node.get("phone").asString(),
                            node.get("specialty").asString()
                    );
                }).toList();
            });
        }
    }
    public static boolean	updateSessionDuration(String memberID ,String trainerID, Float newDuration) {
        String query = """
                MATCH (m:Member)-[r:TRAINED_BY]->(t:Trainer)
                WHERE m.id = $memberID AND t.id = $trainerID
                SET r.duration = $duration
                """;
        Map<String,Object> params = Map.of("memberID",memberID,"trainerID",trainerID,"duration",newDuration);
        try(Session session = AppUtils.getSession()) {
            return session.executeWrite(tx-> {
                ResultSummary resultSummary = tx.run(query,params).consume();
                return resultSummary.counters().propertiesSet()>0;
            });
        }
    }
    public static void main(String[] args) {
//        getNoOfTrainersBySpecialty("Cooley Ltd").forEach(
//                (k,v) -> System.out.println(k + " "+ v)
//        );
//        Trainer trainer = new Trainer("TRN100","Nguyen","0123456789","Succac");
//        System.out.println(addTrainerToCenter(trainer,"Cooley Ltd"));
        listTrainersBySpecialty("Succac").forEach(System.out::println);
        System.out.println(updateSessionDuration("MEM005","TRN013",10.0f));
    }
}
