import dao.*;
import model.Product;
import model.Supplier;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO = new ProductDAO();
        SupplierDAO supplierDAO = new SupplierDAO();

        List<Product> products =  productDAO.listProductsBySupplier("Tokyo Traders",1,3);
        products.forEach(System.out::println);

        Supplier supplier = new Supplier("S006","Mayumi Ohno","VietNam","LeNguyenQuocHuy");
        System.out.println(supplierDAO.updateSupplier(supplier));

        double total = orderDAO.calculateTotalOrder("O008");
        System.out.println(total);
    }
}
