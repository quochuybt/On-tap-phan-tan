package dao;

import model.Order;
import model.Product;
import model.Status;
import model.Supplier;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.summary.ResultSummary;
import org.neo4j.driver.types.Node;
import util.AppUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductDAO {
//    CREATE RANGE INDEX index_productNameFOR (p:Product)ON (p.product_name)
    public static List<Product> listProductsByName(String name, int page, int size) {
        if (name==null||name.trim()=="") {
            throw new RuntimeException("Loi");
        }else if (page<0) {
            throw new RuntimeException("Loi");
        }else if (size<0) {
            throw new RuntimeException("Loi");
        }
        int skip = (page -1)*size;
        String query = """
                MATCH (p:Product)\s
                WHERE p.product_name = $name
                RETURN p SKIP $skip LIMIT $limit
                """;
        Map<String, Object> params = Map.of("name",name,"skip",skip,"limit",size);
        try (Session session= AppUtils.getSession()) {
            return session.executeRead(tx->{
                Result result = tx.run(query,params);
                return result.stream()
                        .map(record -> {
                            Node node = record.get("p").asNode();
                            return new Product(
                                    node.get("product_id").asString(),
                                    node.get("product_name").asString(),
                                    node.get("unit").asString(),
                                    node.get("unit_price").asDouble(),
                                    node.get("units_in_stock").asInt()
                            );
                        }).toList();
            });
        }
    }
//    CREATE INDEX Status_order FOR (o:Order) ON (o.status)
    public static List<Order> listOrdersByStatus(String status) {
        String query = """
                MATCH (o:Order {status:$status}) RETURN o
                """;
        Map<String, Object> params = Map.of("status",status);
        try (Session session= AppUtils.getSession()) {
            return session.executeRead(tx->{
                Result result = tx.run(query,params);
                return result.stream()
                        .map(record -> {
                            Node node = record.get("o").asNode();
                            return new Order(
                                    node.get("order_id").asString(),
                                    node.get("order_date").asLocalDate(),
                                    node.get("customer_name").asString(),
                                    node.get("employee_name").asString(),
                                    Status.valueOf(node.get("status").asString())
                            );
                        }).toList();
            });
        }
    }
//    CREATE INDEX country_Supplier FOR (s:Supplier) ON (s.country)

    public static List<Supplier> listSuppliersByCountry(String country) {
        String query = """
                MATCH (s:Supplier {country:$country}) RETURN s
                """;
        Map<String, Object> params = Map.of("country",country);
        try (Session session= AppUtils.getSession()) {
            return session.executeRead(tx->{
                Result result = tx.run(query,params);
                return result.stream()
                        .map(record -> {
                            Node node = record.get("s").asNode();
                            return new Supplier(
                                    node.get("supplier_id").asString(),
                                    node.get("company_name").asString(),
                                    node.get("contact_name").asString(),
                                    node.get("country").asString()
                            );
                        }).toList();
            });
        }
    }

    public static boolean addProduct(Product product, String supplierID){
        String query = """
                CREATE (p:Product {product_id:$id,product_name:$name,unit:$unit,unit_price:$price,units_in_stock:$stock})
                WITH p
                MATCH (s:Supplier {supplier_id:$supplierID})
                MERGE (s)-[:SUPPLIES]->(p)
                """;
        Map<String, Object> params = Map.of("id",product.getProductId(),"name",product.getProductName(),"unit",product.getUnit(),
                "price",product.getUnitPrice(),"stock",product.getUnitsInStock(),"supplierID",supplierID);
        try (Session session= AppUtils.getSession()) {
            return session.executeWrite(tx->{
                ResultSummary resultSummary = tx.run(query,params).consume();
                return resultSummary.counters().nodesCreated()>0;
            });
        }
    }
    public static boolean updateProductPrice(String productID, double newPrice) {
        String query = """
                MATCH (p:Product {product_id:$productID})\s
                SET p.unit_price = $newPrice
                """;
        Map<String, Object> params = Map.of("productID",productID,"newPrice",newPrice);
        try (Session session= AppUtils.getSession()) {
            return session.executeWrite(tx->{
                ResultSummary resultSummary = tx.run(query,params).consume();
                return resultSummary.counters().propertiesSet()>0;
            });
        }
    }
    public static boolean deleteSupplier(String supplierID) {
        String query = """
                MATCH (s:Supplier {supplier_id:$supplierID})\s
                DETACH DELETE s
                """;
        Map<String, Object> params = Map.of("supplierID",supplierID);
        try (Session session= AppUtils.getSession()) {
            return session.executeWrite(tx->{
                ResultSummary resultSummary = tx.run(query,params).consume();
                return resultSummary.counters().nodesDeleted()>0;
            });
        }
    }

    public static Map<String, Long> countProductsBySupplier(){
        String query = """
                MATCH (s:Supplier)-[r:SUPPLIES]->(p:Product)
                RETURN s.supplier_id as supplier_id, count(p) as total
                """;
        try (Session session= AppUtils.getSession()) {
            return session.executeRead(tx->{
                Result result = tx.run(query);
                return result.stream().collect(Collectors.toMap(
                        r -> r.get("supplier_id").asString(),
                        r->r.get("total").asLong()
                ));
            });
        }
    }
    public static Map<Integer, Double> revenueByMonth(int year) {
        String query = """
                MATCH (o:Order)-[r:ORDERS]->(p:Product)\s
                WHERE o.order_date.year = $year
                RETURN o.order_date.month as month, sum(r.quantity*r.unit_price*(1-r.discount)) as revenue
                """;
        Map<String, Object> params = Map.of("year",year);
        try (Session session= AppUtils.getSession()) {
            return session.executeRead(tx->{
                Result result = tx.run(query,params);
                return result.stream().collect(Collectors.toMap(
                        r -> r.get("month").asInt(),
                        r->r.get("revenue").asDouble()
                ));
            });
        }
    }

    public static void main(String[] args) {
//        listProductsByName("Chai",1,3).forEach(System.out::println);
//        listOrdersByStatus("COMPLETED").forEach(System.out::println);
//        listSuppliersByCountry("Japan").forEach(System.out::println);
//        Product product = new Product("P050","Shiba","100kg",100.8,100);
//        System.out.println(addProduct(product,"S005"));
//        System.out.println(updateProductPrice("P014",1000));
//        System.out.println(deleteSupplier("S002"));
//        countProductsBySupplier().forEach(
//                (k,v)-> System.out.println(k+" "+v)
//        );
        revenueByMonth(2024).forEach(
                (k,v) -> System.out.println(k+" "+v)
        );
    }
}
