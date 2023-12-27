package ru.vsu.cs.MikhailKostyrya.web;


import ru.vsu.cs.MikhailKostyrya.models.Order;
import ru.vsu.cs.MikhailKostyrya.models.Product;
import ru.vsu.cs.MikhailKostyrya.sql_repos.OrderSQLRepo;
import ru.vsu.cs.MikhailKostyrya.sql_repos.ProductSQLRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@WebServlet(name = "products", urlPatterns = {"/products/*"} )
public class ProductServlet extends CRUDServlet<Product> {
    public ProductServlet() {
        super(Product.class, ProductSQLRepo.getINSTANCE());
    }

    @Override
    protected boolean validate(Product obj) {
        return
                obj.getPrice() != null &&
                obj.getName() != null &&
                obj.getDescription() != null &&
                obj.getWarehouseId() != null;
    }

    @Override
    public List<Product> getWithParams(HttpServletRequest request) {
        String order_id = request.getParameter("order_id");
        if(order_id != null) {
            return ProductSQLRepo.getINSTANCE().getByOrderId(Integer.parseInt(order_id));
        }
        return ProductSQLRepo.getINSTANCE().read();
    }
}
