package dao;

import model.Supplier;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import util.AppUtils;

import java.util.Map;

public class SupplierDAO {
    public static boolean updateSupplier (Supplier supplier) {
        if (supplier == null) {
            System.out.println("supplier: Không được null");
        }else if (supplier.getSupplier_id() ==""||supplier.getSupplier_id()==null){
            System.out.println("SupplierID: Không được null hoặc rỗng");
        }else if (supplier.getCompany_name() ==""||supplier.getCompany_name()==null){
            System.out.println("Các thuộc tính cập nhật: CompanyName: Không được null hoặc rỗng");
        }

        String query = """
                MATCH (s:Supplier {supplier_id: $supplierId})
                WHERE $companyName IS NOT NULL AND trim($companyName) <> ""
                SET s.company_name = $companyName
                RETURN COUNT(s) > 0 AS updated
                """;
        Map<String,Object> params = Map.of("supplierId",supplier.getSupplier_id(),"companyName",supplier.getCompany_name());
        try(Session session = AppUtils.getSession()) {
            return session.executeWrite(tx -> {
                Result result = tx.run(query,params);
                return result.single().get("updated").asBoolean();
            });
        }
    }

    public static void main(String[] args) {
        Supplier supplier = new Supplier("S006","Mayumi Ohno","VietNam","TrungNguyen");
        System.out.println(updateSupplier(supplier));
    }
}
