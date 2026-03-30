package dao;

import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import util.AppUtils;

import java.util.Map;
import java.util.Objects;

public class OrderDAO {
    public static double calculateTotalOrder(String orderId) {
        String query = """
                MATCH (o:Order)-[r:ORDERS]-(p:Product)\s
                WHERE o.order_id = $orderId
                RETURN sum(r.quantity*r.unit_price*(1-r.discount)) as total
                """;
        Map<String, Object> params = Map.of("orderId",orderId);
        try(Session session = AppUtils.getSession()) {
            return session.executeRead(tx -> {
                Result result = tx.run(query,params);
                return result.single().get("total").asDouble();
            });
        }
    }

    public static void main(String[] args) {
        double res = calculateTotalOrder("O008");
        System.out.println(res);
    }
}
