package dao;

import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import util.AppUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DepartmentDAO {
    public static Map<String, Long> getNoOfDOctorsBySpeciality(String dptName) {
        Map<String,Long> res = new HashMap<>();
        String query = """
                MATCH (dp:Department {name: $dptName})-[r:BELONG_TO]->(d:Doctor)\s
                RETURN d.Speciality as speciality, count(d) as total
                """;
        Map<String,Object> params = Map.of("dptName",dptName);
        try (Session session = AppUtils.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query,params);
                return result.stream()
                        .collect(Collectors.toMap(
                                r -> r.get("speciality").asString(),
                                r-> r.get("total").asLong()
                        ));
            });
        }
    }

    public static void main(String[] args) {
        getNoOfDOctorsBySpeciality("Internal Medicine").forEach(
                (k,v) -> System.out.println(k + " " + v)
        );
    }
}
